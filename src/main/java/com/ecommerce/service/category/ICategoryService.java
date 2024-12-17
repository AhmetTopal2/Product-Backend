package com.ecommerce.service.category;

import com.ecommerce.dto.DtoCategory;
import com.ecommerce.model.Category;

import java.util.List;

public interface ICategoryService {

    DtoCategory getCategoryById(Long id);

    DtoCategory createCategory(Category category);

    DtoCategory updateCategory(Category category);

    void deleteCategoryById(Long id);

    List<DtoCategory> getAllCategory();
}
