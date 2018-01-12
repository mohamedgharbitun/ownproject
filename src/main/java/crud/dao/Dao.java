package crud.dao;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	public List<EleveDTO> read() {
		Session session = getSessionFactory().openSession();

		List<Eleve> eleves = session.createQuery("FROM Eleve").list();

		List<EleveDTO> listElevesDTO = new ArrayList<EleveDTO>();
		for(Eleve eleve: eleves){
			listElevesDTO.add(dozerBeanMapper.map(eleve, EleveDTO.class));
		}

		session.close();
		return listElevesDTO;
	}
	
	public  EleveDTO findByID(Integer id) {
		Session session = getSessionFactory().openSession();
		EleveDTO e = (EleveDTO) session.load(EleveDTO.class, id);
		session.close();
		return e;
	}
	
	public Integer create(EleveDTO e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + e.toString());
		return e.getId();

	}

	public void update(EleveDTO e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		EleveDTO em = (EleveDTO) session.load(EleveDTO.class, e.getId());
		em.setNom(e.getNom());
		em.setPrenom(e.getPrenom());
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully updated " + e.toString());

	}

	public  void delete(Integer id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		EleveDTO e = findByID(id);
		session.delete(e);
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