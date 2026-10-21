package com.example.springstudents.repository;


import com.example.springstudents.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class InMemoryStudentDAO {

   private final List<Student> STUDENTS = new ArrayList<>();

    public synchronized List<Student> findAllStudent(){

        return List.copyOf(STUDENTS);
    }


    // returns null when a student with this email already exists
    public synchronized Student saveStudent(Student student){
        if(findByEmail(student.getEmail()) != null){
            return null;
        }
        STUDENTS.add(student);
        return student;
    }


    public synchronized Student findByEmail (String email){
        Student stud = STUDENTS.stream()
                .filter(e -> e.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        return stud;
    }

    public synchronized Student updateStudent (Student student){
        var studIndex = IntStream.range(0, STUDENTS.size())
                .filter(index -> STUDENTS.get(index).getEmail().equals(student.getEmail()))
                .findFirst()
                .orElse(-1);
        if(studIndex > -1){
            STUDENTS.set(studIndex, student);
            return student;
        }

        return null;
    }

    public synchronized void deleteStudent(String email){
        var student = findByEmail(email);
        if(student != null){
            STUDENTS.remove(student);
        }
    }

}
