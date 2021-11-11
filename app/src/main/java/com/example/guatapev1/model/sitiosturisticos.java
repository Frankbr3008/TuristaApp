package com.example.guatapev1.model;

public class sitiosturisticos {

    String placeName;
    String detalle;

    public sitiosturisticos(String placeName, String detalle) {
        this.placeName = placeName;
        this.detalle = detalle;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
