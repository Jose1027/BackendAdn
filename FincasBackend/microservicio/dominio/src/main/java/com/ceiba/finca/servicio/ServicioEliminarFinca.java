package com.ceiba.finca.servicio;

import com.ceiba.finca.puerto.repositorio.RepositorioFinca;

public class ServicioEliminarFinca {

    private final RepositorioFinca repositorioFinca;

    public ServicioEliminarFinca(RepositorioFinca repositorioFinca) {
        this.repositorioFinca = repositorioFinca;
    }

    public void ejecutar(Long id) {
        this.repositorioFinca.eliminar(id);
    }
}
