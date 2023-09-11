package com.example.library_management_system.service;

import com.example.library_management_system.Enum.CardStatus;
import com.example.library_management_system.Enum.Gender;
import com.example.library_management_system.model.Student;
import com.example.library_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.library_management_system.model.LibraryCard;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student) {
        //logic to create the libraryCard
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);  // set librarycard for student

        Student savedStudent = studentRepository.save(student); // save both student and library card
        return "Student saved successfully";
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