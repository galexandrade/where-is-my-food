package com.whereismyfood.restapi.services;

import com.whereismyfood.restapi.api.v1.mapper.ProductMapper;
import com.whereismyfood.restapi.api.v1.model.ProductDTO;
import com.whereismyfood.restapi.domain.Product;
import com.whereismyfood.restapi.exceptions.ResourceNotFoundException;
import com.whereismyfood.restapi.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex P. Andrade on 03/06/2018.
 */
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(product -> {
                    ProductDTO productDTO = productMapper.fromProduct(product);
                    return productDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productRepository
                .findById(id)
                .map(productMapper::fromProduct)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ProductDTO createNewProduct(ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        return this.saveAndReturnDTO(product);
    }

    @Override
    public ProductDTO saveProduct(Long id, ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        product.setId(id);

        return this.saveAndReturnDTO(product);
    }

    @Override
    public ProductDTO patchProduct(Long id, ProductDTO productDTO) {
        return productRepository.findById(id).map(product -> {

            if(productDTO.getTitle() != null){
                product.setTitle(productDTO.getTitle());
            }

            if(productDTO.getDescription() != null){
                product.setDescription(productDTO.getDescription());
            }

            if(productDTO.getImageUrl() != null){
                product.setImageUrl(productDTO.getImageUrl());
            }

            if(productDTO.getPrice() != null){
                product.setPrice(productDTO.getPrice());
            }

            return productMapper.fromProduct(productRepository.save(product));
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO saveAndReturnDTO(Product product) {
        Product savedProduct = productRepository.save(product);

        ProductDTO returnDTO = productMapper.fromProduct(savedProduct);

        return returnDTO;
    }
}
