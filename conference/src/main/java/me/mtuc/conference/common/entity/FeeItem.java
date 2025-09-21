package me.mtuc.conference.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.message.StringFormattedMessage;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "fee_item")
public class FeeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int fee_id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 10)
    private String member_type;

    @Column(nullable = false,length = 1)
    private boolean is_member = false;


}
