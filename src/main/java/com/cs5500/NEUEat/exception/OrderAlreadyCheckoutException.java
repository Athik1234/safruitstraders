package com.cs5500.FreshMart.exception;

public class OrderAlreadyCheckoutException extends Exception {

  public OrderAlreadyCheckoutException(String message) {
    super(message);
  }
}
