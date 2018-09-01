package com.eko.task.service;

import java.util.List;

import com.eko.task.dao.NeftTransaction;

public interface IAufgabeService {

	NeftTransaction getTransactionId(int transactionId);
	
//	@Query("select nt.ekotrxnid from NeftTransaction nt where nt.ifscCode='?1'")
    List<NeftTransaction> findByIfsccode(String ifsccode);
}
