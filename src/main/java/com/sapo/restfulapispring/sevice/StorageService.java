package com.sapo.restfulapispring.sevice;

import com.sapo.restfulapispring.model.Storage;

import java.util.List;

public interface StorageService {
    List<Storage> findAll();
}
