package com.in28minutes.unittesting.unittesting.model;

import com.in28minutes.unittesting.unittesting.business.StudentService;
import com.in28minutes.unittesting.unittesting.business.StudentServiceImpl;
import com.in28minutes.unittesting.unittesting.data.StudentDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentTest {

    /**
     * @InjectMocks和@Autowired:
     * 1.Usually when you are unit testing, you shouldn't initialize Spring context. So remove Autowiring.
     * 在执行单元测试时不会启动Spring容器，因此不使用Autowired
     * 2.Usually when you do integration testing, you should use real dependencies. So remove mocking.
     * 在做集成测试时，需要考虑对象之间的依赖关系，因此不适用mock
     */
    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentDAO studentDAO;

    @Captor
    ArgumentCaptor<Integer> captor;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll(){
        when(studentDAO.getAllStudent()).thenReturn(Arrays.asList(new Student(1,18,"zhangsan","male",new Address("shanghai","road"))));
        assertEquals("zhangsan",studentService.getAllStudent().get(0).getName());
    }

    @Test
    public void findByID(){
        when(studentDAO.getStudentByID(1)).thenReturn(new Student(1,18,"zhangsan","male",new Address("shanghai","road")));
        Student student = studentService.getStudentByID(1);
        assertEquals("male",student.getGender());
        verify(studentDAO,times(1)).getStudentByID(1);
        verify(studentDAO,times(1)).getStudentByID(anyInt());
        verify(studentDAO,atLeast(0)).getStudentByID(anyInt());
        verify(studentDAO,atMost(1)).getStudentByID(anyInt());
    }

    @Test
    public void deleteStudent(){
        doNothing().when(studentDAO).deleteStudentByID(1);
        Random r=new Random();
        int k=r.nextInt(20)+3;
        for(int i=0;i<k;i++)
        {
            studentService.deleteStudentByID(1);
        }
        verify(studentDAO,atLeast(3)).deleteStudentByID(1);
        verify(studentDAO,times(k)).deleteStudentByID(1);
        verify(studentDAO,times(k)).deleteStudentByID(captor.capture());
        assertEquals(1,captor.getAllValues().get(0));
    }

}