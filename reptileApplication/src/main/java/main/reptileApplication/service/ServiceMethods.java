package main.reptileApplication.service;

import java.util.List;

public interface ServiceMethods<T> {
    T create(T t);

    List<T> readAll();

    T readById(long id);

    List<T> readByName(String name);

    List<T> readBySpecie(String specie);


    T update(long id, T t);

    boolean delete(long id);
}
