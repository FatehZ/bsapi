package com.ktxdevelopment.authservice.user.model.dto;

import com.ktxdevelopment.authservice.user.model.response.UserLitResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLitDto {
    private String userId;
    private String username;

    public UserLitResponse toResponse() {
        return new UserLitResponse(userId, username);
    }
}
