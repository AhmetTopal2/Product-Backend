package com.ecommerce.controller.product;

import com.ecommerce.dto.DtoProduct;
import com.ecommerce.model.Product;
import com.ecommerce.responses.Response;
import com.ecommerce.service.product.IProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/api/product")
public class ProductControllerImpl implements IProductController {

    @Autowired
    IProductService productService;

    private <T> Response<T> prepareResponse(T data , HttpServletRequest request){
        Response<T> productResponse = new Response<>();
        productResponse.setData(data);
        productResponse.setDate(new Date());
        productResponse.setStatus(HttpStatus.OK.value());
        productResponse.setMessage("İşlem başarılı");
        return productResponse;
    }

    @Override
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Response<DtoProduct>> getProductById(@PathVariable(name = "id") Long id , HttpServletRequest request) {

        return new ResponseEntity<Response<DtoProduct>>(
                prepareResponse(productService.getProductById(id), request)
                , HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/update")
    public ResponseEntity<Response<DtoProduct>> updateProduct(@RequestBody Product product, HttpServletRequest request) {
        return new ResponseEntity<Response<DtoProduct>>(
                prepareResponse(productService.updateProduct(product) , request),
                HttpStatus.OK
        );
    }

    @Override
    @PostMapping(path = "/save")
    public ResponseEntity<Response<DtoProduct>> createProduct(@RequestBody Product product, HttpServletRequest request) {
        return new ResponseEntity<Response<DtoProduct>>(
                prepareResponse(productService.createProduct(product) , request),
                HttpStatus.OK
        );
    }

    @Override
    @GetMapping(path = "/get/all")
    public ResponseEntity<Response<List<DtoProduct>>> getAllProducts(HttpServletRequest request) {
        List<DtoProduct> productList = productService.getAllProducts();

        // Response ile listeyi sarmalayarak döndür
        Response<List<DtoProduct>> response = new Response<>();
        response.setData(productList);
        response.setDate(new Date());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Ürün listesi başarıyla alındı");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Response<DtoProduct>> deleteProductById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        return new ResponseEntity<>(
                prepareResponse(productService.deleteProductById(id) , request),
                HttpStatus.OK
        );
    }


}
