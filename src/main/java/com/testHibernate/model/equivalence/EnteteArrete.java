package com.testHibernate.model.equivalence;
 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class EnteteArrete {
	
	
	public EnteteArrete() {
		super();
	}

	public EnteteArrete(Long id, ArreteEqRef arreteEqRef, byte[] logo, String titreGauche, String titreDroite) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.logo = logo;
		this.titreGauche = titreGauche;
		this.titreDroite = titreDroite;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ArreteEqRef arreteEqRef;
		
	@Lob
	private byte[] logo ;
		
	private String titreGauche ;
	private String titreDroite ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ArreteEqRef getArreteEqRef() {
		return this.arreteEqRef;
	}
	public void setArreteEqRef(ArreteEqRef arreteEqRef) {
		this.arreteEqRef = arreteEqRef;
	}
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	public String getTitreGauche() {
		return titreGauche;
	}
	public void setTitreGauche(String titreGauche) {
		this.titreGauche = titreGauche;
	}
	public String getTitreDroite() {
		return titreDroite;
	}
	public void setTitreDroite(String titreDroite) {
		this.titreDroite = titreDroite;
	}
	
	
		
}