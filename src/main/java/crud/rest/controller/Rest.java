package crud.rest.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import crud.dao.Dao;
import crud.modele.EleveDTO;


@RestController
@RequestMapping("/eleves")
public class Rest {
	
	@Autowired
	private Dao dao;

	@RequestMapping(method=RequestMethod.GET, value="/totalEleves", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTousEleves() {
		List<EleveDTO> result = dao.read();
		return ResponseEntity.ok(result);
	}



	@RequestMapping(method=RequestMethod.GET, value="/getEleveParId/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public EleveDTO getEleveParId(@RequestParam(value="id") int id) {
		EleveDTO eleve = dao.findByID(id);
		return eleve;
	}

	
	// TODO
	@RequestMapping(method=RequestMethod.POST,value="/validerEdition",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> validerEdition(EleveDTO eleve) {
		try {
			dao.update(eleve);
		} catch(HibernateException e){
			return ResponseEntity.ok().build();
		}
			return ResponseEntity.ok().build();
	}


	@RequestMapping(method = RequestMethod.POST, value ="/ajouterEleve", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public EleveDTO ajouterEleve(EleveDTO eleve) {
		dao.create(eleve);
		return eleve;
	}


	@RequestMapping(method=RequestMethod.GET, value="/supprimerEleveParId/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public int supprimerEleveParId(@RequestParam(value="id") int id) {
		dao.delete(id);
		return 1;
	}

}