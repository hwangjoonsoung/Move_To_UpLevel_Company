package me.mtuc.conference.registrations.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.mtuc.conference.enums.PayMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class registrations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String good_name;

    @Column(nullable = false, length = 50)
    private int price = 0;
    @Column(nullable = false, length = 50)
    private int amount = 0;

    @Column(nullable = true, length = 20)
    @Enumerated(EnumType.STRING)
    private PayMethod pay_method;

    @Column(nullable = false, length = 20)
    private boolean pay_status = false;

    @Column(nullable = false, length = 20)
    private String trade_id;
    @Column(nullable = false, length = 20)
    private String order_id;

    @Column(nullable = true)
    private LocalDateTime date_of_payment;

    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, length = 50)
    private String affiliation;
    @Column(nullable = false, length = 50)
    private String position;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 30)
    private String phone;

    @Column(nullable = false, length = 1)
    private boolean registered = false;

    private LocalDateTime date_of_regist ;
    private LocalDateTime date_of_create = LocalDate.now();

    @Column(nullable = false, length = 20)
    private String member_id;
}
