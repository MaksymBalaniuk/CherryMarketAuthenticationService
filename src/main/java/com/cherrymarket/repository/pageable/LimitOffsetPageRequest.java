package com.cherrymarket.repository.pageable;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class LimitOffsetPageRequest implements Pageable {

    private final int limit;
    private final long offset;
    private final Sort sort;

    public LimitOffsetPageRequest(int limit, long offset, Sort sort) {
        if (limit < 1)
            throw new IllegalArgumentException("parameter.limit.cannot-be-less-than-one");

        if (offset < 0)
            throw new IllegalArgumentException("parameter.offset.cannot-be-negative");

        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    public LimitOffsetPageRequest(int limit, long offset) {
        this(limit, offset, Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        return (int) (offset / limit);
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Nonnull
    @Override
    public Sort getSort() {
        return Objects.isNull(sort) ? Sort.unsorted() : sort;
    }

    @Nonnull
    @Override
    public Pageable next() {
        return new LimitOffsetPageRequest(getPageSize(), getOffset() + getPageSize(), getSort());
    }

    public Pageable previous() {
        if (hasPrevious())
            return new LimitOffsetPageRequest(getPageSize(), getOffset() - getPageSize(), getSort());

        return this;
    }

    @Nonnull
    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Nonnull
    @Override
    public Pageable first() {
        return new LimitOffsetPageRequest(getPageSize(), 0, getSort());
    }

    @Nonnull
    @Override
    public Pageable withPage(int pageNumber) {
        return new LimitOffsetPageRequest(getPageSize(), (long) pageNumber * getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset >= limit;
    }

}
