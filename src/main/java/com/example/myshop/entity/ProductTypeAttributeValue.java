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
@Table(name = "product_type_attributes")
public class ProductTypeAttributeValue {
    @EmbeddedId
    private ProductTypeAttributeValueId id;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    @MapsId("productTypeId")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "attribute_value_id")
    @MapsId("attributeValueId")
    private AttributeValue attributeValue;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductTypeAttributeValueId implements Serializable {
        private String productTypeId;

        private String attributeValueId;
    }
}
