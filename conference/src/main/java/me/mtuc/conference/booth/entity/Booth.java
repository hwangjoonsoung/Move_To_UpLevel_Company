package me.mtuc.conference.booth.entity;

import jakarta.persistence.*;
import lombok.*;
import me.mtuc.conference.booth.dto.BoothRequestDto;
import me.mtuc.conference.booth.dto.StaffInfoDto;
import me.mtuc.conference.common.entity.FeeItem;
import me.mtuc.conference.enums.PayMethod;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "booth")
public class Booth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_item_id")
    private FeeItem feeItem;

    @Builder.Default
    @OneToMany(mappedBy = "booth", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Staff> staff = new ArrayList<>();

    @Column(name = "company_name", length = 50, nullable = false)
    private String companyName;

    @Column(name = "ceo_name", length = 50 , nullable = false)
    private String ceoName;

    @Column(name = "company_phone_number", length = 30,nullable = false)
    private String companyPhoneNumber;

    @Column(name = "booth_count", length = 1,nullable = false)
    private Integer boothCount;

    @Column(name = "booth_ids",length = 20, nullable = false)
    private String boothIds;

    @Column(name = "manager_name", length = 30,nullable = false)
    private String managerName;

    @Column(name = "manager_affiliations",length = 50,nullable = false)
    private String managerAffiliations;

    @Column(name = "manager_phone_number", length = 30,nullable = false)
    private String managerPhoneNumber;

    @Column(name = "manager_email",length = 50,nullable = false)
    private String managerEmail;

    @Column(name = "password" , length = 50,nullable = false)
    private String password;

    @Column(name = "price")
    private Integer price = 0;

    @Column(name = "payment_status",length = 1)
    private boolean paymentStatus = false;

    @Column(name = "amount")
    private Integer amount = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method",length = 10)
    private PayMethod paymentMethod;

    @Column(name = "date_of_payment")
    private LocalDateTime dateOfPayment;
    @Column(name = "date_of_create", nullable = false)
    private LocalDateTime dateOfCreate;
    @Column(name = "date_of_update")
    private LocalDateTime dateOfUpdate;

    @Column(name = "is_deleted")
    @Builder.Default
    private boolean isDeleted = false;

    @PrePersist
    private void onPersist(){
        this.dateOfCreate = LocalDateTime.now();
    }

    @PreUpdate
    private void updatePersist(){
        this.dateOfUpdate = LocalDateTime.now();
    }

    //== 연관관계 편의 메서드 ==//
    public void addStaff(Staff staff) {
        this.getStaff().add(staff);
        staff.setBooth(this);
    }

    public void updateBooth(BoothRequestDto boothRequestDto, FeeItem feeItem) {
        this.companyName= boothRequestDto.getBoothInfo().getCompanyName();
        this.ceoName= boothRequestDto.getBoothInfo().getCeoName();
        this.companyPhoneNumber= boothRequestDto.getBoothInfo().getCompanyPhoneNumber();
        this.boothCount= boothRequestDto.getBoothInfo().getBoothCount();
        this.boothIds= boothRequestDto.getBoothInfo().getBoothIds();
        this.managerName= boothRequestDto.getBoothInfo().getManagerName();
        this.managerAffiliations= boothRequestDto.getBoothInfo().getManagerAffiliations();
        this.managerPhoneNumber= boothRequestDto.getBoothInfo().getManagerPhoneNumber();
        this.managerEmail= boothRequestDto.getBoothInfo().getManagerEmail();
        this.price= boothRequestDto.getBoothInfo().getPrice();
        this.feeItem = feeItem;
        List<StaffInfoDto> staffs = boothRequestDto.getStaffs();
        this.getStaff().clear();

        staffs.forEach(staff -> {
            Staff newStaff = Staff.builder()
                    .affiliation(staff.getAffiliation())
                    .name(staff.getName())
                    .position(staff.getPosition())
                    .build();
            this.addStaff(newStaff);
        });
    }
}
