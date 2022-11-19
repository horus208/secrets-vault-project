package com.example.authenticationserver.dto.updaters;

public interface Updater<T>{

    void update(T toBeUpdated);

}
