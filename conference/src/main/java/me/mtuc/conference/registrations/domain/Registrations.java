package me.mtuc.conference.registrations.domain;

import jakarta.persistence.*;
import lombok.*;
import me.mtuc.conference.Util;
import me.mtuc.conference.common.entity.FeeItems;
import me.mtuc.conference.enums.PayMethod;
import me.mtuc.conference.registrations.dto.RegistrationRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private LocalDateTime dateOfRegist ;
    @Column(name = "date_of_create", nullable = false)
    private LocalDateTime dateOfCreate;

    @Column(name = "is_deleted", length = 1)
    private boolean isDeleted = false;

    @Column(nullable = false, length = 10)
    private int memberId = 0;

    public void updateRegistrations(RegistrationRequestDto registrationRequestDto, FeeItems feeItems){
        this.goodName = registrationRequestDto.getGoodName();
        this.feeItems = feeItems;
        this.price = registrationRequestDto.getPrice();
        this.name = registrationRequestDto.getName();
        this.birth = Util.stringToLocalDate(registrationRequestDto.getBirth());
        this.affiliation = registrationRequestDto.getAffiliation();
        this.position = registrationRequestDto.getPosition();
        this.email = registrationRequestDto.getEmail();
        this.phone = registrationRequestDto.getPhone();
    }

    @PrePersist
    public void onPresist(){
        LocalDateTime now = LocalDateTime.now();
        this.dateOfCreate = now;
    }
}
