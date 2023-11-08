package com.ktxdevelopment.authorms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalClientResponse {
    String id;  // return id or null if exception occurred (rollback user creation)
}
