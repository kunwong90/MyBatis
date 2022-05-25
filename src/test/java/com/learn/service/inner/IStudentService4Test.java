package com.learn.service.inner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-service.xml"})
public class IStudentService4Test {


    //@Resource
    //private IStudentService4 studentServiceImpl5;
    @Resource
    private AbstractStudentService4 studentServiceImpl4;

    @Test
    public void addTest() {
        //studentServiceImpl.add();
        studentServiceImpl4.add();
    }
}