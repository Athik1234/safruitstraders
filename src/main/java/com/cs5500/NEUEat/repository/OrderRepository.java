package com.cs5500.FreshMart.repository;

import com.cs5500.FreshMart.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface OrderRepository extends MongoRepository<Order, String> {

}
