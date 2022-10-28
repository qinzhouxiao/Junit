package com.in28minutes.unittesting.unittesting.data;

import com.in28minutes.unittesting.unittesting.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO {

    public Student getStudentByID(int id) {
        return null;
    }

    public List<Student> getAllStudent() {
        return null;
    }

    public void deleteStudentByID(int id){

    }

    public Integer findStudentNum(){
        return null;
    }

}
