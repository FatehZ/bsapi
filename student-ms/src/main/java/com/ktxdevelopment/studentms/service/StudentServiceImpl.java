package com.ktxdevelopment.studentms.service;

import com.ktxdevelopment.studentms.model.ClientAuthorRequestModel;
import com.ktxdevelopment.studentms.model.UserClientResponse;
import com.ktxdevelopment.studentms.repo.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    @Override
    public UserClientResponse saveStudent(ClientAuthorRequestModel request) {
        return studentRepository.saveStudent(request);
    }
}
