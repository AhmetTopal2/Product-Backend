package com.ecommerce.starter;

import com.ecommerce.dto.DtoCategory;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ICategoryRepository;
import com.ecommerce.service.category.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceTests {

    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category testCategory;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testCategory = new Category();
        testCategory.setId(1L);
        testCategory.setCategoryName("Electronics");
        
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setBrand("Test Brand");
        testProduct.setModel("Test Model");
        testProduct.setPrice(new BigDecimal("999.99"));
        
        List<Product> products = new ArrayList<>();
        products.add(testProduct);
        testCategory.setProductList(products);
    }

    @Test
    void getCategoryById_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(testCategory));
        
        DtoCategory result = categoryService.getCategoryById(1L);
        
        assertNotNull(result);
        assertEquals(testCategory.getId(), result.getId());
        assertEquals(testCategory.getCategoryName(), result.getCategoryName());
        assertEquals(1, result.getProductList().size());
    }

    @Test
    void getCategoryById_NotFound() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryById(99L);
        });
    }

    @Test
    void createCategory_Success() {
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);
        
        DtoCategory result = categoryService.createCategory(testCategory);
        
        assertNotNull(result);
        assertEquals(testCategory.getCategoryName(), result.getCategoryName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void getAllCategories_Success() {
        List<Category> categories = new ArrayList<>();
        categories.add(testCategory);
        
        when(categoryRepository.findAll()).thenReturn(categories);
        
        List<DtoCategory> result = categoryService.getAllCategory();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testCategory.getCategoryName(), result.get(0).getCategoryName());
    }

    @Test
    void deleteCategory_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(testCategory));
        doNothing().when(categoryRepository).deleteById(1L);
        
        assertDoesNotThrow(() -> categoryService.deleteCategoryById(1L));
        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteCategory_NotFound() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> {
            categoryService.deleteCategoryById(99L);
        });
    }
} 