package com.ecommerce.controller.product;

import com.ecommerce.dto.DtoProduct;
import com.ecommerce.model.Product;
import com.ecommerce.responses.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductController {
    ResponseEntity<Response<DtoProduct>> getProductById(Long id , HttpServletRequest request);

    ResponseEntity<Response<DtoProduct>> updateProduct(Product product , HttpServletRequest request);

    ResponseEntity<Response<DtoProduct>> createProduct(Product product , HttpServletRequest request);

    ResponseEntity<Response<List<DtoProduct>>> getAllProducts(HttpServletRequest request);

    ResponseEntity<Response<DtoProduct>> deleteProductById(Long id , HttpServletRequest request);
}
