package com.sapo.restfulapispring.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "storages")
@Getter
@Setter
@ToString
public class Storage {
    @Column(name = "id", unique = true)
    @Id
    private Long id;
    @Column(name = "storage_code", unique = true)
    private String storageCode;
    @Column(name = "storage_name")
    private String storageName;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "modified_date")
    private Date modifiedDate;
}
