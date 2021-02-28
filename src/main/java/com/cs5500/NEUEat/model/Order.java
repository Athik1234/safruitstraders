package com.cs5500.FreshMart.model;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;

public class Order {

  @Id
  private String id;
  private String customerId;
  private String greengrocerId;
  private String driverId;
  private LocalDateTime startTime;
  private boolean delivery;
  private LocalDateTime endTime;
  private List<Item> content;
  private double price;
  private Comment comment;

  public Order() {
  }

  public Order(String id, String customerId, String greengrocerId, String driverId,
      LocalDateTime startTime, boolean delivery, LocalDateTime endTime,
      List<Item> content, double price, Comment comment) {
    this.id = id;
    this.customerId = customerId;
    this.greengrocerId = greengrocerId;
    this.driverId = driverId;
    this.startTime = startTime;
    this.delivery = delivery;
    this.endTime = endTime;
    this.content = content;
    this.price = price;
    this.comment = comment;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getGreengrocerId() {
    return greengrocerId;
  }

  public void setGreengrocerId(String greengrocerId) {
    this.greengrocerId = greengrocerId;
  }

  public String getDriverId() {
    return driverId;
  }

  public void setDriverId(String driverId) {
    this.driverId = driverId;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public boolean isDelivery() {
    return delivery;
  }

  public void setDelivery(boolean delivery) {
    this.delivery = delivery;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  public List<Item> getContent() {
    return content;
  }

  public void setContent(List<Item> content) {
    this.content = content;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Comment getComment() {
    return comment;
  }

  public void setComment(Comment comment) {
    this.comment = comment;
  }

  @Override
  public String toString() {
    return "Order{" +
        "id='" + id + '\'' +
        ", customerId='" + customerId + '\'' +
        ", greengrocerId='" + greengrocerId + '\'' +
        ", driverId='" + driverId + '\'' +
        ", startTime=" + startTime +
        ", delivery=" + delivery +
        ", endTime=" + endTime +
        ", content=" + content +
        ", price=" + price +
        ", comment=" + comment +
        '}';
  }
}
