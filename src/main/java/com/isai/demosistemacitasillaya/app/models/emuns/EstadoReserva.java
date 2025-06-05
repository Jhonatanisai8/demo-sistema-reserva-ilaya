package com.isai.demosistemacitasillaya.app.models.emuns;

public enum EstadoReserva {
    PENDIENTE("Pendiente"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada"),
    ASISTIO("Asistio");

    private final String displayName;

    EstadoReserva(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

}
