package com.example.upoznajspaniju.model.entities;

import java.util.Objects;

public class Drzava {

    private String naziv;
    private String opis;
    private String zastava;
    private String glavni_grad;

    public Drzava() {
        super();
    }

    public Drzava(String naziv, String opis, String zastava, String glavni_grad) {
        this.naziv = naziv;
        this.opis = opis;
        this.zastava = zastava;
        this.glavni_grad = glavni_grad;
    }

    public String getGlavni_grad() {
        return glavni_grad;
    }

    public void setGlavni_grad(String glavni_grad) {
        this.glavni_grad = glavni_grad;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getZastava() {
        return zastava;
    }

    public void setZastava(String zastava) {
        this.zastava = zastava;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drzava drzava = (Drzava) o;
        return Objects.equals(naziv, drzava.naziv) && Objects.equals(opis, drzava.opis) && Objects.equals(zastava, drzava.zastava);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, opis, zastava);
    }

    @Override
    public String toString() {
        return "Drzava{" +
                "naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", zastava='" + zastava + '\'' +
                '}';
    }
}
