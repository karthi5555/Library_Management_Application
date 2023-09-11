package com.example.library_management_system.controller;


import com.example.library_management_system.service.StudentService;
import com.example.library_management_system.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    //API to add student
    //also do add the libraryCard automatically
    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student){
        return  studentService.addStudent(student);
    }

    //API to get the student by regNo
    @GetMapping("/getStudentById")
    public ResponseEntity getStudent(@RequestParam("regNo")int regNo){
        Student student = studentService.getStudent(regNo);
        if(student!=null){
            return new ResponseEntity(student,HttpStatus.FOUND);
        }
        return new ResponseEntity("Invalid id!!",HttpStatus.BAD_REQUEST);
    }

    //API to delete a student By regNo
    @DeleteMapping("/deleteStudentById")
    public String deleteTheStudent(@RequestParam("regNo")int regNo){
        boolean delete = studentService.deleteStudent(regNo);
        if(delete){
            return "The student has been removed Successfully";
        }
        return "Invalid regNo!";
    }



    //API to update the age of a student by regNo, age
    @PutMapping("/updateStudentAge")
    public String updateStudentAge(@RequestParam("regNo")int regNo, @RequestParam("age")int age){
        Student student = studentService.updateStudent(regNo,age);
        if(student != null){
            return "The student age has been updated scuccessfully";
        }
        return "Invalid data!";
    }

    //API to get all the students in the db
    @GetMapping("/getAllStudents")
    public List<Student> getListOfStudents(){
        List<Student> list = studentService.getAllStudents();
        return list;
    }

    // get list of all male students
    @GetMapping("/getAllMaleStudents")
    public List<Student> listOfAllMaleStudents(){
        List<Student> maleList = studentService.getAllMaleStudents();
        return maleList;
    }
}