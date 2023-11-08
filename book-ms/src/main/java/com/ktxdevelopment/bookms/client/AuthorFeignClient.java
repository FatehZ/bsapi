package com.ktxdevelopment.bookms.client;

import com.ktxdevelopment.bookms.model.GlobalClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "author-ms",  url = "http://author-ms:8085")
public interface AuthorFeignClient {

    @DeleteMapping("/api/v1/internal/author/book")
    GlobalClientResponse deleteBookFromAuthor(@RequestParam String id, @RequestParam("uid") String uid);

    @PostMapping("api/v1/internal/author/book")
    GlobalClientResponse saveBookToAuthor(@RequestParam String id, @RequestParam("uid") String authorId);
}