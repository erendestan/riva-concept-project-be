package com.example.rivaconceptproject.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private Long userId;
}
