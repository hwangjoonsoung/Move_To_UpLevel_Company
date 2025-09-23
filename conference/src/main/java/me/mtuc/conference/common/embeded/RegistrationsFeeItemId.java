package me.mtuc.conference.common.embeded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationsFeeItemId implements Serializable {

    @Column
    private Long registrationsId;

    @Column
    private Long feeItemsId;

}
