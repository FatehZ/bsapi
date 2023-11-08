package com.ktxdevelopment.userms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<String> getEmailsOfUsers(List<String> ids);

}
