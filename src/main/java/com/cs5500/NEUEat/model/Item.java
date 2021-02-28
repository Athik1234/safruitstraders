package com.cs5500.FreshMart.model;

import java.util.Objects;

public class Item {

  private String itemName;
  private double price;
  private String imageUrl;

  public Item() {
  }

  public Item(String itemName, double price, String imageUrl) {
    this.itemName = itemName;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item)) {
      return false;
    }
    Item item = (Item) o;
    return Double.compare(item.getPrice(), getPrice()) == 0 &&
        getItemName().equals(item.getItemName()) &&
        getImageUrl().equals(item.getImageUrl());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getItemName(), getPrice(), getImageUrl());
  }

  @Override
  public String toString() {
    return "Item{" +
        "itemName='" + itemName + '\'' +
        ", price=" + price +
        ", imageUrl='" + imageUrl + '\'' +
        '}';
  }
}
