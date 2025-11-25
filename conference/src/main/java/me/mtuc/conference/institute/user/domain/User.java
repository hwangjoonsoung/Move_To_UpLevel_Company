package me.mtuc.conference.institute.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    private Long id;

    private String name;
    private String password;
    private String affiliations;
    private String position;
    private String phone;
    private String email;
    private String member_type;
    private String roll;
}
