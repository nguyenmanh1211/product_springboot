package com.sapo.restfulapispring.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Category {
    private long id;
    private String categoryCode;
    private String categoryName;
    private String categoryDescription;
    private Date createdDate;
    private Date modifiedDate;

}
