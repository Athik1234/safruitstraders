package com.cs5500.FreshMart.repository;

import com.cs5500.FreshMart.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {

}
