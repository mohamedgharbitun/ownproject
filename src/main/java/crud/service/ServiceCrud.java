package crud.service;

import java.util.List;

import crud.modele.EleveDTO;

public interface ServiceCrud {

	public List<EleveDTO> getTousEleves();

	public EleveDTO findByID(Integer id);

	public Integer create(EleveDTO eleve);

	public void update(EleveDTO eleveDto);

	public void delete(Integer id);

	public void deleteAll();
}
