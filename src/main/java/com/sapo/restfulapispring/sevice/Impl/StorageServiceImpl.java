package com.sapo.restfulapispring.sevice.Impl;

import com.sapo.restfulapispring.model.Storage;
import com.sapo.restfulapispring.repository.StorageRepository;
import com.sapo.restfulapispring.sevice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public List<Storage> findAll() {
        return storageRepository.findAllCustom(); // select * from storages
    }
}
