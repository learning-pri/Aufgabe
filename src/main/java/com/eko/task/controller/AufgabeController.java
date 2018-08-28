package com.eko.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eko.task.dao.NeftTransaction;
import com.eko.task.repository.AufgabeRepository;

@RestController
@RequestMapping("/")
public class AufgabeController {

	@Autowired
//	IAufgabeRepository repository;
	AufgabeRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public String welcome() {
		return "Hello World!";
	}

	// Find by TransactionId
	@GetMapping("/transaction/{transactionId}")
	public ResponseEntity<NeftTransaction> getTransactionDetails(
			@PathVariable(value = "transactionId") int transactionId) {
		NeftTransaction nTransactionObj = repository.getTransactionId(transactionId);
		System.out.println(nTransactionObj);
		if (nTransactionObj == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(nTransactionObj);
	}

//	@GetMapping("/transaction/{ifsccode}")
//	public ResponseEntity<List<NeftTransaction>> getTransactionsByIfsccode(
//			@PathVariable(value = "ifsccode") String ifsccode) {
//		List<NeftTransaction> transactionList = repository.findByIfsccode(ifsccode);
//		System.out.println(transactionList);
//		if (transactionList == null)
//			return ResponseEntity.notFound().build();
//		return ResponseEntity.ok().body(transactionList);
//	}
}
