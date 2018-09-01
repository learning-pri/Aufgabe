package com.eko.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eko.task.dao.NeftTransaction;

public interface AufgabeRepository extends JpaRepository<NeftTransaction,Long>{

	@Query("select nt.ekotrxnid from NeftTransaction nt where nt.ifscCode=':ifsccode'")
    List<NeftTransaction> findByIfsccode(String ifsccode);

}
