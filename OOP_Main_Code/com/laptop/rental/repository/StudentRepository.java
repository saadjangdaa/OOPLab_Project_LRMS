package com.laptop.rental.repository;

import com.laptop.rental.model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static final String FILE_NAME = "students.txt";

    public void save(Student student) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.printf("ID: %d Name: %s Password: %s Email: %s Phone: %s Department: %s%n", 
                    student.getId(), student.getName(), student.getPassword(), 
                    student.getEmail(), student.getPhoneNumber(), student.getDepartment());
        }
    }

    public List<Student> findAll() throws IOException {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return students;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 12) {
                    int id = Integer.parseInt(parts[1]);
                    String name = parts[3];
                    String password = parts[5];
                    String email = parts[7];
                    String phone = parts[9];
                    String department = parts[11];
                    students.add(new Student(id, name, password, email, phone, department));
                }
            }
        }
        return students;
    }

    public Student findById(int id) throws IOException {
        for (Student student : findAll()) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public Student findByName(String name) throws IOException {
        for (Student student : findAll()) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public void update(Student student) throws IOException {
        List<Student> students = findAll();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                break;
            }
        }
        saveAll(students);
    }

    public void delete(int id) throws IOException {
        List<Student> students = findAll();
        students.removeIf(student -> student.getId() == id);
        saveAll(students);
    }

    private void saveAll(List<Student> students) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, false))) {
            for (Student student : students) {
                writer.printf("ID: %d Name: %s Password: %s Email: %s Phone: %s Department: %s%n", 
                        student.getId(), student.getName(), student.getPassword(), 
                        student.getEmail(), student.getPhoneNumber(), student.getDepartment());
            }
        }
    }
} 