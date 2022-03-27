package com.antra.homework.springdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor

public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> order_products = new ArrayList<>();

//    public List<OrderProduct> getOrderProductStatusDetail() {
//        return order_products;
//    }

    public void setOrderProduct(List<OrderProduct> order_products) {
        this.order_products = order_products;
    }

    public void addOrderProduct(OrderProduct order_products) {
        this.order_products.add(order_products);
    }

//    public void removeOrderProductStatusDetail(OrderProductStatus status) {
//        this.orderProductStatusDetail.remove(status);
//    }
//
//    public void updateOrderProductStatusDetail(OrderProductStatus status1, OrderProductStatus status2) {
//        this.orderProductStatusDetail.remove(status1);
//        this.orderProductStatusDetail.add(status2);
//    }
//
//    public List retrieveOrderProductStatusDetail(Long orderID) {
//        return this.orderProductStatusDetail;
//    }


    public Product(Long productId, String productName, int price, List<OrderProduct> order_products) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.order_products = order_products;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", order_products=" + order_products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                Objects.equals(productId, product.productId) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(order_products, product.order_products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, price, order_products);
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<OrderProduct> getOrder_products() {
        return order_products;
    }

    public void setOrder_products(List<OrderProduct> order_products) {
        this.order_products = order_products;
    }
}
