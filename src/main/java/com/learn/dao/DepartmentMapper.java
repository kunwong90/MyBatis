package com.learn.dao;

import com.learn.entity.Department;
import com.learn.entity.Department2;
import com.learn.mybatis.mapper.BaseMapper;

/**
 * Created by wangkun on 2017/4/16.
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    Department getDepartmentById(int id);

    Department2 getDepartment2ById(int id);

    /**
     * 插入对象
     * @param department
     * @return
     */
    int insertDepartment(Department department);

    int deleteById(Integer id);
}
