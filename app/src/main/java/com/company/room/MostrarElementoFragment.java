package com.company.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.company.room.databinding.FragmentMostrarElementoBinding;
import com.squareup.picasso.Picasso;


public class MostrarElementoFragment extends Fragment {
    private FragmentMostrarElementoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarElementoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ElementosViewModel elementosViewModel = new ViewModelProvider(requireActivity()).get(ElementosViewModel.class);

        elementosViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Elemento>() {
            @Override
            public void onChanged(Elemento elemento) {
                binding.nombre.setText(elemento.nombre);
                binding.precio.setText(elemento.precio+"€");
                binding.descripcion.setText(elemento.descripcion);
                binding.valoracion.setRating(elemento.valoracion);
                Picasso.get().load(elemento.URL).into(binding.imagen);

                binding.valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        if(fromUser){
                            elementosViewModel.actualizar(elemento, rating);
                        }
                    }
                });

            }
        });
    }
}