package com.ktxdevelopment.studentms.service;


import com.ktxdevelopment.studentms.model.ClientAuthorRequestModel;
import com.ktxdevelopment.studentms.model.UserClientResponse;

public interface StudentService {

    UserClientResponse saveStudent(ClientAuthorRequestModel request);

}
