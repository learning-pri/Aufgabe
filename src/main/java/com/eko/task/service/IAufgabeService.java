package com.eko.task.service;

import com.eko.task.dto.CommonRequest;
import com.eko.task.dto.CommonResponse;

public interface IAufgabeService {

//	NeftTransaction getTransactionId(int transactionId);
	
	CommonResponse findByIfsccode(CommonRequest request);
    
//	Transaction getTransaction(int transactionId);
	
	CommonResponse getPancardDetails(CommonRequest request);

	CommonResponse getBankDownList(CommonRequest request);

	CommonResponse searchTransaction(CommonRequest request);

	CommonResponse getAccountDetails(CommonRequest request);

	CommonResponse getTicketdetails(CommonRequest request);

	CommonResponse getAepsDetail(CommonRequest request);

	CommonResponse searchTrackingNumber(CommonRequest request);

	CommonResponse getFundSettlementAmount(CommonRequest request);
}
