package ru.skypro.homework.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "ads")
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ads ads = (Ads) o;
        return getId() != null && Objects.equals(getId(), ads.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
