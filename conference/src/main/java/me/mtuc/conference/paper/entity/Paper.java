package me.mtuc.conference.paper.entity;

import jakarta.persistence.*;
import lombok.*;
import me.mtuc.conference.common.entity.Category;
import me.mtuc.conference.enums.AbstractLanguage;
import me.mtuc.conference.enums.PresentationType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",columnDefinition = "int")
    private Category category;

    @Column(name = "title", length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "presentation_type")
    private PresentationType presentationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "abstract_language")
    private AbstractLanguage abstractLanguage;

    @Column(name = "keyword", length = 100)
    private String keyword;

    @Lob
    @Column(name = "abstract",columnDefinition = "TEXT")
    private String paperAbstract;

    @Column(name = "date_of_create")
    private LocalDateTime dateOfCreate;

    @Column(name = "date_of_update")
    private LocalDateTime dateOfUpdate;

    @Column(name = "deleted")
    @Builder.Default
    private boolean deleted = false;

    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuthorAffiliation> authorAffiliations = new ArrayList<>();

    //== 연관관계 편의 메서드 ==//
    public void addAuthor(Author author){
        this.getAuthors().add(author);
        author.setPaper(this);
    }

}