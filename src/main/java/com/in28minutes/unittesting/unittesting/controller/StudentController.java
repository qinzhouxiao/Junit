package com.in28minutes.unittesting.unittesting.controller;

import com.in28minutes.unittesting.unittesting.business.StudentService;
import com.in28minutes.unittesting.unittesting.business.StudentServiceImpl;
import com.in28minutes.unittesting.unittesting.model.Address;
import com.in28minutes.unittesting.unittesting.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/one-student")
    public Student oneStudent(int id) {
        return new Student(id,18,"zhangsan","male",new Address("shanghai","road"));
    }

    @GetMapping("/all-student")
    public List<Student> findAllStudents(){
        return studentService.getAllStudent();
    }

    @PostMapping("/add-student")
    public Student addStudent(@RequestBody Student student){return student;}

}
