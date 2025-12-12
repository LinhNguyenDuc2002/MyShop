package com.example.myshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Image {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "format")
    private String format;

    @Column(name = "secure_url")
    private String secureUrl;

    @Column(name = "resource_type")
    private String resourceType;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = true)
    private Comment comment;
}
