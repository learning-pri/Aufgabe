package com.eko.task.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eko.task.dto.CommonRequest;
import com.eko.task.dto.CommonResponse;
import com.eko.task.service.AufgabeService;
import com.eko.task.service.IAufgabeService;

@RestController
@RequestMapping("/")
public class AufgabeController {

	@Autowired
	IAufgabeService service;
	
	static Log LOG = LogFactory.getLog(AufgabeService.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String welcome() {
		return "Hello World!";
	}

	@PostMapping("/ifscccode")
	public CommonResponse getTransactionsByIfsccode(@RequestBody CommonRequest request) {
		LOG.info("AufgabeController.getTransactionsByIfsccode()");
		CommonResponse response = service.findByIfsccode(request);
		return response;
	}

	@PostMapping("/tid")
	public CommonResponse searchTransaction(@RequestBody CommonRequest request) {
		LOG.info("AufgabeController.searchTransaction()");
		CommonResponse response = service.searchTransaction(request);
		return response;
		
	}
	
	@PostMapping("/rrn")
	public CommonResponse searchTrackingNumber(@RequestBody CommonRequest request) {
		LOG.info("AufgabeController.searchTrackingNumber()");
		CommonResponse response = service.searchTrackingNumber(request);
		return response;
		
	}
	
	@PostMapping("/bankdown")
	public CommonResponse getBankDownList(@RequestBody CommonRequest request){
		CommonResponse response =  service.getBankDownList(request);
		return response;
	}

	@PostMapping("/pan")
	public CommonResponse getPancardDetails(@RequestBody CommonRequest request) {
		LOG.info("AufgabeController.getPancardDetails()");
		CommonResponse response = service.getPancardDetails(request);
		return response;
	}
	
	@PostMapping("/user")
	public CommonResponse getAccountDetails(@RequestBody CommonRequest request) {
		LOG.info("AufgabeController.getAccountBalance()");
		CommonResponse response = service.getAccountDetails(request);
		return response;
	}
	
//	@GetMapping("/balanceByNumber")
//	public CommonResponse getAccountDetailsByNumber(@RequestBody CommonRequest request) {
//		System.out.println("AufgabeController.getAccountDetailsByCode()");
//		CommonResponse response = service.getAccountDetails(request);
//		return response;
//	}
	
/*	@GetMapping("/balance/{metrchantCode}")
	public CommonResponse getAccountDetailsByNumber(
			@PathVariable(value = "cellNumber") String cellNumber) {
		System.out.println("AufgabeController.getAccountBalance()");
		CommonResponse response = service.getAccountDetails(cellNumber);
		return response;
	}*/
	
	@PostMapping("/jira")
	public CommonResponse getTicketdetails(@RequestBody CommonRequest request) {
		LOG.info("AufgabeController.getTicketdetails()");
		CommonResponse response = service.getTicketdetails(request);
		return response;
	}
	
	@PostMapping("/aeps")
	public CommonResponse getAepsDetail(@RequestBody CommonRequest request) {
		LOG.info("AufgabeController.getAepsDetail()");
		CommonResponse response = service.getAepsDetail(request);
		return response;
	}	
	
	@PostMapping("/fundsettlement")
	public CommonResponse getFundSettlementAmount(@RequestBody CommonRequest request){
		CommonResponse response =  service.getFundSettlementAmount(request);
		return response;
	}
}
