package me.mtuc.conference.common.entity;

import jakarta.persistence.*;
import lombok.*;
import me.mtuc.conference.enums.PaperType;
import me.mtuc.conference.paper.entity.Paper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "int")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "paper_type", length = 10)
    private PaperType paperType;

    @Column(name = "category_name", length = 90)
    private String categoryName;

    @Builder.Default
    @Column(name = "deleted")
    private boolean deleted = false;

    @Column(name = "date_of_create")
    private LocalDateTime dateOfCreate;

    @Column(name = "date_of_update")
    private LocalDateTime dateOfUpdate;

    @OneToMany(mappedBy = "category",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Paper> papers = new ArrayList<>();

}
