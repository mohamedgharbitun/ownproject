package crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crud.dao.DaoCrud;
import crud.modele.EleveDTO;

@Service("crudService")
@Transactional

public class ServiceCrudImpl  implements ServiceCrud {
	
	@Autowired
	private DaoCrud daoCrud;
	
	public List<EleveDTO> getTousEleves(){
		return daoCrud.getTousEleves();
	}

	public EleveDTO findByID(Integer id){
		return daoCrud.findByID(id);
	}

	public Integer create(EleveDTO eleve){
		return daoCrud.create(eleve);
	}

	public void update(EleveDTO eleveDto) {
		daoCrud.update(eleveDto);
	}

	public void delete(Integer id) {
		daoCrud.delete(id);
	}

	public void deleteAll() {
		daoCrud.deleteAll();
	}

}
