package com.watch.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "trademarks")
public class Trademark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "image")
    String image;

    @OneToMany(mappedBy = "trademark",
            cascade = CascadeType.ALL)
    Collection<Series> listSeries = new HashSet<>();

    public void addSeries(Series series){
        if (listSeries == null){
            listSeries = new HashSet<>();
        }
        listSeries.add(series);
        series.setTrademark(this);
    }
}
