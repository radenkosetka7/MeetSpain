package com.example.upoznajspaniju.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grad {

    private String naziv;
    private String opis;
    private String video;
    private List<String> slike=new ArrayList<>();
    private Double latitude;
    private Double longitude;

    public Grad() {
        super();
    }

    public Grad(String naziv, String opis, String video_url,Double latitude,Double longitude) {
        this.naziv = naziv;
        this.opis = opis;
        this.video = video_url;
        this.latitude=latitude;
        this.longitude=longitude;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<String> getSlike() {
        return slike;
    }

    public void setSlike(List<String> slike) {
        this.slike = slike;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grad grad = (Grad) o;
        return Objects.equals(naziv, grad.naziv) && Objects.equals(opis, grad.opis) && Objects.equals(video, grad.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, opis, video);
    }

    @Override
    public String toString() {
        return "Grad{" +
                "naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
