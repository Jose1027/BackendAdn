package com.ceiba.finca.servicio;


import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.finca.modelo.entidad.Finca;
import com.ceiba.finca.puerto.repositorio.RepositorioFinca;

public class ServicioCrearFinca {

    private static final String LA_FINCA_YA_EXISTE_EN_EL_SISTEMA = "La finca ya existe en el sistema";

    private final RepositorioFinca repositorioFinca;

    public ServicioCrearFinca(RepositorioFinca repositorioFinca) {
        this.repositorioFinca = repositorioFinca;
    }

    public Long ejecutar(Finca finca) {
        validarExistenciaPrevia(finca);
        return this.repositorioFinca.crear(finca);
    }

    private void validarExistenciaPrevia(Finca finca) {
        boolean existe = this.repositorioFinca.existe(finca.getNombre());
        if (existe) {
            throw new ExcepcionDuplicidad(LA_FINCA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
