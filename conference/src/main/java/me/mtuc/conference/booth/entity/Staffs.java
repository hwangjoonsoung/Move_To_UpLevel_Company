package me.mtuc.conference.booth.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staffs")
public class Staffs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @Column(name = "affiliation",length = 50,nullable = false)
    private String affiliation;
    @Column(name = "position",length = 50,nullable = false)
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booths_id")
    private Booths booths;

    public void setBooths(Booths booths) {
        this.booths = booths;
        booths.getStaffs().add(this);
    }

}
