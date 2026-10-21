package com.example.springstudents.controller;

import com.example.springstudents.model.Student;
import com.example.springstudents.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping
    public List<Student> findAllStudent (){
        return service.findAllStudent();
    }

    @PostMapping("save_student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@RequestBody Student student){
        Student saved = service.saveStudent(student);
        if(saved == null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Student with this email already exists");
        }
        return saved;
    }

    @GetMapping("/{email}")
    public Student findByEmail(@PathVariable String email){
        return orNotFound(service.findByEmail(email));
    }

    @PutMapping("update_student")
    public Student updateStudent(@RequestBody Student student){
        return orNotFound(service.updateStudent(student));
    }

    @DeleteMapping("delete_student/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable String email){
        orNotFound(service.findByEmail(email));
        service.deleteStudent(email);
    }

    private Student orNotFound(Student student){
        if(student == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        return student;
    }


}
