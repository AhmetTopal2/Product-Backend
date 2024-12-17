package com.ecommerce.controller.category;

import com.ecommerce.dto.DtoCategory;

import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.responses.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICategoryController {
    ResponseEntity<Response<DtoCategory>> getProductById(Long id , HttpServletRequest request);

    ResponseEntity<Response<DtoCategory>> updateProduct(Category category, HttpServletRequest request);

    ResponseEntity<Response<DtoCategory>> createProduct(Category category , HttpServletRequest request);

    ResponseEntity<Response<List<DtoCategory>>> getAllProducts(HttpServletRequest request);

    void deleteProductById(Long id);
}

