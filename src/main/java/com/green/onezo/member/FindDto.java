package com.green.onezo.member;

import lombok.*;

public class FindDto {


    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserIdRes {
        private String userId;
    }

    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PasswordRes {
        private String password;
    }




}
