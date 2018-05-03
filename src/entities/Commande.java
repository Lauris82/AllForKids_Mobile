/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author amine
 */
public class Commande {
   private Integer id;
   private String nom;      
   private Integer quantité;       
   private Integer user;
   private Integer idProduit;

    public Commande() {
    }

    public Commande(String nom, Integer quantité, Integer user, Integer idProduit) {
        
        this.nom = nom;
        this.quantité = quantité;
        this.user = user;
        this.idProduit = idProduit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getQuantité() {
        return quantité;
    }

    public void setQuantité(Integer quantité) {
        this.quantité = quantité;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", nom=" + nom + ", quantit\u00e9=" + quantité + ", user=" + user + ", idProduit=" + idProduit + '}';
    }
   
   
   
   
   
   
   
            }

