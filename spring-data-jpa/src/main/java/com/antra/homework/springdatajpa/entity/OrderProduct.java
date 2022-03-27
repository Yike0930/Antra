

package com.antra.homework.springdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderProductStatus")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderProduct {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;


    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
//    @MapsId("productId")
    private Product product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}




