package com.example.seminar04;

public class Situatie {
    private String disciplina;
    private String activitate;
    private int valoare;

    public Situatie(String disciplina, String activitate, int valoare) {
        this.disciplina = disciplina;
        this.activitate = activitate;
        this.valoare = valoare;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Situatie{");
        sb.append("disciplina='").append(disciplina).append('\'');
        sb.append(", activitate='").append(activitate).append('\'');
        sb.append(", valoare=").append(valoare);
        sb.append('}');
        return sb.toString();
    }
}
