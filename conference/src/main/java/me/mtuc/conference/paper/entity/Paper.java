package me.mtuc.conference.paper.entity;

import jakarta.persistence.*;
import lombok.*;
import me.mtuc.conference.common.entity.Category;
import me.mtuc.conference.enums.AbstractLanguage;
import me.mtuc.conference.enums.PresentationType;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "presentation_type")
    private PresentationType presentationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "abstract_language")
    private AbstractLanguage abstractLanguage;
    @Column(name = "keyword")
    private String keyword;

    @Column(name = "abstract",columnDefinition = "TEXT")
    @Lob
    private String paperAbstract;

    @Column(name = "date_of_create")
    private LocalDateTime dateOfCreate;
    @Column(name = "date_of_update")
    private LocalDateTime dateOfUpdate;

    @Column(name = "deleted")
    @Builder.Default
    private boolean deleted = false;

}