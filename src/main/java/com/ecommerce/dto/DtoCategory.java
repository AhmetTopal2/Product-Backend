package com.ecommerce.dto;

import com.ecommerce.model.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoCategory {

    private Long id;

    private String categoryName;

    private List<DtoProduct> productList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<DtoProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<DtoProduct> productList) {
        this.productList = productList;
    }
}
