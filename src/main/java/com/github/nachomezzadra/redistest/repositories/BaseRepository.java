package com.github.nachomezzadra.redistest.repositories;

import java.util.Map;

public interface BaseRepository<T> {

    public void save(T anObject);

    public T find(Long id);

    public Map<Object, Object> findAll();

    public void delete(Long id);
}
