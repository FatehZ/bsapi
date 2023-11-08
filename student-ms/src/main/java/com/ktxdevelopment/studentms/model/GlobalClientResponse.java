package com.ktxdevelopment.studentms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalClientResponse {
    String id;  // return id or null if exception occurred (rollback previous operations - basic implementation for simplicity)
}
