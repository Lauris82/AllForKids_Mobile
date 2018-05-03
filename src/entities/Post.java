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
public class Post {
    
  private Integer id_post;
  private Integer post_user;
  private String titre;
  private String contenu;
  public Date created_at;
  private String image;
  private Integer nmbrsignal;

    public Post() {
    }

    public Post(Integer id_post, Integer post_user, String titre, String contenu, Date created_at, String image, Integer nmbrsignal) {
        this.id_post = id_post;
        this.post_user = post_user;
        this.titre = titre;
        this.contenu = contenu;
        this.created_at = created_at;
        this.image = image;
        this.nmbrsignal = nmbrsignal;
    }

    public Integer getId_post() {
        return id_post;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public Integer getPost_user() {
        return post_user;
    }

    public void setPost_user(Integer post_user) {
        this.post_user = post_user;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNmbrsignal() {
        return nmbrsignal;
    }

    public void setNmbrsignal(Integer nmbrsignal) {
        this.nmbrsignal = nmbrsignal;
    }

    @Override
    public String toString() {
        return "Post{" + "id_post=" + id_post + ", post_user=" + post_user + ", titre=" + titre + ", contenu=" + contenu + ", created_at=" + created_at + ", image=" + image + ", nmbrsignal=" + nmbrsignal + '}';
    }
  
  
  
    
}
