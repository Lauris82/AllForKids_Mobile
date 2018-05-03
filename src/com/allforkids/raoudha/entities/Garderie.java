/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.raoudha.entities;


/**
 *
 * @author user
 */
public class Garderie
{
    private int id_garderie;
    private int user_garderie;
    private String nom;
    private String emplacement;
    private String description;
    private int capacite;
    private int num_tel;
    private String nomImage ;

    public Garderie() {
    }

    public Garderie(String nom, String emplacement, String description, int capacite, int num_tel, String nomImage) {
        this.nom = nom;
        this.emplacement = emplacement;
        this.description = description;
        this.capacite = capacite;
        this.num_tel = num_tel;
        this.nomImage = nomImage;
    }

    public int getId_garderie() {
        return id_garderie;
    }

    public void setId_garderie(int id_garderie) {
        this.id_garderie = id_garderie;
    }

    public int getUser_garderie() {
        return user_garderie;
    }

    public void setUser_garderie(int user_garderie) {
        this.user_garderie = user_garderie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    @Override
    public String toString() {
        return "Garderie{" + "nom=" + nom + ", emplacement=" + emplacement + ", description=" + description + ", capacite=" + capacite + ", num_tel=" + num_tel + '}';
    }

   
  
    
}
