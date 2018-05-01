/**
* @Project: AllForKids_Mobile
* @Classe: OffreTransport
* @Date: 1 mai 2018
* @Time: 12:05:32
*
* @author Lauris
*/


package com.allforkids.Ettien.entities;

import java.util.Date;


public class OffreTransport {
    
    private Integer id;
    private Integer user;
    private String description;
    private String destination;
    private String date_debut;
    private String date_fin;
    private Integer nombre_place;
    private Integer place_restant;
    private Double prix;

    public OffreTransport() {}
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public Integer getNombre_place() {
        return nombre_place;
    }

    public void setNombre_place(Integer nombre_place) {
        this.nombre_place = nombre_place;
    }

    public Integer getPlace_restant() {
        return place_restant;
    }

    public void setPlace_restant(Integer place_restant) {
        this.place_restant = place_restant;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public int hashCode() {
        return id*user*date_fin.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OffreTransport other = (OffreTransport) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OffreTransport{" + "id=" + id + ", user=" + user + ", description=" + description + ", destination=" + destination + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", nombre_place=" + nombre_place + ", place_restant=" + place_restant + ", prix=" + prix + '}';
    }
    
}



/**
*@Lau82 © 2018
*/
