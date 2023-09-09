package com.example.library_management_system;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
public class StudentService {

    @Autowired StudentRepository studentRepository;
    public Student addStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    public Student getStudent(int regNo) {

        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        }
        return null;
    }

    public boolean deleteStudent(int regNo){
        Optional<Student> student = studentRepository.findById(regNo);
        if(student.isPresent()) {
            studentRepository.deleteById(regNo);
            return true;
        }
        return false;

    }

    public Student updateStudent(int regNo, int newAge){

        Optional<Student> optionalStudent = studentRepository.findById(regNo);
        if(optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            student.setAge(newAge);
            studentRepository.save(student);
            return student;
        }
        return null;

    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getAllMaleStudents() {
        //we need to get all student list
        List<Student> allStudent = studentRepository.findAll();
        //use a for each loop and put the condition
        List<Student> anslist = new ArrayList<>();

        for(Student student : allStudent){
           if(student.getGender().equals(Gender.MALE)){
               anslist.add(student);
           }
        }
        return anslist;

    }
}