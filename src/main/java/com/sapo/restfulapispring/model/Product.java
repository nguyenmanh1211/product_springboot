package com.sapo.restfulapispring.model;

import com.sapo.restfulapispring.anotation.Column;
import com.sapo.restfulapispring.anotation.Entity;
import com.sapo.restfulapispring.anotation.Table;
import lombok.*;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    private long id;
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "picture_path")
    private String picturePath;
    @Column(name = "product_amount")
    private Long productAmount;
    @Column(name = "\u200Bamount_sold")
    private Long amountSold;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "modified_date")
    private Date modifiedDate;
    @Column(name = "prices")
    private BigDecimal prices;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "storage_id")
    private Long storageId;
    @Column(name = "status")
    private boolean status;
}
