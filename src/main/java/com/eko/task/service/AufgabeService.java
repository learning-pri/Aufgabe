package com.eko.task.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eko.task.dao.AccountStatementMetadata;
import com.eko.task.dao.AccountStatementViewBO;
import com.eko.task.dao.AepsBO;
import com.eko.task.dao.BankDownBO;
import com.eko.task.dao.EkoCspBO;
import com.eko.task.dao.EkoTransaction;
import com.eko.task.dao.FundSettlementBO;
import com.eko.task.dao.IfsccodeBO;
import com.eko.task.dao.IssueTrackerBO;
import com.eko.task.dto.CommonRequest;
import com.eko.task.dto.CommonResponse;
import com.eko.task.repository.AufgabeRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AufgabeService implements IAufgabeService {

	private static final String SERVICE_REQUEST = "Request: ";
	private static final String SERVICE_RESPONSE = "Response: ";
	private static final String NA = "NA";
	private static final int SUCCESS_STATUS = 1;
	private static final String SUCCESS = "Success";
	private static final int FAILURE_STATUS = 0;
	private static final String FAILURE = "Failure";
	static SimpleDateFormat transactionDate = new SimpleDateFormat("dd/MM/yy hh:mm a");
	static SimpleDateFormat transactionDateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");

	static Logger LOG = LoggerFactory.getLogger(AufgabeService.class);
	protected static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private AufgabeRepository aufgabeRepository;

	@PersistenceContext
	protected EntityManager em;

	public CommonResponse findByIfsccode(CommonRequest request) {

		System.out.println("AufgabeService.findByIfsccode()");
		CommonResponse response = new CommonResponse();
		try {
			LOG.info(SERVICE_REQUEST + mapper.writeValueAsString(request));
			List<Object[]> ifsccodeBOObjArry = aufgabeRepository.findByIfsccode(request.getParameters().getIfscCode());

			List<IfsccodeBO> ifscList = new ArrayList<IfsccodeBO>();
			IfsccodeBO ifsc = new IfsccodeBO();

			if (ifsccodeBOObjArry.size() == 0) {
				response.setStatusId(FAILURE_STATUS);
				response.setMessage(FAILURE);
				response.setData("Ifsccode not found.");

				return response;
			}
			ifsccodeBOObjArry.stream().forEach(item -> {
				ifsc.setBankName(null == item[0] ? NA : item[0].toString());
				ifsc.setIfsccode(null == item[1] ? NA : item[1].toString());
				ifsc.setBranchName(null == item[2] ? NA : item[2].toString());
				ifsc.setCity(null == item[3] ? NA : item[3].toString());
				ifsc.setDistrict(null == item[4] ? null : item[4].toString());
				ifsc.setState(null == item[5] ? NA : item[5].toString());
				ifscList.add(ifsc);
			});

			response.setStatusId(SUCCESS_STATUS);
			response.setMessage(SUCCESS);
			response.setData(ifscList);

			LOG.info(SERVICE_RESPONSE + mapper.writeValueAsString(response));

			return response;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatusId(FAILURE_STATUS);
			response.setMessage(FAILURE);
			response.setData("Oops.. unable to process request");

			return response;
		}
	}

	@Override
	public CommonResponse getPancardDetails(CommonRequest request) {
		CommonResponse response = new CommonResponse();
		try {
			LOG.info(SERVICE_REQUEST + mapper.writeValueAsString(request));

			List<Object[]> ekoCspObjArry = aufgabeRepository
					.populatePancardDetails(request.getParameters().getPanNumber());

			List<EkoCspBO> ekoCspList = new ArrayList<EkoCspBO>();
			LOG.info("PANCARD :: " + request.getParameters().getPanNumber());

			if (ekoCspObjArry.size() == 0) {
				response.setStatusId(FAILURE_STATUS);
				response.setMessage(FAILURE);
				response.setData(request.getParameters().getPanNumber() + " is not found.");

				return response;
			}

			ekoCspObjArry.stream().forEach(item -> {
				EkoCspBO ekoCsp = new EkoCspBO();
				ekoCsp.setCellNumber(null == item[0] ? NA : item[0].toString());
				ekoCsp.setName(null == item[1] ? NA : item[1].toString());
				ekoCsp.setDateOfBirth(null == item[2] ? NA : item[2].toString());
				ekoCsp.setPanCardNumber(null == item[3] ? NA : item[3].toString());
				ekoCsp.setCspcode(null == item[4] ? NA : item[4].toString());
				ekoCspList.add(ekoCsp);
			});

			response.setStatusId(SUCCESS_STATUS);
			response.setMessage(SUCCESS);
			response.setData(ekoCspList);

			LOG.info(SERVICE_RESPONSE + mapper.writeValueAsString(response));

			return response;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatusId(FAILURE_STATUS);
			response.setMessage(FAILURE);
			response.setData("Oops.. unable to process request");

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusId(FAILURE_STATUS);
			response.setMessage(FAILURE);
			response.setData("Oops.. unable to process request");

			return response;
		}
	}

	@Override
	public CommonResponse getBankDownList(CommonRequest request) {
		CommonResponse response = new CommonResponse();
		try {
			LOG.info(SERVICE_REQUEST + mapper.writeValueAsString(request));

			List<Object[]> bankDownObjArry = aufgabeRepository.populateBankDownList();

			if (bankDownObjArry.size() == 0) {
				response.setStatusId(FAILURE_STATUS);
				response.setMessage(FAILURE);
				response.setData("No bank is down currently.");

				return response;
			}

			List<BankDownBO> bankDownist = preparingBankDownList(bankDownObjArry);

			response.setStatusId(SUCCESS_STATUS);
			response.setMessage(SUCCESS);
			response.setData(bankDownist);

			LOG.info(SERVICE_RESPONSE + mapper.writeValueAsString(response));

			return response;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatusId(FAILURE_STATUS);
			response.setMessage(FAILURE);
			response.setData("Oops.. unable to process request");

			return response;
		}
	}

	private List<BankDownBO> preparingBankDownList(List<Object[]> bankDownObjArry) {
		List<BankDownBO> bankDownist = new ArrayList<BankDownBO>();
		BankDownBO bankBO = new BankDownBO();
		bankDownObjArry.stream().forEach(item -> {
			bankBO.setBankName(null == item[0] ? NA : item[0].toString());
			bankBO.setStoppedAt(null == item[1] ? NA : formatingDate(item[1].toString()));
			bankBO.setStoppedTill(null == item[2] ? NA : formatingDate(item[2].toString()));
			bankDownist.add(bankBO);
		});
		return bankDownist;
	}

	@Override
	public CommonResponse searchTransaction(CommonRequest request) {
		CommonResponse response = new CommonResponse();
		try {
			LOG.info(SERVICE_REQUEST + mapper.writeValueAsString(request));

			List<Object[]> ekoTransactionObjArray = aufgabeRepository
					.getEkoTransactionbyTransactionId(request.getParameters().getTid());

			if (ekoTransactionObjArray == null || ekoTransactionObjArray.size() == 0) {
				LOG.info("Transaction id: " + request.getParameters().getTid() + " not found");
				// return preparingResponse(FAILURE_STATUS, FAILURE, "Transaction not found");
				response.setStatusId(FAILURE_STATUS);
				response.setMessage(FAILURE);
				response.setData("Transaction id: " + request.getParameters().getTid() + " not found");
				return response;
			}
			EkoTransaction ekoTransactionBO = populateEkoTransactionBO(ekoTransactionObjArray);

			if (!request.hasGlobalAccess()) {

				if (!ekoTransactionBO.getOriginationAddress().equals(request.getRequesterCell())) {
					LOG.info("Oops.. You have not initiated this transaction.");
					response.setStatusId(FAILURE_STATUS);
					response.setMessage(FAILURE);
					response.setData("Oops.. You have not initiated this transaction.");
					return response;
				}
			}
			   
/*			if (ekoTransactionBO.getOriginationAddress().equals(request.getRequesterCell())) {
				LOG.info("Oops.. You have not initiated this transaction.");
				response.setStatusId(FAILURE_STATUS);
				response.setMessage(FAILURE);
				response.setData("Oops.. You have not initiated this transaction.");
				return response;
			}*/

			List<Object[]> accountStatementViewArray = aufgabeRepository
					.getAccountStatementViewByTID(ekoTransactionBO.getTransactionId(), ekoTransactionBO.getCustomerId());
			AccountStatementViewBO accountStatementViewBO = populateAccountStatementViewBO(accountStatementViewArray);

			response.setStatusId(SUCCESS_STATUS);
			response.setMessage(SUCCESS);
			response.setData(accountStatementViewBO);

			LOG.info(SERVICE_RESPONSE + mapper.writeValueAsString(response));

			return response;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatusId(FAILURE_STATUS);
			response.setMessage(FAILURE);
			response.setData("Oops.. unable to process request");

			return response;
		}
	}

	private String formatingDate(String date) {
		try {
			return transactionDate.format(transactionDateInput.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private AccountStatementViewBO populateAccountStatementViewBO(List<Object[]> accountStatementViewArray) {


		AccountStatementViewBO accountStatementViewBO = new AccountStatementViewBO();
		accountStatementViewArray.stream().forEach(item -> {
			accountStatementViewBO.setCustomerId(null == item[0] ? NA : item[0].toString());
			accountStatementViewBO.setAccountId(null == item[1] ? NA : item[1].toString());
			if(null ==item[2] || Double.parseDouble(item[2].toString())==0.0) {
				accountStatementViewBO.setAmount(item[3].toString());
			} else {
				accountStatementViewBO.setAmount(item[2].toString());
			}
		//	accountStatementViewBO.setAmount(null == item[2] ? item[3].toString() : item[2].toString());
			accountStatementViewBO.setJiraId(null == item[4] ? NA : item[4].toString());
			String transactionDateStr = null;

			if (null != item[5]) {
				transactionDateStr = formatingDate(item[5].toString());
				accountStatementViewBO.setTransactionTime(transactionDateStr);
			} else
				accountStatementViewBO.setTransactionTime(NA);

			accountStatementViewBO.setRefundTransactionId(null == item[6] ? NA : item[6].toString());
			accountStatementViewBO.setRunningBalance(null == item[7] ? NA : item[7].toString());
			accountStatementViewBO.setPipe(null == item[8] ? NA : item[8].toString());
			accountStatementViewBO.setStatus(null == item[9] ? NA : item[9].toString());
			accountStatementViewBO.setTransactionId(null == item[10] ? NA : item[10].toString());
			accountStatementViewBO.setReferenceNumber(null == item[11] ? NA : item[11].toString());
			// accountStatementViewBO.setTransactionTypeId(null == item[12] ? null :
			// item[12].toString());

			LOG.info("Metadata-- "+ item[13]);
			if (null != item[13]) {

				AccountStatementMetadata metadataObj = null;
				try {
					metadataObj = mapper.readValue(item[13].toString(), AccountStatementMetadata.class);
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				LOG.info("metadataObj.getTx_name()-- "+ metadataObj.getTx_name());
				LOG.info("metadataObj.getChannel()-- "+ metadataObj.getChannel());
				LOG.info("metadataObj.getRecipient_name()-- "+ metadataObj.getMerchant_name());
				LOG.info("metadataObj.getMerchant_mobile()-- "+ metadataObj.getMerchant_mobile());
				LOG.info("metadataObj.getUtrNumber()-- "+ metadataObj.getUtrNumber());
				accountStatementViewBO.setTransactionTypeId(metadataObj == null ? null : metadataObj.getTx_name());
				accountStatementViewBO.setChannel(metadataObj == null ? null : metadataObj.getChannel());
				accountStatementViewBO.setMerchantName(metadataObj == null ? null : metadataObj.getMerchant_name());
				accountStatementViewBO.setMerchantCell(metadataObj == null ? null : metadataObj.getMerchant_mobile());
		//		accountStatementViewBO.setTransactionTypeId(metadataObj == null ? null : metadataObj.getChannel());
			} else
				accountStatementViewBO.setTransactionTypeId(null);
		});

		return accountStatementViewBO;
	}

	private EkoTransaction populateEkoTransactionBO(List<Object[]> ekoTransactionObjArray) {

		EkoTransaction ekoTransactionBO = new EkoTransaction();
		ekoTransactionObjArray.stream().forEach(item -> {
			ekoTransactionBO.setTransactionId(null == item[0] ? 0 : Integer.parseInt(item[0].toString()));
			ekoTransactionBO.setOriginationAddress(null == item[2] ? NA : item[2].toString());
			ekoTransactionBO.setOriginationID(null == item[3] ? NA : item[3].toString());
			ekoTransactionBO.setCustomerId(null == item[4] ? NA : item[4].toString());
//			ekoTransactionBO.setCustomerCellNumber(null == item[9] ? NA : item[9].toString());
		});
		return ekoTransactionBO;
	}

	@Override
	public CommonResponse getAccountDetails(CommonRequest request) {
		CommonResponse response = new CommonResponse();

		try {
			LOG.info(SERVICE_REQUEST + mapper.writeValueAsString(request));

			String input = null;
			List<Object[]> ekoCspObjArry;
			if (null != request.getParameters().getUserCode()) {
				input = request.getParameters().getUserCode();
				ekoCspObjArry = aufgabeRepository
						.populateInputAccountDetailsByCode(request.getParameters().getUserCode());
			} else if (null != request.getParameters().getMobileNumber()) {
				input = request.getParameters().getMobileNumber();
				ekoCspObjArry = aufgabeRepository
						.populateInputAccountDetailsByCellNumber(request.getParameters().getMobileNumber());
			} else if (null != request.getParameters().getName()) {
				input = request.getParameters().getName();
				ekoCspObjArry = aufgabeRepository
						.populateInputAccountDetailsByName(request.getParameters().getName());
			} else {
				response.setStatusId(FAILURE_STATUS);
				response.setMessage(FAILURE);
				response.setData("Invalid Input");

				return response;
			}

			List<EkoCspBO> ekoCspList = new ArrayList<EkoCspBO>();
			LOG.info("Input -- " + input);

			if (ekoCspObjArry.size() == 0) {
				response.setStatusId(FAILURE_STATUS);
				response.setMessage(FAILURE);
				response.setData(input + " is not found.");

				return response;
			}

	/*		ekoCspObjArry.stream().forEach(item -> {
				EkoCspBO ekoCsp = new EkoCspBO();
				ekoCsp.setCellNumber(null == item[0] ? NA : item[0].toString());
				ekoCsp.setName(null == item[1] ? NA : item[1].toString());
				ekoCsp.setDateOfBirth(null == item[2] ? NA : item[2].toString());
				ekoCsp.setPanCardNumber(null == item[3] ? NA : item[3].toString());
				ekoCsp.setCspcode(null == item[4] ? NA : item[4].toString());

				ekoCsp.setAccountType(null == item[5] ? NA : item[5].toString());
				ekoCsp.setActivationDate(null == item[6] ? NA : formatingDate(item[6].toString()));
				ekoCsp.setStatus(null == item[7] ? NA : item[7].toString());
				ekoCsp.setBalance(null == item[8] ? NA : item[8].toString());
				ekoCsp.setAreaManager(null == item[9] ? NA : item[9].toString());

				ekoCspList.add(ekoCsp);
			});*/
			
			
			ekoCspObjArry.stream().forEach(item -> {
				EkoCspBO ekoCsp = new EkoCspBO();
				
				ekoCsp.setCellNumber(null == item[0] ? NA : item[0].toString());
				ekoCsp.setName(null == item[1] ? NA : item[1].toString());
				ekoCsp.setDateOfBirth(null == item[2] ? NA : item[2].toString());
				ekoCsp.setPanCardNumber(null == item[3] ? NA : item[3].toString());
				ekoCsp.setCspcode(null == item[4] ? NA : item[4].toString());
				ekoCsp.setActivationDate(null == item[5] ? NA : formatingDate(item[5].toString()));
				ekoCsp.setStatus(null == item[6] ? NA : item[6].toString());
				ekoCsp.setBalance(null == item[7] ? NA : item[7].toString());
				ekoCsp.setAccountType(null == item[8] ? NA : item[8].toString());
				ekoCsp.setDistributor(null == item[9] ? NA : item[9].toString());
				ekoCsp.setAreaManager(null == item[10] ? NA : item[10].toString());
				ekoCsp.setPlanName(null == item[11] ? NA : item[11].toString());
				ekoCsp.setEmail(null == item[12] ? NA : item[12].toString());
				ekoCsp.setShopAddress(null == item[13] ? NA : item[13].toString());
				ekoCsp.setCity(null == item[14] ? NA : item[14].toString());
				ekoCsp.setState(null == item[15] ? NA : item[15].toString());
				ekoCsp.setPincode(null == item[16] ? NA : item[16].toString());
				ekoCsp.setCircel(null == item[17] ? NA : item[17].toString());
				ekoCsp.setCircel(null == item[18] ? NA : item[18].toString());

				ekoCspList.add(ekoCsp);
			});

			response.setStatusId(SUCCESS_STATUS);
			response.setMessage(SUCCESS);
			response.setData(ekoCspList);

			LOG.info(SERVICE_RESPONSE + mapper.writeValueAsString(response));

			return response;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatusId(FAILURE_STATUS);
			response.setMessage(FAILURE);
			response.setData("Oops.. unable to process request");

			return response;
		}
	}

	@Override
	public CommonResponse getTicketdetails(CommonRequest request) {
		CommonResponse response = new CommonResponse();
		try {
			LOG.info(SERVICE_REQUEST + mapper.writeValueAsString(request));
			List<Object[]> issueTrackerArray = aufgabeRepository
					.populateTicketDetails(request.getParameters().getJira());

			if (issueTrackerArray.size() == 0) {
				response.setStatusId(FAILURE_STATUS);
				response.setMessage(FAILURE);
				response.setData(request.getParameters().getJira() + " is not found.");

				return response;
			}

			IssueTrackerBO issueTracker = new IssueTrackerBO();
			issueTrackerArray.stream().forEach(item -> {
				issueTracker.setTicketRefNo(null == item[0] ? NA : item[0].toString());
				issueTracker.setEkoTrxnId(null == item[1] ? NA : item[1].toString());
				issueTracker.setIssueReason(null == item[2] ? NA : item[2].toString());
				issueTracker.setUpdatedAt(null == item[3] ? NA : item[3].toString());
				issueTracker.setTat(null == item[4] ? NA : item[4].toString());

				// issueTrackerList.add(issueTracker);
			});

			response.setStatusId(SUCCESS_STATUS);
			response.setMessage(SUCCESS);
			response.setData(issueTracker);

			LOG.info(SERVICE_RESPONSE + mapper.writeValueAsString(response));

			return response;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatusId(FAILURE_STATUS);
			response.setMessage(FAILURE);
			response.setData("Oops.. unable to process request");

			return response;
		}
	}

	@Override
	public CommonResponse getAepsDetail(CommonRequest request) {
		CommonResponse response = new CommonResponse();
		try {
			LOG.info(SERVICE_REQUEST + mapper.writeValueAsString(request));
			List<Object[]> aepsArray = aufgabeRepository.populateAepsDetails(request.getParameters().getMobileNumber());

			if (aepsArray.size() == 0) {
				response.setStatusId(FAILURE_STATUS);
				response.setMessage(FAILURE);
				response.setData(request.getParameters().getMobileNumber() + " is not enabled on AePS services.");

				return response;
			}

			List<AepsBO> aepsList = new ArrayList<AepsBO>();
			aepsArray.stream().forEach(item -> {
				AepsBO aepsBO = new AepsBO();
				aepsBO.setCustomerId(null == item[0] ? NA : item[0].toString());
				aepsBO.setMerchantCode(null == item[1] ? NA : item[1].toString());
				aepsBO.setCellnumber(null == item[2] ? NA : item[2].toString());
				aepsBO.setName(null == item[3] ? NA : item[3].toString());

	//			aepsBO.setRblAgentId(null == item[4] ? NA : item[4].toString());
	//
	//			aepsBO.setDeviceId(null == item[5] ? NA : item[5].toString());
	//			aepsBO.setTerminalId(null == item[6] ? NA : item[6].toString());
	//			aepsBO.setStatus(null == item[7] ? NA : item[7].toString());
	//			aepsBO.setCreatedAt(null == item[8] ? NA : item[8].toString());

				aepsBO.setDeviceId(null == item[4] ? NA : item[4].toString());
				aepsBO.setTerminalId(null == item[5] ? NA : item[5].toString());
				aepsBO.setStatus(null == item[6] ? NA : item[6].toString());
				aepsBO.setRblAgentId(null == item[7] ? NA : item[7].toString());
				aepsBO.setCreatedAt(null == item[8] ? NA : formatingDate(item[8].toString()));

				aepsList.add(aepsBO);
			});

			response.setStatusId(SUCCESS_STATUS);
			response.setMessage(SUCCESS);
			response.setData(aepsList);

			LOG.info(SERVICE_RESPONSE + mapper.writeValueAsString(response));

			return response;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatusId(FAILURE_STATUS);
			response.setMessage(FAILURE);
			response.setData("Oops.. unable to process request");

			return response;
		}
	}

	@Override
	public CommonResponse searchTrackingNumber(CommonRequest request) {
		CommonResponse response = new CommonResponse();
		try {
			LOG.info(SERVICE_REQUEST + mapper.writeValueAsString(request));

			List<Object[]> accountStatementViewArray = aufgabeRepository
					.getAccountStatementViewByRRN(request.getParameters().getTrackingNumber());
			
			if (accountStatementViewArray == null || accountStatementViewArray.size() == 0) {
				LOG.info("Tracking Number: " + request.getParameters().getTid() + " not found");
				// return preparingResponse(FAILURE_STATUS, FAILURE, "Transaction not found");
				response.setStatusId(FAILURE_STATUS);
				response.setMessage(FAILURE);
				response.setData("Tracking Number: " + request.getParameters().getTid() + " not found");
				return response;
			}
			
			AccountStatementViewBO accountStatementViewBO = populateAccountStatementViewBO(accountStatementViewArray);

			response.setStatusId(SUCCESS_STATUS);
			response.setMessage(SUCCESS);
			response.setData(accountStatementViewBO);

			LOG.info(SERVICE_RESPONSE + mapper.writeValueAsString(response));

			return response;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatusId(FAILURE_STATUS);
			response.setMessage(FAILURE);
			response.setData("Oops.. unable to process request");

			return response;
		}
	}

	@Override
	public CommonResponse getFundSettlementAmount(CommonRequest request) {
		CommonResponse response = new CommonResponse();
		try {
			LOG.info(SERVICE_REQUEST + mapper.writeValueAsString(request));

			List<Object[]> fundSettlementObjArry = aufgabeRepository.getFundSettlementAmount();

			if (fundSettlementObjArry.size() == 0) {
				response.setStatusId(FAILURE_STATUS);
				response.setMessage(FAILURE);
				response.setData("No fund settlement pending currently.");

				return response;
			}

			//List<FundSettlementBO> fundSettlementList = new ArrayList<FundSettlementBO>();
			FundSettlementBO fundSettlementBO = new FundSettlementBO();
			fundSettlementObjArry.stream().forEach(item -> {
				fundSettlementBO.setUnsettledAmount(null == item[0] ? NA : item[0].toString());
			//	fundSettlementList.add(fundSettlementBO);
			});
			

			response.setStatusId(SUCCESS_STATUS);
			response.setMessage(SUCCESS);
			response.setData(fundSettlementBO);

			LOG.info(SERVICE_RESPONSE + mapper.writeValueAsString(response));

			return response;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatusId(FAILURE_STATUS);
			response.setMessage(FAILURE);
			response.setData("Oops.. unable to process request");

			return response;
		}
	}
}
