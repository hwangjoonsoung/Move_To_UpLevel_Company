package me.mtuc.conference.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.mtuc.conference.common.embeded.RegistrationsFeeItemId;
import me.mtuc.conference.registrations.domain.Registrations;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registrations_fee_item")
public class RegistrationsFeeItems {

    @EmbeddedId
    private RegistrationsFeeItemId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("registrationsId")
    @JoinColumn(name = "registrations_id")
    private Registrations registrations;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("feeItemsId")
    @JoinColumn(name = "fee_item_id")
    private FeeItems feeItems;

}
