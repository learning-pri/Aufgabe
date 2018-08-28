package com.eko.task.repository;

import com.eko.task.dao.NeftTransaction;

public interface IAufgabeRepository {

	NeftTransaction getTransactionId(int transactionId);
	
//	@Query("select nt.ekotrxnid from NeftTransaction nt where nt.ifscCode='?'")
 //   List<NeftTransaction> findByIfsccode(String ifsccode);
}
