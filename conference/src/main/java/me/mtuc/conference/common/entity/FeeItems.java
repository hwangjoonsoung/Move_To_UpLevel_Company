package me.mtuc.conference.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.mtuc.conference.registrations.domain.Registrations;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fee_items")
public class FeeItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(name = "member_type", nullable = false, length = 10)
    private String memberType;

    @Column(name = "is_member", nullable = false, length = 1)
    private boolean isMember = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fees_id")
    private Fees fees;

    @OneToMany(mappedBy = "feeItems",cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Registrations> registrations = new ArrayList<>();
}
