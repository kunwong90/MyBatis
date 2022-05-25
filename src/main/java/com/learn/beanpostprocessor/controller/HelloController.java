package com.learn.beanpostprocessor.controller;

import com.learn.beanpostprocessor.service.impl.HelloServiceImplV1;
import com.learn.beanpostprocessor.service.impl.HelloServiceImplV2;
import com.learn.beanpostprocessor.util.SwitchUtil;
import com.learn.entity.Department;
import com.learn.entity.Student;
import com.learn.service.inner.IStudentService;
import com.learn.service.inner.IStudentService4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping
@Controller
public class HelloController {

    /*@Autowired
    private HelloServiceImplV1 helloServiceImplV1;

    @Autowired
    private HelloServiceImplV2 helloServiceImplV2;


    public void sayHello() {
        if (SwitchUtil.getHelloVersion().equals("A")) {
            helloServiceImplV1.sayHello();
        } else {
            helloServiceImplV2.sayHello();
        }
    }

    public void sayHi() {
        if (SwitchUtil.getHiVersion().equals("A")) {
            helloServiceImplV1.sayHi();
        } else {
            helloServiceImplV2.sayHi();
        }
    }*/

    @Autowired
    private IStudentService studentService;

    @Resource(name = "studentServiceImpl4")
    private IStudentService4 iStudentService;

    @RequestMapping("/add")
    public void add() {
        Student student = new Student();
        Department department = new Department();
        department.setId(2);
        student.setDepartment(department);
        student.setName("侯亮平");
        student.setSex("0");
        studentService.addAndDel(student);
    }
    @RequestMapping("/test")
    public void test() {
        iStudentService.add();
    }
}
