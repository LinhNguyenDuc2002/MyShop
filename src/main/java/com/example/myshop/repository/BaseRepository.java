package com.example.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @param <E> Entity type
 * @param <I> ID type
 */
@NoRepositoryBean
public interface BaseRepository<E, I> extends JpaRepository<E, I>, ListQuerydslPredicateExecutor<E> {
}
