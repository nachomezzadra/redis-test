package com.github.nachomezzadra.redistest.repositories;

import com.github.nachomezzadra.redistest.domain.CustomPojo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CustomPojoRepositoryImpl implements CustomPojoRepository {


    private RedisTemplate<String, CustomPojo> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, CustomPojo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(CustomPojo anObject) {
        this.redisTemplate.opsForHash().put(CustomPojo.KEY, anObject.getId(), anObject);
    }

    @Override
    public CustomPojo find(Long id) {
        return (CustomPojo) this.redisTemplate.opsForHash().get(CustomPojo.KEY, id);
    }

    @Override
    public Map<Object, Object> findAll() {
        return this.redisTemplate.opsForHash().entries(CustomPojo.KEY);
    }

    @Override
    public void delete(Long id) {
        this.redisTemplate.opsForHash().delete(CustomPojo.KEY, id);
    }
}
