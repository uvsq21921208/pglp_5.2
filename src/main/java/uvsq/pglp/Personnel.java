package uvsq.pglp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Personnel class.
 * @author Mouttie Classe personnel.
 */
public final class Personnel extends TypePersonnel implements Serializable {
 
  private static final long serialVersionUID = 1L;
  /**
  * Le nom d'un personnel.
  */
  private String nom;

  /**
  * Le prenom d'un personnel.
  */
  private String prenom;
  /**
  * Le numero du telephone.
  */
  private ArrayList<String> telephones;
  /**
  * date de naissance.
  */
  private LocalDate dateDeNaissance;
  /**
  * Fonction.
  */
  private String fonction;
  /**
  * personnel builder.
  */

  public static class PersonelBuilder {
    /**
    * Le nom d'un personnel.
    */

    private String nom;
    /**
    * Le prenom d'un personnel.
    */

    private String prenom;
    /**
    * Le numero du telephone.
    */
    private ArrayList<String> telephones = null;
    /**
    * date de naissance.
    */

    private LocalDate dateDeNaissance = null;
    /**
    * Fonction.
    */

    private String fonction;
    /**
    * PersonnelBUilder.
    * @param nomPara nom.
    * @param prenomPara prenom.
    * @param fonctionPara fonction.
    */

    public PersonelBuilder(final String nomPara,
             final String prenomPara, final String fonctionPara) {
      this.nom = nomPara;
      this.prenom = prenomPara;
      this.fonction = fonctionPara;
    }
    
    /**
    * setter for telephones.
    * @param telephones the telephones to set
    */

    private void setTelephones(ArrayList<String> telephones) {
      this.telephones = telephones;
    }
     
    /**
    * builder for birthdate.
    * @param datePara date de naissance.
    * @return instance.
    */

    public PersonelBuilder dateDeNaissance(final LocalDate datePara) {
      this.setDateDeNaissance(datePara);
      return this;
    }

    /**
    * telephone builder.
    * @param phonePara phone.
    * @return instance.
    */
    public PersonelBuilder telephone(final String phonePara) {
      this.setTelephones(new ArrayList<String>());
      this.telephones.add(phonePara);
      return this;
    }
    /**
    * personnel.
    * @return instance.
    */

    public Personnel build() {
      return new Personnel(this);
    }

    /**
    * getter for date de naissance.
    * @return the dateDeNaissance
    */

    public LocalDate getDateDeNaissance() {
      return dateDeNaissance;
    }

    /**
    * setter for date de naissance.
    * @param dateDeNaissance the dateDeNaissance to set
    */
    public void setDateDeNaissance(LocalDate dateDeNaissance) {
      this.dateDeNaissance = dateDeNaissance;
    }

    public ArrayList<String> getTelephones() {
      return this.telephones;
    }

  }
  /**
  * private constructor.
  * @param builder builder object.
  */

  private Personnel(final PersonelBuilder builder) {
    this.nom = builder.nom;
    this.prenom = builder.prenom;
    this.fonction = builder.fonction;
    setDateDeNaissance(builder.getDateDeNaissance());
    setTelephones(builder.getTelephones());
  }
  /**
  * getter for nom.
  * @return nom.
  */

  public String getNom() {
    return nom;
  }
  /**
  * getter for prenom.
  * @return prenom.
  */

  public String getPrenom() {
    return prenom;
  }
  /**
  * getter for telephones.
  * @return List des telephones.
  */

  public ArrayList<String> getTelephones() {
    return telephones;
  }

  /**
  * getter for date de naissance.
  * @return date de naissance.
  */

  public LocalDate getDateDeNaissance() {
    return dateDeNaissance;
  }

  /**
  * setter for telephone.
  * @param telephones the telephones to set
  */

  private void setTelephones(ArrayList<String> telephones) {
    this.telephones = telephones;
  }
  /**
  * setter for date de naissance.
  * @param dateDeNaissance the dateDeNaissance to set
  */
  
  private void setDateDeNaissance(LocalDate dateDeNaissance) {
    this.dateDeNaissance = dateDeNaissance;
  }
  /**
  * getter for fonction.
  * @return fonction.
  */

  public String getFonction() {
    return fonction;
  }
  /**
  * check if a groupe or not.
  * @return groupe ou pas.
  */

  @Override
  public boolean isGroupe() {
    return false;
  }
  /** 
  * returns hashcode of elements.
  * @return hashcode.
  */
  
  @Override
  public int hashCode() {
    return Objects.hash(fonction, nom, prenom);
  }
  
  /** 
  * check if two objects are equal.
  */
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Personnel)) {
      return false;
    }
    Personnel other = (Personnel) obj;
    return Objects.equals(fonction, other.fonction) && Objects.equals(nom, other.nom)
       && Objects.equals(prenom, other.prenom);
  }
   

}
