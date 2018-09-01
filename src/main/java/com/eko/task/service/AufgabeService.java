package com.eko.task.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eko.task.dao.NeftTransaction;
import com.eko.task.repository.AufgabeRepository;

@Service
public class AufgabeService implements IAufgabeService {

	@Autowired
	private AufgabeRepository aufgabeService;

	@PersistenceContext
	protected EntityManager em;

	// private SessionFactory sessionFactory;
	//
	// @Autowired
	// public AufgabeService(EntityManagerFactory factory) {
	// if(factory.unwrap(SessionFactory.class) == null){
	// throw new NullPointerException("factory is not a hibernate factory");
	// }
	// this.sessionFactory = factory.unwrap(SessionFactory.class);
	// }

	public NeftTransaction getTransactionId(int transactionId) {

		System.out.println("TransactionRepositoryImpl.getEkoTID()");
		Criteria criteria = em.unwrap(Session.class).createCriteria(NeftTransaction.class)
				.add(Restrictions.eq("ekotrxnid", transactionId));
		return (NeftTransaction) criteria.uniqueResult();
	}

	public List<NeftTransaction> findByIfsccode(String ifsccode) {
		System.out.println("ISCCODE  ::  "+ifsccode);
		List<NeftTransaction> list = (List<NeftTransaction>) aufgabeService.findByIfsccode(ifsccode);
		return list;
		// String queryStr = "select nt.ekotrxnid from NeftTransaction nt where
		// nt.ifscCode='?'";
		// try {
		// Query query = em.createNativeQuery(queryStr);
		// query.setParameter(1, ifsccode);
		//
		// return (List<NeftTransaction>) query.getSingleResult();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// try {
		// throw e;
		// } catch (Exception e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// }
		// return null;

	}

}
