package com.cs5500.FreshMart.repository;

import com.cs5500.FreshMart.model.SearchEngine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SearchEngineRepository extends MongoRepository<SearchEngine, String> {

}
