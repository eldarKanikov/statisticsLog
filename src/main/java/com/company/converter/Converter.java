package com.company.converter;

public interface Converter<S,T> {
    T convert(S s);
}
