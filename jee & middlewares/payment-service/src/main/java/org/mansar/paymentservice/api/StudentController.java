package org.mansar.paymentservice.api;

import org.mansar.paymentservice.model.Student;
import org.mansar.paymentservice.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> get() {
        return studentService.findAll();
    }

    @GetMapping("/major/{majorId}")
    public List<Student> getByMajor(@PathVariable("majorId") final String majorId) {
        return studentService.getByMajor(majorId);
    }

    @GetMapping("/{code}")
    public Student getByCode(@PathVariable("code") final String code) {
        return studentService.getByCode(code);
    }
}
