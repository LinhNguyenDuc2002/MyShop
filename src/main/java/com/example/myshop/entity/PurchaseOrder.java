package com.example.myshop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.Collection;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "total")
    private Double total;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<OrderProductType> orderProductTypes;

    @OneToOne
    @JoinColumn(name = "shipping_id")
    private Shipping shipping;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
}
