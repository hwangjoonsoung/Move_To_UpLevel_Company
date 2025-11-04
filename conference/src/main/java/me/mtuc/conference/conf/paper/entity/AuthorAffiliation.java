package me.mtuc.conference.conf.paper.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "author_affiliation")
public class AuthorAffiliation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "int")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paper_id",columnDefinition = "int")
    private Paper paper;

    @Column(name = "affiliation_code",columnDefinition = "int")
    private Integer code;

    @Column(name = "affiliation")
    private String affiliation;

    @Builder.Default
    @Column(name = "deleted")
    private boolean deleted = false;
}
