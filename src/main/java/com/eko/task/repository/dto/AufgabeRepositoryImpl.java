package com.eko.task.repository.dto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eko.task.dao.NeftTransaction;
import com.eko.task.repository.AufgabeRepository;
import com.eko.task.repository.IAufgabeRepository;

@Repository
public class AufgabeRepositoryImpl implements IAufgabeRepository{

//	@Autowired
//	AufgabeRepository aufgabeRepository;

	@PersistenceContext
	protected EntityManager em;

	private SessionFactory sessionFactory;
	
	 @Autowired
	  public AufgabeRepositoryImpl(EntityManagerFactory factory) {
	    if(factory.unwrap(SessionFactory.class) == null){
	      throw new NullPointerException("factory is not a hibernate factory");
	    }
	    this.sessionFactory = factory.unwrap(SessionFactory.class);
	  }
	 
	public NeftTransaction getTransactionId(int transactionId) {

		System.out.println("TransactionRepositoryImpl.getEkoTID()");
		Criteria criteria = em.unwrap(Session.class).createCriteria(NeftTransaction.class)
				.add(Restrictions.eq("ekotrxnid", transactionId));
			  return (NeftTransaction) criteria.uniqueResult();
	}

//	public List<NeftTransaction> findByIfsccode(String ifsccode) {
//		
//	    List<NeftTransaction> cities = (List<NeftTransaction>) aufgabeRepository.findByIfsccode(ifsccode);
//        return cities;
//	}
	
	

}
