package io.tamknown.libapp.service;

import io.tamknown.libapp.model.Student;
import io.tamknown.libapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.lang.Integer;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addUser(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllUsers() {
        return studentRepository.findAll();
    }

    public Optional<Student> getUser(Integer id) {
        return studentRepository.findById(id);
    }

    public void updateUser(Integer id, Student studentUpdate) {
        studentRepository.findById(id)
                .map(student -> {
                    student.setName(studentUpdate.getName());
                    return studentRepository.save(student);
                })
                .orElseGet(() -> {
                    studentUpdate.setId(id);
                    return studentRepository.save(studentUpdate);
                });
    }

    public void deleteUser(Integer id) {
        studentRepository.deleteById(id);
    }
}
