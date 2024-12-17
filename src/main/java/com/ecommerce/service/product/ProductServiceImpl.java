package com.ecommerce.service.product;

import com.ecommerce.dto.DtoCategory;
import com.ecommerce.dto.DtoProduct;
import com.ecommerce.exception.ErrorMessage;
import com.ecommerce.exception.GlobalException;
import com.ecommerce.exception.MessageType;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ICategoryRepository;
import com.ecommerce.repository.IProductRepository;
import com.ecommerce.service.category.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    IProductRepository productRepository;

    @Autowired
    ICategoryRepository categoryRepository;

    public DtoProduct convertDtoProduct(Product product) {
        DtoProduct dtoProduct = new DtoProduct();
        BeanUtils.copyProperties(product, dtoProduct);

        if (product.getCategory() != null) {
            dtoProduct.setDtoCategory(convertDtoCategory(product.getCategory()));
        } else {
            dtoProduct.setDtoCategory(null);
        }

        return dtoProduct;
    }


    public DtoCategory convertDtoCategory(Category category){
        DtoCategory dtoCategory = new DtoCategory();
        dtoCategory.setCategoryName(category.getCategoryName());
        dtoCategory.setId(category.getId());
        return dtoCategory;
    }

    public Category findCategoryByName(String name){
        return categoryRepository.findCategoryByCategoryName(name);
    }

    @Override
    public DtoProduct getProductById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()){
            return convertDtoProduct(optional.get());

        }else{
            throw new GlobalException(new ErrorMessage(MessageType.RESOURCE_NOT_FOUND, "Urun Bulunamadı"));
        }
    }

    @Override
    public DtoProduct updateProduct(Product product) {
        Product dbProduct = productRepository.save(product);
        if (dbProduct.getId()!=null){
            return convertDtoProduct(dbProduct);
        }else {
            throw new GlobalException(new ErrorMessage(MessageType.BAD_REQUEST, "Urun Guncelleme Sirasinda Bir Hata Olustu"));
        }
    }

    @Override
    public DtoProduct createProduct(Product product) {
        product.setDateAdded(LocalDate.now());

        if (product.getCategory() == null || product.getCategory().getCategoryName() == null) {
            throw new GlobalException(new ErrorMessage(MessageType.VALIDATION_ERROR, "Category cannot be null"));
        }

        if (product.getCategory() != null && product.getCategory().getCategoryName() != null) {
            Category category = findCategoryByName(product.getCategory().getCategoryName());
            if (category != null) {
                product.setCategory(category);
            } else {
                Category newCategory = new Category();
                newCategory.setCategoryName(product.getCategory().getCategoryName());
                Category dbCategory = categoryRepository.save(newCategory);
                product.setCategory(dbCategory);
            }
        } else {
            throw new RuntimeException("Category cannot be null");
        }

        Product dbProduct = productRepository.save(product);
        if (dbProduct.getId() != null) {
            return convertDtoProduct(dbProduct);
        } else {
            throw new RuntimeException("Error occurred while saving the product");
        }
    }


    @Override
    public List<DtoProduct> getAllProducts() {
        List<Product> dbProductList = productRepository.findAll();
        List<DtoProduct> dtoProductList = new ArrayList<>();
        if (!dbProductList.isEmpty()){
            for (Product product : dbProductList){
                dtoProductList.add(convertDtoProduct(product));
            }
            return dtoProductList;
        }else {
            throw new GlobalException(new ErrorMessage(MessageType.RESOURCE_NOT_FOUND, "Urun Listesi Bulunamadi"));
        }

    }

    @Override
    public DtoProduct deleteProductById(Long id) {
        DtoProduct dtoProduct = this.getProductById(id);
        if (dtoProduct.getId()!=null){
            productRepository.deleteById(id);
            return dtoProduct;
        }else {
            throw new RuntimeException("Urun Silme Sirasinda Bir Hata Olustu");
        }
    }
}
