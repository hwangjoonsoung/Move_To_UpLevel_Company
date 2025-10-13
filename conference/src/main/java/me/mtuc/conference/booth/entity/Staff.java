package me.mtuc.conference.booth.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff")
public class Staff {

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
    @JoinColumn(name = "booth_id")
    private Booth booth;

    public void setBooth(Booth booth) {
        this.booth = booth;
        booth.getStaff().add(this);
    }

}
