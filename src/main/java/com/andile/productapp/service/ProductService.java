package com.andile.productapp.service;

import com.andile.productapp.dto.ProductDto;
import com.andile.productapp.repository.ProductRepository;
import com.andile.productapp.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //Flux is a class that returns one to many objects
    public Flux<ProductDto> getProducts(){
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> getProduct(String id){
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,String id){
        return productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(AppUtils::dtoToEntity).doOnNext(e ->e.setId(id)))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }
    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}
