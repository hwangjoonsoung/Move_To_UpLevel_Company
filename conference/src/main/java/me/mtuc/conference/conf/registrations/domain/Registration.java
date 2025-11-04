package me.mtuc.conference.conf.registrations.domain;

import jakarta.persistence.*;
import lombok.*;
import me.mtuc.conference.conf.common.entity.FeeItem;
import me.mtuc.conference.conf.util.Util;
import me.mtuc.conference.conf.enums.PayMethod;
import me.mtuc.conference.conf.registrations.dto.RegistrationRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "good_name", nullable = false, length = 50)
    private String goodName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_item_id")
    private FeeItem feeItem;

    @Column(nullable = false)
    private Integer price = 0;
    @Column(nullable = false)
    private Integer amount = 0;

    @Column(name = "pay_method", nullable = true, length = 20)
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    @Column(name = "pay_status", nullable = false, length = 20)
    private boolean payStatus = false;

    @Column(name = "trade_id", nullable = true, length = 20)
    private String tradeId;
    @Column(name= "order_id", nullable = true, length = 20)
    private String orderId;

    @Column(name = "date_of_payment", nullable = true)
    private LocalDateTime dateOfPayment;

    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false)
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
    @Column(name = "date_of_update")
    private LocalDateTime dateOfUpdate ;
    @Column(name = "date_of_create", nullable = false)
    private LocalDateTime dateOfCreate;

    @Builder.Default
    @Column(name = "is_deleted", length = 1)
    private boolean isDeleted = false;

    @Column(nullable = false)
    private Integer memberId = 0;

    public void updateRegistration(RegistrationRequestDto registrationRequestDto, FeeItem feeItem){
        this.goodName = registrationRequestDto.getGoodName();
        this.feeItem = feeItem;
        this.price = registrationRequestDto.getPrice();
        this.name = registrationRequestDto.getName();
        this.birth = Util.stringToLocalDate(registrationRequestDto.getBirth());
        this.affiliation = registrationRequestDto.getAffiliation();
        this.position = registrationRequestDto.getPosition();
        this.email = registrationRequestDto.getEmail();
        this.phone = registrationRequestDto.getPhone();
    }

    @PrePersist
    public void onPersist(){
        LocalDateTime now = LocalDateTime.now();
        this.dateOfCreate = now;
    }

    @PreUpdate
    public void updatePersist(){
        LocalDateTime now = LocalDateTime.now();
        this.dateOfUpdate = now;
    }
}
