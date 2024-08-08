package com.app.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.app.dto.ProdFilterReqDTO;
import com.app.dto.ProductResDTO;

@Service
@Transactional
public interface ProductService {
	
	public List<ProductResDTO> getProductsByFilter(ProdFilterReqDTO prod);
}