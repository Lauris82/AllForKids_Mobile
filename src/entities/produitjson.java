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
public class produitjson {
     private Integer IdProduit;
    private String NomProduit;
    private String TypeProduit;
    private Double PrixProduit;
    private String Description;
    private Double PrixPromo;
    private Integer IdPropriete;
    private String image;
    private Integer nbrlike;
    private Integer nbrdislike;

    public produitjson() {
    }

    public produitjson(Integer IdProduit, String NomProduit, String TypeProduit, Double PrixProduit, String Description, Double PrixPromo, Integer IdPropriete, String image, Integer nbrlike, Integer nbrdislike) {
        this.IdProduit = IdProduit;
        this.NomProduit = NomProduit;
        this.TypeProduit = TypeProduit;
        this.PrixProduit = PrixProduit;
        this.Description = Description;
        this.PrixPromo = PrixPromo;
        this.IdPropriete = IdPropriete;
        this.image = image;
        this.nbrlike = nbrlike;
        this.nbrdislike = nbrdislike;
    }

    public Integer getIdProduit() {
        return IdProduit;
    }

    public void setIdProduit(Integer IdProduit) {
        this.IdProduit = IdProduit;
    }

    public String getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String NomProduit) {
        this.NomProduit = NomProduit;
    }

    public String getTypeProduit() {
        return TypeProduit;
    }

    public void setTypeProduit(String TypeProduit) {
        this.TypeProduit = TypeProduit;
    }

    public Double getPrixProduit() {
        return PrixProduit;
    }

    public void setPrixProduit(Double PrixProduit) {
        this.PrixProduit = PrixProduit;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Double getPrixPromo() {
        return PrixPromo;
    }

    public void setPrixPromo(Double PrixPromo) {
        this.PrixPromo = PrixPromo;
    }

    public Integer getIdPropriete() {
        return IdPropriete;
    }

    public void setIdPropriete(Integer IdPropriete) {
        this.IdPropriete = IdPropriete;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNbrlike() {
        return nbrlike;
    }

    public void setNbrlike(Integer nbrlike) {
        this.nbrlike = nbrlike;
    }

    public Integer getNbrdislike() {
        return nbrdislike;
    }

    public void setNbrdislike(Integer nbrdislike) {
        this.nbrdislike = nbrdislike;
    }

    @Override
    public String toString() {
        return "produitjson{" + "IdProduit=" + IdProduit + ", NomProduit=" + NomProduit + ", TypeProduit=" + TypeProduit + ", PrixProduit=" + PrixProduit + ", Description=" + Description + ", PrixPromo=" + PrixPromo + ", IdPropriete=" + IdPropriete + ", image=" + image + ", nbrlike=" + nbrlike + ", nbrdislike=" + nbrdislike + '}';
    }
    
    

}
