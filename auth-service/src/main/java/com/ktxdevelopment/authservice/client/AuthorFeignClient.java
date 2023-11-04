package com.ktxdevelopment.authservice.client;

import com.ktxdevelopment.authservice.model.ClientUserRequestModel;
import com.ktxdevelopment.authservice.model.UserClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "author-ms",  url = "http://internal:8086")
public interface AuthorFeignClient {

    @PostMapping("/api/v1/author")
    UserClientResponse saveAuthor(@RequestBody ClientUserRequestModel author);
}