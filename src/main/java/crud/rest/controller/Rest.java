package crud.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import crud.dao.Dao;
import crud.modele.EleveDTO;


@RestController
@RequestMapping(value ="/eleves")
public class Rest {
	
	@Autowired
	private Dao dao;

	@RequestMapping(method=RequestMethod.GET, value="/totalEleves")
	public ResponseEntity<List<EleveDTO>> getTousEleves() {
		List<EleveDTO> result = dao.getTousEleves();
		
		if(result.isEmpty()){
            return new ResponseEntity<List<EleveDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<EleveDTO>>(result, HttpStatus.OK);
	}


	@RequestMapping(method=RequestMethod.GET, value="/getEleveParId/{id}")
	public ResponseEntity<EleveDTO> getEleveParId(@PathVariable("id") Integer id) {
		EleveDTO eleve = dao.findByID(id);
		
		if (eleve == null) {
              return new ResponseEntity<EleveDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EleveDTO>(eleve, HttpStatus.OK);
	}

	
	@RequestMapping(method=RequestMethod.POST,value="/validerEdition")
	public ResponseEntity<?> validerEdition( @RequestBody EleveDTO eleve) {
		EleveDTO eleveDto = dao.findByID(eleve.getId());
		
		if (eleveDto == null) {
            System.out.println("Eleve with id " + eleve.getId() + " not found");
            return new ResponseEntity<EleveDTO>(HttpStatus.NOT_FOUND);
        }
		
		eleveDto.setNom(eleve.getNom());
		eleveDto.setPrenom(eleve.getPrenom());
		
		dao.update(eleveDto);
		return new ResponseEntity<EleveDTO>(eleveDto, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.POST, value ="/ajouterEleve")
	public ResponseEntity<EleveDTO> ajouterEleve(@RequestBody EleveDTO eleve) {
		dao.create(eleve);
		return ResponseEntity.ok(eleve);
	}


	@RequestMapping(method=RequestMethod.DELETE, value="/supprimerEleveParId/{id}")
	public ResponseEntity<EleveDTO> supprimerEleveParId(@PathVariable(value="id") Integer id) {
		
		EleveDTO eleveDto = dao.findByID(id);
        if (eleveDto == null) {
            System.out.println("Unable to delete. Eleve with id " + id + " not found");
            return new ResponseEntity<EleveDTO>(HttpStatus.NOT_FOUND);
        }
		
		dao.delete(id);
		return new ResponseEntity<EleveDTO>(HttpStatus.NO_CONTENT);
	}

}