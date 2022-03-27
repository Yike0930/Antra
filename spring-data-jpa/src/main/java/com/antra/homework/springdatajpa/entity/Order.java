package com.antra.homework.springdatajpa.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @Column(name = "customerID")
    private Long customerID;

    @Column(name = "orderDate")
    private String orderDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> order_products = new ArrayList<>();

    public Order() {

    }

    public List<OrderProduct> getOrderProducts() {
        return order_products;
    }

    public void setOrderProducts(List<OrderProduct> order_products) {

        this.order_products = order_products;
    }

    public void addOrderProductStatus(OrderProduct order_products) {
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


    public Order(Long orderID, Long customerID, String orderDate, List<OrderProduct> order_products) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.order_products = order_products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", customerID=" + customerID +
                ", orderDate='" + orderDate + '\'' +
                ", order_products=" + order_products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderID, order.orderID) &&
                Objects.equals(customerID, order.customerID) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(order_products, order.order_products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, customerID, orderDate, order_products);
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderProduct> getOrder_products() {
        return order_products;
    }

    public void setOrder_products(List<OrderProduct> order_products) {
        this.order_products = order_products;
    }
}

