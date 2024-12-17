package com.ecommerce.service.product;

import com.ecommerce.dto.DtoProduct;
import com.ecommerce.model.Product;

import java.util.List;

public interface IProductService {

    DtoProduct getProductById(Long id);

    DtoProduct updateProduct(Product product);

    DtoProduct createProduct(Product product);

    List<DtoProduct> getAllProducts();

    DtoProduct deleteProductById(Long id);
}
