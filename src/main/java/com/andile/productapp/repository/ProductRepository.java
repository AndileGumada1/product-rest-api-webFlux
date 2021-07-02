package com.andile.productapp.repository;

import com.andile.productapp.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,String> {

}
