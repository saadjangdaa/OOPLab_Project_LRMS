package com.laptop.rental.service;

import com.laptop.rental.model.Student;
import com.laptop.rental.repository.StudentRepository;
import java.io.IOException;
import java.util.List;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public boolean registerStudent(int id, String name, String password, String email, String phoneNumber, String department) throws IOException {
        if (studentRepository.findById(id) != null) {
            return false;
        }

        Student student = new Student(id, name, password, email, phoneNumber, department);
        studentRepository.save(student);
        return true;
    }

    public Student getStudentById(int id) throws IOException {
        return studentRepository.findById(id);
    }

    public Student getStudentByName(String name) throws IOException {
        return studentRepository.findByName(name);
    }

    public List<Student> getAllStudents() throws IOException {
        return studentRepository.findAll();
    }

    public boolean updateStudent(int id, String name, String email, String phoneNumber, String department) throws IOException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setEmail(email);
        student.setPhoneNumber(phoneNumber);
        student.setDepartment(department);
        
        studentRepository.update(student);
        return true;
    }

    public boolean deleteStudent(int id) throws IOException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            return false;
        }

        studentRepository.delete(id);
        return true;
    }

    public boolean authenticateStudent(int id, String password) throws IOException {
        Student student = studentRepository.findById(id);
        return student != null && student.getPassword().equals(password);
    }

    public boolean changePassword(int id, String oldPassword, String newPassword) throws IOException {
        Student student = studentRepository.findById(id);
        if (student == null || !student.getPassword().equals(oldPassword)) {
            return false;
        }

        student.setPassword(newPassword);
        studentRepository.update(student);
        return true;
    }
} 