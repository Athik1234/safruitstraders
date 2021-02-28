package com.cs5500.FreshMart.model;

import java.util.HashMap;
import java.util.Map;

public class Node {

  // childMap stores its descendent
  Map<Character, Node> childMap = new HashMap<>();
  // infoMap store greengrocerId and count
  Map<String, Integer> infoMap = new HashMap<>();

  public Node() {
  }

  public void insertGreengrocer(String greengrocerId) {
    if (!this.infoMap.containsKey(greengrocerId)) {
      this.infoMap.put(greengrocerId, 1);
    } else {
      int count = this.infoMap.get(greengrocerId);
      this.infoMap.put(greengrocerId, count + 1);
    }
  }

  public void deleteGreengrocer(String greengrocerId) {
    if (this.infoMap.containsKey(greengrocerId)) {
      int count = this.infoMap.get(greengrocerId);
      if (count == 1) {
        this.infoMap.remove(greengrocerId);
      } else {
        this.infoMap.put(greengrocerId, count - 1);
      }
    }
  }
}
