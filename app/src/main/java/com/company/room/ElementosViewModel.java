package com.company.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

public class ElementosViewModel extends AndroidViewModel {

    ElementosRepositorio elementosRepositorio;


    MutableLiveData<Elemento> elementoSeleccionado = new MutableLiveData<>();

    MutableLiveData<String> terminoBusqueda = new MutableLiveData<>();

    LiveData<List<Elemento>> resultadoBusqueda = Transformations.switchMap(terminoBusqueda, new Function<String, LiveData<List<Elemento>>>() {
        @Override
        public LiveData<List<Elemento>> apply(String input) {
            return elementosRepositorio.buscar(input);
        }
    });



    public ElementosViewModel(@NonNull Application application) {
        super(application);

        elementosRepositorio = new ElementosRepositorio(application);
    }



    LiveData<List<Elemento>> obtener(){
        return elementosRepositorio.obtener();
    }

    LiveData<List<Elemento>> masValorados(){
        return elementosRepositorio.masValorados();
    }

    LiveData<List<Elemento>> buscar(){
        return resultadoBusqueda;
    }

    void insertar(Elemento elemento){
        elementosRepositorio.insertar(elemento);
    }

    void eliminar(Elemento elemento){
        elementosRepositorio.eliminar(elemento);
    }

    void actualizar(Elemento elemento, float valoracion){
        elementosRepositorio.actualizar(elemento, valoracion);
    }


    void seleccionar(Elemento elemento){
        elementoSeleccionado.setValue(elemento);
    }

    MutableLiveData<Elemento> seleccionado(){
        return elementoSeleccionado;
    }


    void establecerTerminoBusqueda(String s){
        terminoBusqueda.setValue(s);
    }
}