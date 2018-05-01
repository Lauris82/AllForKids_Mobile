/**
* @Project: AllForKids_Mobile
* @Classe: Reservation_Offre
* @Date: 1 mai 2018
* @Time: 16:17:30
*
* @author Lauris
*/


package com.allforkids.Ettien.entities;

import java.util.Date;


public class Reservation_Offre {
    private Integer id;
    private Integer user;
    private Integer nombreEnfants;
    private Date date_reservation;
    private Integer offreTransport;
    private Integer etat;

    public Reservation_Offre() {}

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

    public Integer getNombreEnfants() {
        return nombreEnfants;
    }

    public void setNombreEnfants(Integer nombreEnfants) {
        this.nombreEnfants = nombreEnfants;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public Integer getOffreTransport() {
        return offreTransport;
    }

    public void setOffreTransport(Integer offreTransport) {
        this.offreTransport = offreTransport;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reservation_Offre{" + "id=" + id + ", user=" + user + ", nombreEnfants=" + nombreEnfants + ", date_reservation=" + date_reservation + ", offreTransport=" + offreTransport + ", etat=" + etat + '}';
    }

    @Override
    public int hashCode() {
        return id*user*date_reservation.hashCode();
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
        final Reservation_Offre other = (Reservation_Offre) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}



/**
*@Lau82 © 2018
*/
