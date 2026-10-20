package com.example.seminar4_exercitiu;

import java.io.Serializable;

public class Avion implements Serializable {
    private String model;
    private String companie;
    private int capacitate;
    private float rating;
    private boolean disponibil;

    public Avion(String model, String companie, int capacitate, float rating, boolean disponibil) {
        this.model = model;
        this.companie = companie;
        this.capacitate = capacitate;
        this.rating = rating;
        this.disponibil = disponibil;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Avion{");
        sb.append("model='").append(model).append('\'');
        sb.append(", companie='").append(companie).append('\'');
        sb.append(", capacitate=").append(capacitate);
        sb.append(", rating=").append(rating);
        sb.append(", disponibil=").append(disponibil);
        sb.append('}');
        return sb.toString();
    }
}
