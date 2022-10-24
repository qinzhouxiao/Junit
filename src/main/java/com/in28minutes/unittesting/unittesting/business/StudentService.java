package com.in28minutes.unittesting.unittesting.business;

import com.in28minutes.unittesting.unittesting.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {

    Student getStudentByID(int id);

    List<Student> getAllStudent();

}
