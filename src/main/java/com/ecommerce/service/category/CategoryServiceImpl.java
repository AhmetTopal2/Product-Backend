package com.ecommerce.service.category;

import com.ecommerce.dto.DtoCategory;
import com.ecommerce.dto.DtoProduct;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ICategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    ICategoryRepository categoryRepository;

    DtoCategory convertDtoCategory(Category category){

        DtoCategory dtoCategory = new DtoCategory();
        dtoCategory.setCategoryName(category.getCategoryName());
        dtoCategory.setId(category.getId());
        dtoCategory.setProductList(converDtoProductList(category.getProductList()));

        return dtoCategory;
    }

    List<DtoProduct> converDtoProductList(List<Product> productList){
        List<DtoProduct> dtoProductList = new ArrayList<>();

        for (Product product : productList){
            DtoProduct dtoProduct = new DtoProduct();
            BeanUtils.copyProperties(product , dtoProduct);
            dtoProductList.add(dtoProduct);
        }
        return dtoProductList;
    }

    @Override
    public DtoCategory getCategoryById(Long id) {
       Optional<Category>  optional= categoryRepository.findById(id);
       if (optional.isPresent()){
            return  convertDtoCategory(optional.get());
       }else {
           throw new RuntimeException("Category Not Found");
       }
    }

    @Override
    public DtoCategory createCategory(Category category) {
        Category dbCategory = categoryRepository.save(category);
        if (dbCategory.getId()!=null && !dbCategory.getProductList().isEmpty()){
            return convertDtoCategory(dbCategory);
        }else {
            throw new RuntimeException("Category Kayit Isleminde Hata Olustu");
        }
    }

    @Override
    public DtoCategory updateCategory(Category category) {
        //en son duzeltmelerele yapilacak
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {
        if (categoryRepository.findById(id).isPresent()){
            categoryRepository.deleteById(id);
        }else {
            throw new RuntimeException("Category Not Found");
        }
    }

    @Override
    public List<DtoCategory> getAllCategory() {
        List<Category> dbCategoryList = categoryRepository.findAll();
        List<DtoCategory> dtoCategories = new ArrayList<>();
        for (Category category : dbCategoryList){
            dtoCategories.add(convertDtoCategory(category));
        }
        return dtoCategories;
    }
}
