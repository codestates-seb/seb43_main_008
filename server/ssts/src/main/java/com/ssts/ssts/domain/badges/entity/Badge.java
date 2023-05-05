package com.ssts.ssts.domain.badges.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "badges")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;


    public static Badge of(String name, String description){
        Badge badge = new Badge();
        badge.name = name;
        badge.description = description;

        return badge;
    }

}
