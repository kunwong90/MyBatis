package com.learn.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by wangkun on 2017/4/22.
 */
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class QueryLimitPlugin implements Interceptor {

    private int limit;

    private String dbType;

    private static final String LMT_TABLE_NAME = "limit_Table_Name_xxx";

    private static final String DELEGATE_BOUNDSQL_SQL = "delegate.boundSql.sql";

    /**
     * MappedStatement 对象在参数列表中的索引位置
     */
    private static final int MAPPEDSTATEMENT_INDEX = 0;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //取出被拦截对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[MAPPEDSTATEMENT_INDEX];
        if (ms.getSqlCommandType() == SqlCommandType.SELECT) {
            //分离代理对象，从而形成多次代理，通过两次循环最原始的被代理类，mybatis使用的是jdk代理
            while (metaObject.hasGetter("h")) {
                Object object = metaObject.getValue("h");
                metaObject = SystemMetaObject.forObject(object);
            }
            //分离最后一个代理对象的目标类
            while (metaObject.hasGetter("target")) {
                Object object = metaObject.getValue("target");
                metaObject = SystemMetaObject.forObject(object);
            }
            //取出即将要执行的sql
            String sql = (String) metaObject.getValue(DELEGATE_BOUNDSQL_SQL);
            String limitSql;
            //判断参数是不是mysql数据库且sql有没有被插件重写过
            if ("mysql".equals(this.dbType) && sql.indexOf(LMT_TABLE_NAME) == -1) {
                //去掉前后空格
                sql = sql.trim();
                //将参数写入sql
                limitSql = "select * from (" + sql + ") " + LMT_TABLE_NAME + " limit " + limit;
                //重写要执行的sql
                metaObject.setValue(DELEGATE_BOUNDSQL_SQL, limitSql);
            }
        }
        //调用原来对象的方法，进入责任链的下一层级
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String strLimit = (String) properties.getProperty("limit", "2");
        this.limit = Integer.parseInt(strLimit);
        this.dbType = properties.getProperty("dbType", "mysql");
    }
}
