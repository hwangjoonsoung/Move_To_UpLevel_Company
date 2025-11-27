package me.mtuc.conference.institute.login.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.mtuc.conference.institute.user.domain.User;

import java.time.LocalDateTime;

@Getter
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    private Long id;

    @Column(name = "refresh_token", length = 200,nullable = false)
    private String refreshToken;

    // 연관관계 생략
    @Column(name = "user_id",columnDefinition = "int")
    private Long userId;

    private LocalDateTime dateOfExpired ;
    @Builder.Default
    private LocalDateTime dateOfCreate = LocalDateTime.now();
}
