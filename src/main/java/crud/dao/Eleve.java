package crud.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "eleves")
	public class Eleve {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "prenom")
		private String prenom;
		
		@Column(name="nom")
		private String nom;
		
		@Column(name="datenaissance")
		private String dateNaissance;
		
		public Eleve() {
		}

		public Eleve(Integer id, String prenom, String nom, String dateNaissance) {
			this.id = id;
			this.prenom = prenom;
			this.nom = nom;
			this.dateNaissance = dateNaissance;
		}
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}
		
		public String getDateNaissance() {
			return dateNaissance;
		}

		public void setDateNaissance(String dateNaissance) {
			this.dateNaissance = dateNaissance;
		}

		@Override
		public String toString() {
			return "Employee: " + this.id + ", " + this.prenom + ", " + this.nom + ", " + this.dateNaissance; 
		}
		
}
