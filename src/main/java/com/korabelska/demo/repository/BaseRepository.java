package com.korabelska.demo.repository;

import com.korabelska.demo.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseRepository<T, ID> {

    protected final SpannerTemplate spannerTemplate;

    public abstract T create(T t);

    public abstract T updateExisting(T t) throws EntityNotFoundException;

    public abstract List<T> findAll();

    public abstract Optional<T> findByKey(ID... keys);

    public void delete(T t) {
        spannerTemplate.delete(t);
    }

    public abstract void deleteByKey(ID... keys);

}
