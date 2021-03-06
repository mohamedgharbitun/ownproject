package crud.rest.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import crud.modele.EleveDTO;
import crud.service.ServiceCrud;


@RestController
@RequestMapping(value ="/eleves")
public class Rest {
	
	@Autowired
	private ServiceCrud serviceCrud;
	
	// To get all beans definitions
//	@Autowired
//    private ApplicationContext applicationContext;

	@RequestMapping(method=RequestMethod.GET, value="/totalEleves", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EleveDTO>> getTousEleves() {
		List<EleveDTO> result = serviceCrud.getTousEleves();
		
		if(result.isEmpty()){
            return new ResponseEntity<List<EleveDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<EleveDTO>>(result, HttpStatus.OK);
	}


	@RequestMapping(method=RequestMethod.GET, value="/getEleveParId/{id}")
	public ResponseEntity<EleveDTO> getEleveParId(@PathVariable("id") Integer id) {
		EleveDTO eleve = serviceCrud.findByID(id);
		
		if (eleve == null) {
              return new ResponseEntity<EleveDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EleveDTO>(eleve, HttpStatus.OK);
	}

	
	@RequestMapping(method=RequestMethod.POST,value="/validerEdition")
	public ResponseEntity<?> validerEdition( @RequestBody EleveDTO eleve) {
		EleveDTO eleveDto = serviceCrud.findByID(eleve.getId());
		
		if (eleveDto == null) {
            System.out.println("Eleve with id " + eleve.getId() + " not found");
            return new ResponseEntity<EleveDTO>(HttpStatus.NOT_FOUND);
        }
		
		eleveDto.setId(eleve.getId());
		eleveDto.setNom(eleve.getNom());
		eleveDto.setPrenom(eleve.getPrenom());
		eleveDto.setDateNaissance(eleve.getDateNaissance());
		
		serviceCrud.update(eleveDto);
		return new ResponseEntity<EleveDTO>(eleveDto, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.POST, value ="/ajouterEleve")
	public ResponseEntity<EleveDTO> ajouterEleve(@RequestBody EleveDTO eleve) {
		serviceCrud.create(eleve);
		return ResponseEntity.ok(eleve);
	}


	@RequestMapping(method=RequestMethod.DELETE, value="/supprimerEleveParId/{id}")
	public ResponseEntity<?> supprimerEleveParId(@PathVariable(value="id") Integer id) {
		
        try {
        	serviceCrud.delete(id);
        }catch (HibernateException e) {
        	return new ResponseEntity<EleveDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EleveDTO>(HttpStatus.OK);
	}

}