package com.andile.productapp.repository;

import com.andile.productapp.dto.ProductDto;
import com.andile.productapp.entity.Product;
import org.springframework.data.domain.Range;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,String> {
//    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);

}
