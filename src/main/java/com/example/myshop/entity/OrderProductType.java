package com.example.myshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "order_product_type")
public class OrderProductType {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "unit_price")
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;
}
