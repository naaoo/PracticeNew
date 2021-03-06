package com.company.database.repositories;

import java.util.List;

public interface Repository<T> {

        public List<T> findAll();
        public T findOne(int id);
        public void create(T entity);
}
