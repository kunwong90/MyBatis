package com.learn.service.impl;

import com.learn.service.inner.AbstractStudentService4;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl5 extends AbstractStudentService4 {
    @Override
    public void after() {
        System.out.println("StudentServiceImpl5 after");
    }

    @Override
    public void before() {
        System.out.println("StudentServiceImpl5 before");
    }
}
