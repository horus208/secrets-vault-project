package com.example.authenticationserver.mappers;

public interface DtoMapper<T1,T2>{

    public T1 mapDto(T2 toBeMapped);
}