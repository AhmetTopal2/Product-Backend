package com.ecommerce.starter;

import com.ecommerce.dto.DtoProduct;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ICategoryRepository;
import com.ecommerce.repository.IProductRepository;
import com.ecommerce.service.product.ProductServiceImpl;
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
class ProductServiceTests {

    @Mock
    private IProductRepository productRepository;

    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;
    private Category testCategory;

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
        testProduct.setCategory(testCategory);
    }

    @Test
    void getProductById_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        
        DtoProduct result = productService.getProductById(1L);
        
        assertNotNull(result);
        assertEquals(testProduct.getName(), result.getName());
        assertEquals(testProduct.getBrand(), result.getBrand());
        assertEquals(testProduct.getPrice(), result.getPrice());
    }

    @Test
    void getProductById_NotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> {
            productService.getProductById(99L);
        });
    }

    @Test
    void createProduct_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(testCategory));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);
        
        DtoProduct result = productService.createProduct(testProduct);
        
        assertNotNull(result);
        assertEquals(testProduct.getName(), result.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void getAllProducts_Success() {
        List<Product> products = new ArrayList<>();
        products.add(testProduct);
        
        when(productRepository.findAll()).thenReturn(products);
        
        List<DtoProduct> result = productService.getAllProducts();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testProduct.getName(), result.get(0).getName());
    }

    @Test
    void deleteProduct_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        doNothing().when(productRepository).deleteById(1L);
        
        assertDoesNotThrow(() -> productService.deleteProductById(1L));
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteProduct_NotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> {
            productService.deleteProductById(99L);
        });
    }
} 