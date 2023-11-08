package com.ktxdevelopment.studentms.service;


import com.ktxdevelopment.studentms.model.ClientStudentRequestModel;
import com.ktxdevelopment.studentms.model.GlobalClientResponse;
import com.ktxdevelopment.studentms.model.Student;

import java.util.List;

public interface StudentService {

    GlobalClientResponse saveStudent(ClientStudentRequestModel request);

    List<Student> getAllReadersOfBook(String bookId);

    String subscribeToAuthor(String studentId, String authorId);

    List<String> getAllSubscribersOfAuthor(String authorId);
}
