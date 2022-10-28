package com.in28minutes.unittesting.unittesting.model;

import com.in28minutes.unittesting.unittesting.business.StudentService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

class StudentServiceStub implements StudentService{

    private Map<Integer,Student> studentMap=new HashMap<>();

    public StudentServiceStub() {
        Student student1 = new Student(1, 18, "zhangsan", "male", new Address("shanghai", "road"));
        Student student2 = new Student(2, 19, "lisi", "male", new Address("shanghai", "road"));
        Student student3 = new Student(3, 20, "wangwu", "male", new Address("shanghai", "road"));
        Student student4 = new Student(4, 21, "Bob", "male", new Address("shanghai", "road"));
        studentMap.put(1,student1);
        studentMap.put(2,student2);
        studentMap.put(3,student3);
        studentMap.put(4,student4);
    }

    public Map<Integer, Student> getStudentMap() {
        return studentMap;
    }

    public void setStudentMap(Map<Integer, Student> studentMap) {
        this.studentMap = studentMap;
    }

    @Override
    public Student getStudentByID(int id) {
        return studentMap.get(id);
    }

    @Override
    public List<Student> getAllStudent() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public void deleteStudentByID(int id) {
        studentMap.remove(id);
    }

    @Override
    public Integer findStudentNum() {
        return studentMap.size();
    }
}

class StudentServiceEmptyStub implements StudentService{

    @Override
    public Student getStudentByID(int id) {
        return null;
    }

    @Override
    public List<Student> getAllStudent() {
        return null;
    }

    @Override
    public void deleteStudentByID(int id) {

    }

    @Override
    public Integer findStudentNum() {
        return null;
    }
}

public class StudentServiceStubTest {
    StudentService studentService=new StudentServiceStub();
    StudentService studentServiceEmpty=new StudentServiceEmptyStub();

    @Test
    public void findStudentByID(){
        assertEquals(1,studentService.getStudentByID(1).getId());
        assertEquals(19,studentService.getStudentByID(2).getAge());
        assertEquals("wangwu",studentService.getStudentByID(3).getName());
        assertEquals("male",studentService.getStudentByID(3).getGender());
        assertEquals(null,studentService.getStudentByID(anyInt()));
    }

    @Test
    public void findAllStudent(){
        assertEquals(4,studentService.getAllStudent().size());
        assertEquals("zhangsan",studentService.getAllStudent().get(0).getName());
        assertEquals(new Address("shanghai", "road"),studentService.getAllStudent().get(3).getAddress());
        assertEquals(null,studentServiceEmpty.getAllStudent());
    }

    @Test
    public void deleteStudent(){
        studentService.deleteStudentByID(1);
        studentServiceEmpty.deleteStudentByID(1);
        assertEquals(3,studentService.findStudentNum());
        assertEquals(null,studentService.getStudentByID(1));
        assertEquals("lisi",studentService.getAllStudent().get(0).getName());

    }

}
