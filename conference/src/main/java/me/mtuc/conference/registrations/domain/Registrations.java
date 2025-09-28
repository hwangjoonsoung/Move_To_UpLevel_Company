package me.mtuc.conference.registrations.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.mtuc.conference.common.entity.FeeItems;
import me.mtuc.conference.enums.PayMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Registrations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "good_name", nullable = false, length = 50)
    private String goodName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_items_id")
    private FeeItems feeItems;

    @Column(nullable = false, length = 50)
    private int price = 0;
    @Column(nullable = false, length = 50)
    private int amount = 0;

    @Column(name = "pay_method", nullable = true, length = 20)
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    @Column(name = "pay_status", nullable = false, length = 20)
    private boolean payStatus = false;

    @Column(name = "trade_id", nullable = true, length = 20)
    private String tradeId;
    @Column(name= "order_id", nullable = true, length = 20)
    private String orderId;

    @Column(name = "date_id_payment", nullable = true)
    private LocalDateTime dateOfPayment;

    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, length = 30)
    private LocalDate birth;
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

    @Column(name = "date_of_regist")
    private LocalDateTime date_of_regist ;
    @Column(name = "date_of_create", nullable = false)
    private LocalDateTime date_of_create = LocalDateTime.now();

    @Column(nullable = false, length = 10)
    private int member_id = 0;
}
