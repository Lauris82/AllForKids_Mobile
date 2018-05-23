/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.raoudha.entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class Enfant 
{
    private int id_enfant;
    private String nom;
      private String prenom;
      private Date dateNaissance;
      private String sexe;
      private String categorie;
      private int user;
      private int garderie;

    public Enfant()
    {
    }

    public Enfant(String nom, String prenom, Date dateNaissance, String sexe, String categorie) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.categorie = categorie;
    }

    public int getId_enfant() {
        return id_enfant;
    }

    public void setId_enfant(int id_enfant) {
        this.id_enfant = id_enfant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getGarderie() {
        return garderie;
    }

    public void setGarderie(int garderie) {
        this.garderie = garderie;
    }
      
    
}
