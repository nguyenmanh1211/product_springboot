package com.sapo.restfulapispring.dao.common;

import com.sapo.restfulapispring.dao.custom.SqlCreator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class ObjectDAOImpl<T> implements ObjectDAO<T> {
    private SqlCreator<T> sqlCreator;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public ObjectDAOImpl(SqlCreator<T> sqlCreator, NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate, SimpleJdbcCall simpleJdbcCall) {
        this.sqlCreator = sqlCreator;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcCall = simpleJdbcCall;
    }

    public ObjectDAOImpl() {

    }


    @Override
    @Transactional
    public int create(T obj) {
        String sql = sqlCreator.createSQLInsert(obj);
        Map<String, Object> params = sqlCreator.mapObject(obj);
        if (!StringUtils.isNotBlank(sql)) return 0;
        if (params.size() < 1) return -1;
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public int update(T obj, long id, boolean status) {
        String sql = sqlCreator.createSQLUpDate(obj, status);
        Map<String, Object> params = sqlCreator.mapObject(obj);
        params.put("id", id);
        if (!StringUtils.isNotBlank(sql)) return 0;
        if (params.size() < 1) return -1;

        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public List<T> findAll(String sql, Class<T> zClass) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(zClass));
    }

    @Override
    public T findOne(String sql, Class<T> zClass) {
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(zClass));
    }


    @Override
    public Long getLong(String sql) {
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public void delete(String sql){
        jdbcTemplate.execute(sql);
    }

}
