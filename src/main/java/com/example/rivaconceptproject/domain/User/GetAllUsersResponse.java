package com.example.rivaconceptproject.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsersResponse {
    private List<User> users;
//    private List<UserSummary> users;
//
//    @Data
//    @Builder
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class UserSummary {
//        private long id;
//        private String firstName;
//        private String lastName;
//        private String email;
//        private long phoneNumber;
//    }
}
