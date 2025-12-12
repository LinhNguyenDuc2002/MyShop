package com.example.myshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.Collection;

@Entity
@Table(name = "city")
public class City {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Collection<District> districts;
}
