package com.sapo.restfulapispring.repository;

import com.sapo.restfulapispring.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage,Long> {
    @Query("select s from Storage s where s.storageName like %:id%")
    List<Storage> findAllCustom();
}
