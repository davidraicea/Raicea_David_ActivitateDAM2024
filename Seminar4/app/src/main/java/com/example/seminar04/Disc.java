package com.example.seminar04;

import android.view.View;

public class Disc {
    private String nume;
    private float raza;
    private float diametru;

    private String vechime;

    private boolean important;

    private double arie;

    public Disc(String nume, float raza,boolean important,String vechime) {
        this.nume = nume;
        this.raza = raza;
        this.vechime = vechime;
        this.diametru = 2*raza;
        this.arie = Math.PI * raza * raza;
        this.important = important;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getRaza() {
        return raza;
    }

    public void setRaza(float raza) {
        this.raza = raza;
    }

    public float getDiametru() {
        return diametru;
    }

    public void setDiametru(float diametru) {
        this.diametru = diametru;
    }

    public double getArie() {
        return arie;
    }

    public void setArie(double arie) {
        this.arie = arie;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Disc{");
        sb.append("nume='").append(nume).append('\'');
        sb.append(", raza=").append(raza);
        sb.append(", diametru=").append(diametru);
        sb.append(", vechime='").append(vechime).append('\'');
        sb.append(", important=").append(important);
        sb.append(", arie=").append(arie);
        sb.append('}');
        return sb.toString();
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public String getVechime() {
        return vechime;
    }

    public void setVechime(String vechime) {
        this.vechime = vechime;
    }


}
