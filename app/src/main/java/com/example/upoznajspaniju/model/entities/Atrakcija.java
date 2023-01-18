package com.example.upoznajspaniju.model.entities;

import java.util.Objects;

public class Atrakcija {

    private Integer id;
    private String ime;
    private String opis;
    private String slika;
    private Integer omiljena;
    private Double latitude;
    private Double longitude;


    public Atrakcija() {
        super();
    }

    public Atrakcija(String ime, String opis, String slika, Integer omiljena,Double latitude,Double longitude) {
        this.ime = ime;
        this.opis = opis;
        this.slika = slika;
        this.omiljena = omiljena;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Integer getOmiljena() {
        return omiljena;
    }

    public void setOmiljena(Integer omiljena) {
        this.omiljena = omiljena;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atrakcija atrakcija = (Atrakcija) o;
        return Objects.equals(ime, atrakcija.ime) && Objects.equals(opis, atrakcija.opis) && Objects.equals(slika, atrakcija.slika) && Objects.equals(omiljena, atrakcija.omiljena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, opis, slika, omiljena);
    }

    @Override
    public String toString() {
        return "Atrakcija{" +
                "ime='" + ime + '\'' +
                ", opis='" + opis + '\'' +
                ", slika='" + slika + '\'' +
                ", omiljena=" + omiljena +
                '}';
    }
}
