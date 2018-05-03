/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author amine
 */
public class Commentaire {
    String contenu;
    Date date;

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Commentaire() {
    }

    public Commentaire(String contenu, Date date) {
        this.contenu = contenu;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "contenu=" + contenu + ", date=" + date + '}';
    }
    
}
