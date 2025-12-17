package com.example.myshop.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "product_categories")
public class ProductCategory {
    @EmbeddedId
    private ProductCategoryId productCategoryId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @MapsId("categoryId")
    private Category category;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductCategoryId implements Serializable {
        private String productId;

        private String categoryId;
    }
}
