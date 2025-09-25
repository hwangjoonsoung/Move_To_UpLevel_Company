package me.mtuc.conference.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "fees")
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Lob
    private String description;

    @OneToMany(mappedBy = "fees" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<FeeItems> feeItems = new ArrayList<>();
}
