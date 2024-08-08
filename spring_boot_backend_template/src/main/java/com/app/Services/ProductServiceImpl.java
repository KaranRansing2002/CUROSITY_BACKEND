package com.app.Services;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Dao.FilterDao;
import com.app.Dao.ProductDao;
import com.app.Dao.ProductVariantDao;
import com.app.Entities.Product;
import com.app.Entities.ProductVariant;
import com.app.dto.ProdFilterReqDTO;
import com.app.dto.ProductResDTO;
import com.app.dto.ProductVariantDTO;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired 
    private ProductDao prod;
    
    @Autowired 
    private ProductVariantDao variant;
    
    @Autowired
    private FilterDao filter;
    
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ProductResDTO> getProductsByFilter(ProdFilterReqDTO product) {
        List<Long> imgids = filter.findByColorAndSize(product.getColor(), product.getSize());
        
        List<Product> prodss = product.getCat()==null ? prod.findAll() : prod.findByCategoryName(product.getCat());
        
        List<Product> prods=new ArrayList<>();
        for(Product p : prodss) {
        	if(product.getPrice()!=null && p.getPrice()>=product.getPrice()[0] && p.getPrice()<=product.getPrice()[1]) {
        	prods.add(p);
        	}
        	
        }
        
        List<ProductResDTO> result = new ArrayList<>();
        
        for (Product p : prods) {
            List<ProductVariant> variants = variant.findBypid(p.getPid());
            
            List<ProductVariant> filteredVariants = variants.stream()
                .filter(v -> imgids.contains(v.getImgid()))
                .collect(Collectors.toList());
            
            ProductResDTO dto = new ProductResDTO();
            dto.setProduct(p);
            dto.setVariants(filteredVariants.stream()
                .map(v -> mapper.map(v, ProductVariantDTO.class))  
                .collect(Collectors.toList()));
            
            result.add(dto);
        }
        
        return result;
    }
}