package com.ecommerce.controller.category;

import com.ecommerce.dto.DtoCategory;
import com.ecommerce.model.Category;
import com.ecommerce.responses.Response;
import com.ecommerce.service.category.ICategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/api/category")
public class CategoryControllerImpl implements ICategoryController{

    @Autowired
    ICategoryService categoryService;

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
    public ResponseEntity<Response<DtoCategory>> getProductById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        return new ResponseEntity<Response<DtoCategory>>(
                prepareResponse(categoryService.getCategoryById(id) , request),
                HttpStatus.OK
        );
    }

    @Override
    @PutMapping(path = "/update")
    public ResponseEntity<Response<DtoCategory>> updateProduct(@RequestBody Category category, HttpServletRequest request) {
        return new ResponseEntity<Response<DtoCategory>>(
                prepareResponse(categoryService.updateCategory(category) , request) ,
                HttpStatus.OK
        );
    }

    @Override
    @PostMapping(path = "/create")
    public ResponseEntity<Response<DtoCategory>> createProduct(@RequestBody Category category, HttpServletRequest request) {
        return new ResponseEntity<Response<DtoCategory>>(
                prepareResponse(categoryService.createCategory(category) , request),
                HttpStatus.OK
        );
    }

    @Override
    @GetMapping(path = "/get/all")
    public ResponseEntity<Response<List<DtoCategory>>> getAllProducts(HttpServletRequest request) {
        return new ResponseEntity<Response<List<DtoCategory>>>(
                prepareResponse(categoryService.getAllCategory() , request) ,
                HttpStatus.OK
        );
    }

    @Override
    @DeleteMapping(path = "/delete")
    public void deleteProductById(Long id) {
        categoryService.deleteCategoryById(id);
    }
}
