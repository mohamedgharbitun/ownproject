package crud.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crud.connexion.Connexion;
import crud.modele.Eleve;
 
public class Dao {
 
        public List<Eleve> getTousEleves() {
 
            try {
             Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement("select * from eleves");
             List<Eleve> al = new ArrayList<Eleve>();
             ResultSet rs = ps.executeQuery();
             boolean found = false;
 
             while (rs.next()) {
                 Eleve e = new Eleve();
                 System.out.println(rs.getString("nom"));
                 e.setId(rs.getInt("id"));
                 e.setPrenom(rs.getString("prenom"));
                 e.setNom(rs.getString("nom"));
                 al.add(e);
                 found = true;
             }
             System.out.println(al);
             rs.close();
             if (found) {
                 return al;
             } else {
                 return null; // Pas de data trouvé
             }
             } catch (Exception e) {
             System.out.println("Erreur avec  getTousEleves() -->" + e.getMessage());
             return (null);
         }
        }
 
        public Eleve getEleveParId(int id) {
        	
        	if( id == 0)
        		return null;
 
            try {
	            Connection con = Connexion.getConnection();
	            PreparedStatement ps = con.prepareStatement("select * from eleves WHERE id = ?");
	            ps.setInt(1, id);
	            Eleve e = new Eleve();
	            ResultSet rs = ps.executeQuery();
	 
	            while (rs.next()) {
	 
	                e.setId(rs.getInt("id"));
	                e.setPrenom(rs.getString("prenom"));
	                e.setNom(rs.getString("nom"));
	 
	            }
	            rs.close();
	            return e;
 
            }catch (Exception e) {
                System.out.println("Erreur avec  getEleveParId() -->" + e.getMessage());
                return (null);
            }
        }
 
        public boolean validerEdition(Eleve eleve) {
 
            try {
                Connection con = Connexion.getConnection();
                
                if(getEleveParId(eleve.getId()) == null)
                {
                	return false;
                }
 
             // L'insert avec mysql
                String query = "UPDATE eleves SET prenom = ?, nom = ? WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString (1, eleve.getPrenom());
                ps.setString (2, eleve.getNom());
                ps.setInt (3, eleve.getId());
 
                ps.executeUpdate();
                ps.close();
                }catch (Exception e) {
                    System.out.println("Erreur avec validerEdition() -->" + e.getMessage());
            }
            return true;
        }
 
        public void ajouterEleve(Eleve eleve) {
 
            try {
                System.out.println("Ajout de l eleve avec le prenom: " + eleve.getPrenom());
            Connection con = Connexion.getConnection();
 
         // L'insert avec mysql
            String query = " INSERT INTO eleves (prenom, nom)" + " values (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString (1, eleve.getPrenom());
            ps.setString (2, eleve.getNom());
 
            ps.executeUpdate();
            ps.close();
            }catch (Exception e) {
                System.out.println("Erreur avec ajouterEleve() -->" + e.getMessage());
 
            }
        }
 
        public void supprimerEleve(int id) {
 
            try {
            Connection con = Connexion.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE from eleves WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            }catch (Exception e) {
                System.out.println("Error In supprimerEleveParId() -->" + e.getMessage());
 
            }
        }
}