package com.cs5500.FreshMart.service;

import com.cs5500.FreshMart.model.Item;
import com.cs5500.FreshMart.model.GreengrocerInfo;
import java.util.List;

public interface SearchEngineService {
  void addGreengrocer(String word, String greengrocerId);
  List<String> searchGreengrocer(String word);
  void removeGreengrocer(String word, String greengrocerId);
  void eraseInfo(GreengrocerInfo info, String greengrocerId);
  void eraseItems(List<Item> items, String greengrocerId);
  void updateInfo(GreengrocerInfo info, String greengrocerId);
}
