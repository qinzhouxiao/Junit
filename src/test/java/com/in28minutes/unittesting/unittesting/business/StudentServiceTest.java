package com.in28minutes.unittesting.unittesting.business;

import com.in28minutes.unittesting.unittesting.data.StudentDAO;
import com.in28minutes.unittesting.unittesting.model.Address;
import com.in28minutes.unittesting.unittesting.model.Item;
import com.in28minutes.unittesting.unittesting.model.Student;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @InjectMocks
    private StudentDAO studentDAO;

    @Mock
    private StudentServiceImpl studentServiceImpl;


    @Test
    public void getStudentByID() {
        when(studentDAO.getStudentByID(1)).thenReturn(new Student(1,"张三","male"));
        when(studentDAO.getStudentByID(2)).thenReturn(new Student(2,20,"李四","female",new Address("上海","road")));
        Student student1 = studentServiceImpl.getStudentByID(1);
        assertEquals(student1.getAge(),1);
    }

//    @Test
//    void getAllStudent() {
//    }
}