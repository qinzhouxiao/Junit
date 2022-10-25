package com.in28minutes.unittesting.unittesting.model;

import com.in28minutes.unittesting.unittesting.business.StudentService;
import com.in28minutes.unittesting.unittesting.business.StudentServiceImpl;
import com.in28minutes.unittesting.unittesting.data.StudentDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentDAO studentDAO;

    @Test
    public void getAll(){
        when(studentDAO.getAllStudent()).thenReturn(Arrays.asList(new Student(1,18,"zhangsan","male",new Address("shanghai","road"))));
        assertEquals("zhangsan",studentService.getAllStudent().get(0).getName());
    }

}