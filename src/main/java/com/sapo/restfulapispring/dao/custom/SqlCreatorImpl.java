package com.sapo.restfulapispring.dao.custom;

import com.sapo.restfulapispring.anotation.Column;
import com.sapo.restfulapispring.anotation.Entity;
import com.sapo.restfulapispring.anotation.Table;
import com.sapo.restfulapispring.model.Product;
import com.sapo.restfulapispring.model.request.ProductRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SqlCreatorImpl<T> implements SqlCreator<T> {


    public String createSQLInsert(T obj) {
        String tableName = "";
        if (obj.getClass().isAnnotationPresent(Table.class)) {//nếu có @Table lấy tên bảng
            Table tableClass = obj.getClass().getAnnotation(Table.class);
            tableName = tableClass.name();
        }
        StringBuilder fields = new StringBuilder("");
        StringBuilder params = new StringBuilder("");

        for (Field field : obj.getClass().getDeclaredFields()) {
            if (fields.length() > 1) {
                fields.append(",");
                params.append(",");
            }
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (!column.name().equalsIgnoreCase("id")) {
                    fields.append(column.name());
                    params.append(":" + field.getName());
                }
            }
        }
        String sql = "Insert into " + tableName + " (" + fields.toString() + ") VALUES(" + params.toString() + ")";
        return sql;
    }

    public Map<String, Object> mapObject(T obj) {

        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> paramMap = new HashMap<>();
        for (Field field : fields) {
            Field f = null;
            try {
                f = obj.getClass().getDeclaredField(field.getName());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            f.setAccessible(true);
            try {
                paramMap.put(field.getName(), f.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return paramMap;
    }

    public String createSQLUpDate(T obj, boolean status) {
        String tableName = "";
        if (obj.getClass().isAnnotationPresent(Table.class)) {
            Table tableClass = obj.getClass().getAnnotation(Table.class);
            tableName = tableClass.name();
        }
        StringBuilder fields = new StringBuilder("");
        Field[] fs = obj.getClass().getDeclaredFields();
        for (Field field : fs) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (status) { // nếu status = true update toàn bộ. nếu  = false chỉ update những field có dữ liệu
                    if (fields.length() > 1) {
                        fields.append(",");
                    }
                    fields.append(column.name() + " = :" + field.getName() + " ");
                } else {
                    try {
                        Field f = null;
                        try {
                            f = obj.getClass().getDeclaredField(field.getName());
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        f.setAccessible(true);
                        if (f.get(obj) != null) {
                            if (fields.length() > 1) {
                                fields.append(",");
                            }
                            fields.append(column.name() + " = :" + field.getName() + " ");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (fields.length() == 0) return null;
        String sql = "Update " + tableName + " set " + fields.toString() + " where id = :id";
        return sql;
    }

    public static void main(String[] args) {
        Product product = new Product();
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("a");
        BeanUtils.copyProperties(productRequest, product);
        SqlCreator<Product> sqlCreator = new SqlCreatorImpl<>();
        System.out.println(sqlCreator.createSQLUpDate(product, true));
    }
}
