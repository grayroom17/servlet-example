package com.servlet.example.http.mapper;

public interface Mapper<F, T> {
    T map(F object);
}
