package com.example.myshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "attribute")
public class Attribute {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;
}
