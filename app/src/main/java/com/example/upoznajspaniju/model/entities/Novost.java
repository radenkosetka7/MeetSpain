package com.example.upoznajspaniju.model.entities;

import java.util.Objects;

public class Novost {

    private String naslov;
    private String slika;
    private String sadrzaj;
    private String url;

    public Novost() {
        super();
    }

    public Novost(String naslov, String slika, String sadrzaj, String url) {
        this.naslov = naslov;
        this.slika = slika;
        this.sadrzaj = sadrzaj;
        this.url = url;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Novost novost = (Novost) o;
        return Objects.equals(naslov, novost.naslov) && Objects.equals(slika, novost.slika) && Objects.equals(sadrzaj, novost.sadrzaj) && Objects.equals(url, novost.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naslov, slika, sadrzaj, url);
    }

    @Override
    public String toString() {
        return "Novost{" +
                "naslov='" + naslov + '\'' +
                ", slika='" + slika + '\'' +
                ", sadrzaj='" + sadrzaj + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
