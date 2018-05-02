/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yassine.entities;












/**
 *
 * @author ASUS
 */
public class evenement {
     public int id_evenement ;
 public int evenement_user;
 public String nom ;
   public String description;
    public String date_debut ;
    public String date_fin ;
    public String emplacement ;
    public String image ;
    public int etat ;
public int nbr_place ;

    public evenement() {
        
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getEvenement_user() {
        return evenement_user;
    }

    public void setEvenement_user(int evenement_user) {
        this.evenement_user = evenement_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


  

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    @Override
    public String toString() {
        return "evenement{" + "id_evenement=" + id_evenement + ", evenement_user=" + evenement_user + ", nom=" + nom + ", description=" + description + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", emplacement=" + emplacement + ", image=" + image + ", etat=" + etat + ", nbr_place=" + nbr_place + '}';
    }

}
