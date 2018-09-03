package com.eko.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eko.task.dao.NeftTransaction;
import com.eko.task.service.IAufgabeService;

@RestController
@RequestMapping("/")
public class AufgabeController {

	@Autowired
//	AufgabeRepository repository;
	IAufgabeService service;

	@RequestMapping(method = RequestMethod.GET)
	public String welcome() {
		return "Hello World!";
	}

	// Find by TransactionId
	@GetMapping("/transaction/{transactionId}")
	public ResponseEntity<NeftTransaction> getTransactionDetails(
			@PathVariable(value = "transactionId") int transactionId) {
		NeftTransaction nTransactionObj = service.getTransactionId(transactionId);
		System.out.println(nTransactionObj);
		if (nTransactionObj == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(nTransactionObj);
	}

	@GetMapping("/ifscccode/{ifsccode}")
	public List<NeftTransaction> getTransactionsByIfsccode(
			@PathVariable(value = "ifsccode") String ifsccode) {
		System.out.println("IFSC CODE :: "+ifsccode);
		List<NeftTransaction> transactionList = service.findByIfsccode(ifsccode);
		System.out.println("List Values :: "+transactionList);
		if (transactionList == null)
			return null;
		return transactionList;
	}
}
