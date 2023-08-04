package ru.skypro.homework.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity(name = "ads")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "price")
    private Integer price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;


    @ToString.Exclude
    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL)
    private List<Comment> comments;



}
