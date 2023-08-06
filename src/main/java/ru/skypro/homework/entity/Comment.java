package ru.skypro.homework.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "comments")
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ads ads;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "text")
    private String text;


}
