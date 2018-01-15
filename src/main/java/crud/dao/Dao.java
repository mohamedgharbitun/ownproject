package crud.dao;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crud.modele.EleveDTO;

@Repository
public class Dao {
	
	
	@Autowired
	private Mapper dozerBeanMapper;
	
	public SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public List<EleveDTO> getTousEleves() {
		Session session = getSessionFactory().openSession();

		List<Eleve> eleves = session.createQuery("FROM Eleve").list();

		List<EleveDTO> listElevesDTO = new ArrayList<EleveDTO>();
		for(Eleve eleve: eleves){
			listElevesDTO.add(dozerBeanMapper.map(eleve, EleveDTO.class));
		}

		session.close();
		return listElevesDTO;
	}
	
	public EleveDTO findByID(Integer id) {
		Session session = getSessionFactory().openSession();
		EleveDTO eleveDto = null;
		try {
			Eleve e = (Eleve) session.load(Eleve.class, id);
			eleveDto = dozerBeanMapper.map(e, EleveDTO.class);
		} catch (ObjectNotFoundException e) {
			return null;
		} finally {
			session.close();
		}
		return eleveDto;
	}
	
	public Integer create(EleveDTO eleve) {
		Eleve eleveDao = dozerBeanMapper.map(eleve, Eleve.class);
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(eleveDao);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + eleveDao.toString());
		return eleveDao.getId();

	}

	public void update(EleveDTO eleveDto) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Eleve eleveDao = dozerBeanMapper.map(eleveDto, Eleve.class);
		Eleve em = (Eleve) session.load(Eleve.class, eleveDao.getId());
		em.setNom(eleveDto.getNom());
		em.setPrenom(eleveDto.getPrenom());
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully updated " + eleveDto.toString());
	}

	public void delete(Integer id) {
		Eleve eleveDao = dozerBeanMapper.map(findByID(id), Eleve.class);
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		EleveDTO e = findByID(id);
		session.delete(eleveDao);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully deleted " + e.toString());

	}

	public  void deleteAll() {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("DELETE FROM Eleve ");
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully deleted all Eleves.");

	}
}