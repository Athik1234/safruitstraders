package com.cs5500.FreshMart.service;

import com.cs5500.FreshMart.model.Comment;
import com.cs5500.FreshMart.model.Item;
import com.cs5500.FreshMart.model.Greengrocer;
import com.cs5500.FreshMart.model.GreengrocerInfo;
import java.util.List;

public interface GreengrocerService {

  int addItem(String id, Item item);

  int removeItem(String id, Item item);

  List<Item> getAllitems(String id);

  GreengrocerInfo getInformation(String id);

  int updateInfo(String id, GreengrocerInfo info);
}
