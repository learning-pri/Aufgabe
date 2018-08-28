package com.eko.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eko.task.dao.NeftTransaction;

public interface AufgabeRepository extends JpaRepository<NeftTransaction,Long>, IAufgabeRepository{

//	@Query("select nt.ekotrxnid from NeftTransaction nt where nt.ifscCode='?'")
//    List<NeftTransaction> findByIfsccode(String ifsccode);
}
