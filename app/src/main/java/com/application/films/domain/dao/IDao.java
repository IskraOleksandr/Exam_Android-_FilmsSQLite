package com.application.films.domain.dao;

public interface IDao<T> {

    int getItemCount();
    T getItem(int pos);
    int getItemId(int pos);
    T getItemById(int id);
    void addItem(T item);
    //TO DO:
    //boolean removeItem(int id);
    //boolean updateItem(T item)
}
