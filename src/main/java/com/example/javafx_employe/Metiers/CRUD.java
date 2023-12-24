package com.example.javafx_employe.Metiers;

import javafx.collections.ObservableList;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface CRUD<T,PK> {
    public boolean Create(T object);
    public ObservableList<T> All();
    public Optional<T> Read(PK id);
    public boolean Update(T object,PK id);
    public boolean Delete(PK id);
    public Long Count();
}
