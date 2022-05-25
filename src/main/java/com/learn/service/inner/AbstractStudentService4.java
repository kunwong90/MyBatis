package com.learn.service.inner;

import com.learn.dao.DepartmentMapper;
import com.learn.dao.StudentMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public abstract class AbstractStudentService4 implements IStudentService4 {

    @Resource
    private AbstractStudentService4 self;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    public final void test() {
        before();
        self.addStudentAndDeleteDep();
        //((AbstractStudentService4) AopContext.currentProxy()).addStudentAndDeleteDep();
        after();
    }

    @Override
    public int add() {
        test();
        return 0;
    }

    @Transactional
    public void addStudentAndDeleteDep() {
        departmentMapper.deleteById(1);
        studentMapper.delete(1);
    }

    public abstract void after();

    public abstract void before();
}
