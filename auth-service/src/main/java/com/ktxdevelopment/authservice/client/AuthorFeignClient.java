package com.ktxdevelopment.authservice.client;

import com.ktxdevelopment.authservice.model.request.ClientUserRequestModel;
import com.ktxdevelopment.authservice.model.response.GlobalClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "author-ms",  url = "http://author-ms:8085")
public interface AuthorFeignClient {

    @PostMapping("/api/v1/author")
    GlobalClientResponse saveAuthor(@RequestBody ClientUserRequestModel author);
}