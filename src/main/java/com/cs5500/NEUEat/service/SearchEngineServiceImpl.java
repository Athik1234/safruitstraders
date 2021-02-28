package com.cs5500.FreshMart.service;

import com.cs5500.FreshMart.model.Item;
import com.cs5500.FreshMart.model.GreengrocerInfo;
import com.cs5500.FreshMart.model.SearchEngine;
import com.cs5500.FreshMart.repository.SearchEngineRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchEngineServiceImpl implements SearchEngineService {

  @Autowired
  SearchEngineRepository searchEngineRepository;

  @Override
  public void addGreengrocer(String word, String greengrocerId) {
    Optional<SearchEngine> optionalSearchEngine = searchEngineRepository.findById("1");
    if (optionalSearchEngine.isEmpty()) return;
    SearchEngine searchEngine = optionalSearchEngine.get();
    searchEngine.add(word, greengrocerId);
    searchEngineRepository.save(searchEngine);
  }

  @Override
  public List<String> searchGreengrocer(String word) {
    Optional<SearchEngine> optionalSearchEngine = searchEngineRepository.findById("1");
    if (optionalSearchEngine.isEmpty()) return null;
    SearchEngine searchEngine = optionalSearchEngine.get();
    searchEngineRepository.save(searchEngine);
    return searchEngine.search(word);
  }

  @Override
  public void removeGreengrocer(String word, String greengrocerId) {
    Optional<SearchEngine> optionalSearchEngine = searchEngineRepository.findById("1");
    if (optionalSearchEngine.isEmpty()) return;
    SearchEngine searchEngine = optionalSearchEngine.get();
    searchEngine.remove(word, greengrocerId);
    searchEngineRepository.save(searchEngine);
  }

  @Override
  public void eraseInfo(GreengrocerInfo info, String greengrocerId) {
    this.removeGreengrocer(info.getGreengrocerName(), greengrocerId);
    this.removeGreengrocer(info.getTag1(), greengrocerId);
    this.removeGreengrocer(info.getTag2(), greengrocerId);
    this.removeGreengrocer(info.getTag3(), greengrocerId);
  }

  @Override
  public void eraseItems(List<Item> items, String greengrocerId) {
    for (Item item : items) {
      this.removeGreengrocer(item.getItemName(), greengrocerId);
    }
  }

  @Override
  public void updateInfo(GreengrocerInfo info, String greengrocerId) {
    this.addGreengrocer(info.getGreengrocerName(), greengrocerId);
    this.addGreengrocer(info.getTag1(), greengrocerId);
    this.addGreengrocer(info.getTag2(), greengrocerId);
    this.addGreengrocer(info.getTag3(), greengrocerId);
  }
}
