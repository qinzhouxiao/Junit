package com.in28minutes.unittesting.unittesting.business;

import com.in28minutes.unittesting.unittesting.data.StudentDAO;
import com.in28minutes.unittesting.unittesting.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentDAO studentDAO;

    @Override
    public Student getStudentByID(int id) {
        return studentDAO.getStudentByID(id);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentDAO.getAllStudent();
    }
}
