package crud.dao;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crud.modele.EleveDTO;

@Repository
public class DaoCrudImpl implements  DaoCrud {
	
	
	@Autowired
	private Mapper dozerBeanMapper;
	
	@Autowired
	private SessionFactory sessionFactory;
	

	public List<EleveDTO> getTousEleves() {

		Session session = getCurrentSession();
		List<Eleve> eleves = session.createQuery("FROM Eleve").list();

		List<EleveDTO> listElevesDTO = new ArrayList<EleveDTO>();
		for(Eleve eleve: eleves){
			listElevesDTO.add(dozerBeanMapper.map(eleve, EleveDTO.class));
		}
		return listElevesDTO;
	}
	
	public EleveDTO findByID(Integer id) {
		EleveDTO eleveDto = null;
		Session session = getCurrentSession();
		try {
			Eleve eleveDao = (Eleve) session.load(Eleve.class, id);
			eleveDto = dozerBeanMapper.map(eleveDao, EleveDTO.class);
		} catch (ObjectNotFoundException e) {
			return null;
		}
		return eleveDto;
	}
	
	public Integer create(EleveDTO eleve) {
		Session session = getCurrentSession();
		Eleve eleveDao = dozerBeanMapper.map(eleve, Eleve.class);
		session.save(eleveDao);
		return eleveDao.getId();

	}

	public void update(EleveDTO eleveDto) {
		Session session = getCurrentSession();
		Eleve eleveDao = dozerBeanMapper.map(eleveDto, Eleve.class);
		Eleve em = (Eleve) session.load(Eleve.class, eleveDao.getId());
		em.setNom(eleveDto.getNom());
		em.setPrenom(eleveDto.getPrenom());
		em.setDateNaissance(eleveDto.getDateNaissance());
	}

	public void delete(Integer id) {
		Eleve eleveDao = dozerBeanMapper.map(findByID(id), Eleve.class);
		
		if(eleveDao == null) {
			throw new HibernateException("Eleve non trouv�");
		}
		Session session = getCurrentSession();
		session.clear();
		session.delete(eleveDao);
	}

	public  void deleteAll() {
		Session session = getCurrentSession();
		Query query = session.createQuery("DELETE FROM Eleve ");
		query.executeUpdate();

	}
	
	protected Session getCurrentSession(){
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		return session;
	}
}
