package com.ktxdevelopment.studentms.service;

import com.ktxdevelopment.studentms.exception.InternalServerException;
import com.ktxdevelopment.studentms.model.ClientStudentRequestModel;
import com.ktxdevelopment.studentms.model.GlobalClientResponse;
import com.ktxdevelopment.studentms.model.Student;
import com.ktxdevelopment.studentms.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;

    @Override
    public GlobalClientResponse saveStudent(ClientStudentRequestModel request) {
        Student student = studentRepository.save(Student.builder()
                .id(request.getId())
                .name(request.getName())
                .age(request.getAge()).build()
        );
        return new GlobalClientResponse(student.getId());
    }

    @Override
    public List<Student> getAllReadersOfBook(String bookId) {
        return studentRepository.findUsersByReadingBook(bookId).orElseThrow(InternalServerException::new);
    }

    @Override
    public String subscribeToAuthor(String studentId, String authorId) {
        Student student = studentRepository.findById(studentId).orElseThrow(InternalServerException::new);
        if (student.getSubscriptions().contains(authorId)) return "Already subscribed";
        student.getSubscriptions().add(authorId);
        studentRepository.save(student);
        return "Successfully subscribed";
    }

    @Override
    public List<String> getAllSubscribersOfAuthor(String authorId) {
        return studentRepository.findUsersBySubscriberAuthor(authorId).orElse(new ArrayList<String>());
    }
}
