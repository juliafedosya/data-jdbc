package com.korabelska.demo.repository;

import com.korabelska.demo.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseRepository<T,ID> {

    protected final SpannerTemplate spannerTemplate;

    public abstract T create(T t);

    public abstract T updateExisting(T t) throws EntityNotFoundException;

    public abstract List<T> findAll();

    public abstract void delete(T t);

    public abstract void deleteById(ID id);

    public abstract boolean existsById(ID id);

    public abstract Optional<T> findById(ID id);

}
