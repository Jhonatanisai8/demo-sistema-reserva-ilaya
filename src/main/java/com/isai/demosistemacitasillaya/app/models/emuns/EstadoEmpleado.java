package com.isai.demosistemacitasillaya.app.models.emuns;

public enum EstadoEmpleado {
    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    LICENCIA("De Licencia");

    private final String displayName;

    EstadoEmpleado(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
