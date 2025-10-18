package me.mtuc.conference.paper.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "int")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paper_id" , columnDefinition = "int")
    private Paper paper;

    @Column(name = "affiliation_code",columnDefinition = "int")
    private Integer affiliationCode;

    @Column(name = "deleted")
    @Builder.Default
    private boolean deleted = false;
}
