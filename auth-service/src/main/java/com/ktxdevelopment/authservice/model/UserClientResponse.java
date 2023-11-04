package com.ktxdevelopment.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserClientResponse {
    String id;  // return id or null if exception occurred (rollback user creation)
}
