package com.whereismyfood.restapi.controllers.v1;

import com.whereismyfood.restapi.api.v1.model.ProductDTO;
import com.whereismyfood.restapi.api.v1.model.ProductListDTO;
import com.whereismyfood.restapi.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by Alex P. Andrade on 06/06/2018.
 */
@RestController
@RequestMapping(ProductController.BASE_URL)
@Api(description = "Product Controller")
public class ProductController {
    public static final String BASE_URL = "/api/v1/secure/products";

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a list of products.")
    public ProductListDTO getAllProducts(){
        return new ProductListDTO(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createNewProduct(@RequestBody ProductDTO productDTO){
        return productService.createNewProduct(productDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.saveProduct(id, productDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO patchProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.patchProduct(id, productDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
    }

}
