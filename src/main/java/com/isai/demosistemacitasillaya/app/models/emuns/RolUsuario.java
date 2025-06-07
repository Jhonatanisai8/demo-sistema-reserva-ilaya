package com.isai.demosistemacitasillaya.app.models.emuns;

public enum RolUsuario {
    ADMIN("Administrador"),
    CLIENTE("Cliente");
    private final String displayName;

    RolUsuario(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
