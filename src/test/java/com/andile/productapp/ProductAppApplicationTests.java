package com.andile.productapp;

import com.andile.productapp.controller.ProductController;
import com.andile.productapp.dto.ProductDto;
import com.andile.productapp.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import  static org.mockito.Mockito.when;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class ProductAppApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ProductService productService;
	@Test
	public void saveProductTest() {
		Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102","mobile",2,100));
		when(productService.saveProduct(productDtoMono)).thenReturn(productDtoMono);

		webTestClient.post().uri("http://localhost:8050/product/products")
						.body(Mono.just(productDtoMono), ProductDto.class)
						.exchange()
						.expectStatus().isOk();
	}
	@Test
	public void getProducts(){
		Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto("102","mobile",2,100),
				new ProductDto("103","television",21,1100));
		when(productService.getProducts()).thenReturn(productDtoFlux);

		Flux<ProductDto> responseBody = webTestClient.get().uri("http://localhost:8050/product")
				.exchange()
				.expectStatus()
				.isOk()
				.returnResult(ProductDto.class)
				.getResponseBody();
		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(new ProductDto("102","mobile",2,100))
				.expectNext(new ProductDto("103","television",21,1100))
				.verifyComplete();
	}
	@Test
	public void getProductTest(){
		Mono<ProductDto> productDtoMono=Mono.just(new ProductDto("102","mobile",1,10000));
		when(productService.getProduct(any())).thenReturn(productDtoMono);

		Flux<ProductDto> responseBody = webTestClient.get().uri("http://localhost:8050/product/102")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNextMatches(p->p.getName().equals("mobile"))
				.verifyComplete();
	}

	@Test
	public void updateProductTest(){
		Mono<ProductDto> productDtoMono=Mono.just(new ProductDto("102","mobile",1,10000));
		when(productService.updateProduct(productDtoMono,"102")).thenReturn(productDtoMono);

		webTestClient.put().uri("http://localhost:8050/product/update/102")
				.body(Mono.just(productDtoMono),ProductDto.class)
				.exchange()
				.expectStatus().isOk();//200
	}

	@Test
	public void deleteProductTest(){
		given(productService.deleteProduct(any())).willReturn(Mono.empty());
		webTestClient.delete().uri("http://localhost:8050/product/delete/102")
				.exchange()
				.expectStatus().isOk();//200
	}

}
