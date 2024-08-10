package com.app.dto;

import java.util.List;
import com.app.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private Product product;
    private List<ProductVariantDTO> variants; 
	
}