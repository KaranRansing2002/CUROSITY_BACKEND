package com.app.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entities.Category;
import com.app.Entities.Size;
import com.app.Services.ProductService;
import com.app.dto.ProdFilterReqDTO;
import com.app.dto.ProductResDTO;
import com.app.dto.UserRegisterDTO;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {
	
	@Autowired
	ProductService ps;
	
	@GetMapping("/filter")
	public ResponseEntity<List<ProductResDTO>> getProductByFilter(
	        @RequestParam(required = false) String color,
	        @RequestParam(required = false) Size size,
	        @RequestParam(required = false) Category cat,
	        @RequestParam(required = false) int[] price) {

	    // Create and populate the filter object
	    ProdFilterReqDTO filter = new ProdFilterReqDTO();
	    filter.setColor(color);
	    filter.setSize(size);
	    filter.setCat(cat);
	    filter.setPrice(price);

	    // Fetch products using the service
	    List<ProductResDTO> products = ps.getProductsByFilter(filter);
	    return ResponseEntity.ok(products);
	}


}