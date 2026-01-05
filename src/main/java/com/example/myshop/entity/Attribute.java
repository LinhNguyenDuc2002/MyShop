package com.example.myshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "attributes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Attribute {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;
}
