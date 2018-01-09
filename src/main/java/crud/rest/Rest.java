package crud.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import crud.dao.Dao;
import crud.modele.Eleve;

@Path("/eleves")
public class Rest {

	// OBTENIR LA LISTE DES ELEVES
	@GET
	@Path("totalEleves")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Eleve> getTousEleves() {
		Dao dao = new Dao();
		return dao.getTousEleves();
	}

	// OBTENIR l'ELEVE PAR ID
	@GET
	@Path("getEleveParId/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEleveParId(@PathParam("id") int id) {
		Dao dao = new Dao();
		Eleve eleve = dao.getEleveParId(id);
		if (eleve == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Eleve not found").build();
		}
		return Response.ok(eleve, MediaType.APPLICATION_JSON).build();
	}

	// VALIDER L'EDITION DE L'ELEVE
	@POST
	@Path("validerEdition")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validerEdition(Eleve eleve) {
		Dao dao = new Dao();
		if (!dao.validerEdition(eleve)) {
			return Response.status(Response.Status.NOT_FOUND).entity("Eleve not found").build();
		}
		return Response.ok(eleve, MediaType.APPLICATION_JSON).build();
	}

	// AJOUTER UN ELEVE
	@POST
	@Path("ajouterEleve")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Eleve ajouterEleve(Eleve eleve) {
		Dao dao = new Dao();
		dao.ajouterEleve(eleve);
		return eleve;
	}

	// SUPPRIMER UN ELEVE PAR ID
	@GET
	@Path("supprimerEleveParId/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int supprimerEleveParId(@PathParam("id") int id) {
		Dao dao = new Dao();
		dao.supprimerEleve(id);
		return 1;
	}

}