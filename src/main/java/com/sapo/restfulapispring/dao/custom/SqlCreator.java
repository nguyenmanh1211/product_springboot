package com.sapo.restfulapispring.dao.custom;

import java.util.Map;

public interface SqlCreator<T> {
    String createSQLInsert(T obj);
    Map<String,Object> mapObject(T obj);
    String createSQLUpDate(T obj,boolean status);
}
