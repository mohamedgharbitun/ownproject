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
		private int id;
		
		@Column(name = "prenom")
		private String prenom;
		
		@Column(name="nom")
		private String nom;
		
		public Eleve() {
		}

		public Eleve(int id, String prenom, String nom) {
			this.id = id;
			this.prenom = prenom;
			this.nom = nom;
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
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

		@Override
		public String toString() {
			return "Employee: " + this.id + ", " + this.prenom + ", " + this.nom; 
		}
		
}
