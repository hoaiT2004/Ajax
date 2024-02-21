    package com.example.useajax.model;

    import jakarta.persistence.Entity;
    import jakarta.persistence.Id;
    import lombok.*;

    @Entity
    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class User {
        @Id
        private String username;
        private String password;
    }
