package com.sapo.restfulapispring.sevice.Impl;

import com.sapo.restfulapispring.dao.common.ObjectDAO;
import com.sapo.restfulapispring.dao.common.P;
import com.sapo.restfulapispring.model.Product;
import com.sapo.restfulapispring.model.request.ProductRequest;
import com.sapo.restfulapispring.sevice.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ObjectDAO<Product> productDAO;
    private Long productCodeMax;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductServiceImpl(ObjectDAO<Product> productDAO) {
        try {
            this.productDAO = productDAO;
        } finally {
            String sql = "select SUBSTRING_INDEX(product_code,'o',-1) as pz from product where product_code like '%pro%'  order by pz desc limit 0,1";
            productCodeMax = productDAO.getLong(sql);
        }
    }

    @Override
    public List<Product> findAll(int page, String name) {
        if (page < 1) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Page phải lớn hơn 0!!!");
        String sql = "Select * from product where product_name like '%" + name + "%' limit " + ((page - 1) * 10) + ",10";
        return productDAO.findAll(sql, Product.class);
    }

    @Override
    public Product create(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        product.setCreatedDate(new Date());
        String code = "Pro" + (productCodeMax + 1);
        try {
            if (!StringUtils.isNotBlank(product.getProductCode())) {
                product.setProductCode(code);
                productCodeMax += 1;
            }
            productDAO.create(product);
        } catch (DuplicateKeyException e) {
            log.error(e.getCause().getMessage());
            throw new ResponseStatusException(HttpStatus.OK,"Đã có mã của Sản phẩm này");
        }   catch (DataIntegrityViolationException e){
            log.error(e.getCause().getMessage());
            throw new ResponseStatusException(HttpStatus.OK,"Không tìm thấy !!!");
        }
        return product;
    }

    @Override
    public Product update(ProductRequest productRequest, long id, int status) {
        boolean stt = true;
        Product product = findOne(id);
        if (product == null) return null;
        BeanUtils.copyProperties(productRequest, product);
        product.setModifiedDate(new Date());
        if (status == 1) stt = false;
        int check = productDAO.update(product, id, stt);
        if (check == 1) return product;
        else return null;
    }

    @Override
    public Product findOne(long id) {
        String sql = "Select * from product where id = " + id;
        return productDAO.findOne(sql, Product.class);
    }

}
