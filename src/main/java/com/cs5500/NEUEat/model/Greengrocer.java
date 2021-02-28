package com.cs5500.FreshMart.model;

import java.util.List;

public class Greengrocer extends User {
  private GreengrocerInfo information;
  private List<Item> list;

  public Greengrocer() {
    this.setType("greengrocer");
  }

  public Greengrocer(String userName, String password, String phoneNumber, String address,
      String city, String state, String zip, GreengrocerInfo information,
      List<Item> list) {
    super(userName, password, phoneNumber, address, city, state, zip);
    this.setType("greengrocer");
    this.information = information;
    this.list = list;
  }

  public Greengrocer(String userName, String password, String phoneNumber, String address,
      String city, String state, String zip) {
    super(userName, password, phoneNumber, address, city, state, zip);
    this.setType("greengrocer");
  }

  public GreengrocerInfo getInformation() {
    return information;
  }

  public void setInformation(GreengrocerInfo information) {
    this.information = information;
  }

  public List<Item> getList() {
    return list;
  }

  public void setList(List<Item> list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return "Greengrocer{" +
        "information=" + information +
        ", list=" + list +
        '}';
  }
}
