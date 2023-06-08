package com.company.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Elemento {
    @PrimaryKey(autoGenerate = true)
    int id;

    String nombre;
    String descripcion;
    int precio;
    String URL;

    float valoracion;

    public Elemento(String nombre, String descripcion, int precio ,String URL) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.URL=URL;



    }
}
