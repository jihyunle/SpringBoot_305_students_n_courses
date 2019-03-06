package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/")
    public String index(Model model){

        // create a student
        Student student = new Student();
        student.setName("Doyle");
        student.setMajor("Computer Science");
        student.setGrade("Junior");

        // create a course
        Course course = new Course();
        course.setInstructor("Wolf");
        course.setTitle("Data Structures");
        course.setDepartment("Programming");

        // add the course to an empty list
        Set<Course> courses = new HashSet<Course>();
        courses.add(course);

        // add list of courses to student's course list
        student.setCourses(courses);

        // save the student to the database
        studentRepository.save(student);

        // grab all the students from the database and send them to the template
        model.addAttribute("students", studentRepository.findAll());

        return "index";

    }
}
