package com.github.nachomezzadra.redistest.repositories;

import com.github.nachomezzadra.redistest.BaseSpringTest;
import com.github.nachomezzadra.redistest.domain.CustomPojo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CustomPojoRepositoryTest extends BaseSpringTest {

    @Autowired
    private CustomPojoRepository repository;

    @Autowired
    private RedisTemplate<String, CustomPojo> redisTemplate;

    @Before
    public void before() {
        redisTemplate.delete(CustomPojo.KEY);
    }


    @Test
    public void shouldProperlySaveACustomPojo() {
        assertEquals(Long.valueOf(0), redisTemplate.opsForHash().size(CustomPojo.KEY));

        CustomPojo aCustomPojo = new CustomPojo(1L, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        repository.save(aCustomPojo);

        assertEquals(Long.valueOf(1), redisTemplate.opsForHash().size(CustomPojo.KEY));

        List<Object> values = redisTemplate.opsForHash().values(CustomPojo.KEY);
        assertEquals(aCustomPojo, values.get(0));
    }


    @Test
    public void shouldProperlyFindById() {
        CustomPojo aCustomPojo = new CustomPojo(2L, UUID.randomUUID().toString(), UUID.randomUUID().toString());
        redisTemplate.opsForHash().put(CustomPojo.KEY, aCustomPojo.getId(), aCustomPojo);
        assertEquals(Long.valueOf(1), redisTemplate.opsForHash().size(CustomPojo.KEY));

        CustomPojo actualPojo = repository.find(aCustomPojo.getId());

        assertEquals(aCustomPojo, actualPojo);
    }


    @Test
    public void shouldProperlyFindAll() {
        CustomPojo aCustomPojo1 = new CustomPojo(3L, UUID.randomUUID().toString(), UUID.randomUUID().toString());
        CustomPojo aCustomPojo2 = new CustomPojo(4L, UUID.randomUUID().toString(), UUID.randomUUID().toString());

        redisTemplate.opsForHash().put(CustomPojo.KEY, aCustomPojo2.getId(), aCustomPojo2);
        redisTemplate.opsForHash().put(CustomPojo.KEY, aCustomPojo1.getId(), aCustomPojo1);
        assertEquals(Long.valueOf(2), redisTemplate.opsForHash().size(CustomPojo.KEY));

        Map<Object, Object> allPojos = repository.findAll();

        assertEquals(aCustomPojo1, allPojos.get(aCustomPojo1.getId()));
        assertEquals(aCustomPojo2, allPojos.get(aCustomPojo2.getId()));
    }
}
