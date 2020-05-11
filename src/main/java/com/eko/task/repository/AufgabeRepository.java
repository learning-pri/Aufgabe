package com.eko.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eko.task.dao.NeftTransaction;

public interface AufgabeRepository extends JpaRepository<NeftTransaction, Long> {

	// @Query("select nt from NeftTransaction nt where nt.ifscCode=?1")
	@Query(value = "select bankName, ifsccode, branchName,city, district, state from ifsccode where ifscCode=?1", nativeQuery = true)
	List<Object[]> findByIfsccode(String ifsccode);

	@Query(value = "select ekotrxnid, trackingnumber, IFSCCode, RecipientsAccNo, CSPCell, DepositorCell, TxAmount, TxStatus, "
			+ "TxTime, RefundTrxnId, RefundTime, Mode, RkbTrackingnumber, destination, Comments from nefttransaction where ekotrxnid=?1", nativeQuery = true)
	List<Object[]> getTransactionDetails(int transactionId);
	// @Query(value = "select ekotrxnid as ekoTrxnId, trackingnumber as
	// trackingNumber, IFSCCode as ifscCode, RecipientsAccNo as recipientsAccNo,
	// CSPCell as cspCell, DepositorCell as depositorCell, TxAmount as txAmount,
	// TxStatus as txStatus, "
	// + "TxTime as txTime, RefundTrxnId as refundTrxnId, RefundTime as refundTime,
	// Mode as mode, RkbTrackingnumber as rkbTrackingNumber, destination as
	// destination, Comments as comments from nefttransaction where ekotrxnid=?1",
	// nativeQuery = true)
	// List<Transaction> getTransactionDetails(int transactionId);

	@Query(value = "select CellNumber,DisplayName,DateOfBirth,PANCardNumber,CSPCode from ekocsp where cellnumber is not null and PANCardNumber = ?1", nativeQuery = true)
	List<Object[]> populatePancardDetails(String panNumber);

	@Query(value = "select bankname, StoppedAt, stoppedtill from bankdetailhistory where StoppedTill>now()", nativeQuery = true)
	List<Object[]> populateBankDownList();

	@Query(value = "select * from ekotransaction where TransactionID= ?1", nativeQuery = true)
	List<Object[]> getEkoTransactionbyTransactionId(String string);

	@Query(value = "select * from ekocustomer where cellnumber= ?1", nativeQuery = true)
	List<Object[]> getEkoCustomerByOriginatorNumber(String merchantCellNumber);
//
//	@Query(value = "select tid,status_name,credit_amount,debit_amount,timestamp,tracking_number, service_provider from account_statement_view where tid=?1 and user_id= ?2", nativeQuery = true)	
//	List<Object[]> getAccountStatementView(int tid, String originationID);

	@Query(value = "select user_id, account_id,credit_amount, debit_amount, jira_id, timestamp, refund_tid, running_balance, service_provider, status_name, tid, tracking_number, txtype_id,metadata from account_statement_view where tid=?1 and user_id= ?2", nativeQuery = true)	
	List<Object[]> getAccountStatementViewByTID(int tid, String originationID);
	
//	@Query(value = "select c.CellNumber, c.Display_Name, c.Date_Of_Birth, ec.PANCardNumber, ec.CSPCode,  if(sa.prd_offering_id =8 , 'Wallet', ect.typename) as Account_Type,  sa.ACTIVATION_DATE, astate.status_description, sa.SAVINGS_BALANCE, etsm.name as Area_Manager from customer c join savings_account sa on c.customer_id=sa.customerid join account a on a.account_id=sa.account_id join account_state astate on a.account_state_id=astate.account_state_id left join ekocsp ec on c.customer_id=ec.customerid left join ekocsptype ect on ec.csptype=ect.TypeId left join ekotsm etsm on ec.tsm_id=etsm.tsm_id where sa.prd_offering_id in (1,8) and c.cellnumber is not null and ec.cspcode= ?1", nativeQuery = true)
	@Query(value = "select tmp.CellNumber,tmp.Display_Name,tmp.Date_Of_Birth,tmp.PANCardNumber,tmp.CSPCode,tmp.ACTIVATION_DATE, tmp.status_description,tmp.SAVINGS_BALANCE, if(tmp.prd_offering_id =8 , 'Wallet', ect.typename) as Account_Type,   if(tmp.prd_offering_id =8 , '', scsp.DisplayName) as Distributor , if(tmp.prd_offering_id =8 , '', etsm.name) as Area_Manager,  if(tmp.prd_offering_id =8 , '', tmp.PlanName) as PlanName, if(tmp.prd_offering_id =8 , '', tmp.email) as Email, tmp.Shop_Address, tmp.CITY, tmp.State, tmp.Pincode, tmp.Circel, if(tmp.prd_offering_id =8 , '', tmp.BlankChequeStatus) as BlankChequeStatus  from ( select c.CellNumber as CellNumber, c.Display_Name as Display_Name, c.Date_Of_Birth as Date_Of_Birth, ec.PANCardNumber as PANCardNumber, ec.CSPCode as CSPCode,  sa.prd_offering_id as prd_offering_id,  sa.ACTIVATION_DATE as ACTIVATION_DATE, astate.status_description as status_description, sa.SAVINGS_BALANCE as SAVINGS_BALANCE, ec.SuperCsp as SuperCsp,ec.csptype as csptype,ec.tsm_id as tsm_id, ep.planname as PlanName, ec.advancestatus BlankChequeStatus, cd.email as email, CONCAT(ifnull(ekoadd.LINE_1,addr.LINE_1),\" \",ifnull(ekoadd.LINE_2,addr.LINE_2)) as Shop_Address, ifnull(ekoadd.CITY,pid.city) as CITY, ifnull((ifnull(ekoadd.state,pid.state)),mp.statename) as State, ifnull(ekoadd.zip,pid.pincode) as Pincode, cir.CirCleName as Circel from  customer c  join savings_account sa on c.customer_id=sa.customerid  join account a on a.account_id=sa.account_id  join account_state astate on a.account_state_id=astate.account_state_id  left join ekocsp ec on c.customer_id=ec.customerid  left join subscription_detail sd on ec.customerid=sd.customer_id left join ekoplan ep on sd.ekoplan_id=ep.planid join customer_detail cd on cd.customer_id=ec.customerid left join customer_address_detail addr on (EC.customerid=addr.CUSTOMER_ID) left join ekocircle cir on c.Circle=cir.Circleid left join pincodedetail pid on addr.ZIP=pid.pincode left join ekoaddressdetail ekoadd on c.PermanentAddressId=ekoadd.EKO_ADDRESS_ID left join master_pincodes mp on ekoadd.zip=mp.pincode where  sa.prd_offering_id in (1,8) and c.cellnumber is not null and ec.cspcode= ?1 ) tmp join ekocsp scsp on scsp.EkoCspId=tmp.SuperCsp left join ekocsptype ect on tmp.csptype=ect.TypeId  left join ekotsm etsm on tmp.tsm_id=etsm.tsm_id", nativeQuery = true)
	List<Object[]> populateInputAccountDetailsByCode(String metrchantCode);

//	@Query(value = "select c.CellNumber, c.Display_Name, c.Date_Of_Birth, ec.PANCardNumber, ec.CSPCode,  if(sa.prd_offering_id =8 , 'Wallet', ect.typename) as Account_Type,  sa.ACTIVATION_DATE, astate.status_description, sa.SAVINGS_BALANCE, etsm.name as Area_Manager from customer c join savings_account sa on c.customer_id=sa.customerid join account a on a.account_id=sa.account_id join account_state astate on a.account_state_id=astate.account_state_id left join ekocsp ec on c.customer_id=ec.customerid left join ekocsptype ect on ec.csptype=ect.TypeId left join ekotsm etsm on ec.tsm_id=etsm.tsm_id where sa.prd_offering_id in (1,8) and c.cellnumber= ?1", nativeQuery = true)
	@Query(value = "select tmp.CellNumber,tmp.Display_Name,tmp.Date_Of_Birth,tmp.PANCardNumber,tmp.CSPCode,tmp.ACTIVATION_DATE, tmp.status_description,tmp.SAVINGS_BALANCE, if(tmp.prd_offering_id =8 , 'Wallet', ect.typename) as Account_Type,   if(tmp.prd_offering_id =8 , '', scsp.DisplayName) as Distributor , if(tmp.prd_offering_id =8 , '', etsm.name) as Area_Manager,  if(tmp.prd_offering_id =8 , '', tmp.PlanName) as PlanName, if(tmp.prd_offering_id =8 , '', tmp.email) as Email, tmp.Shop_Address, tmp.CITY, tmp.State, tmp.Pincode, tmp.Circel, if(tmp.prd_offering_id =8 , '', tmp.BlankChequeStatus) as BlankChequeStatus  from ( select c.CellNumber as CellNumber, c.Display_Name as Display_Name, c.Date_Of_Birth as Date_Of_Birth, ec.PANCardNumber as PANCardNumber, ec.CSPCode as CSPCode,  sa.prd_offering_id as prd_offering_id,  sa.ACTIVATION_DATE as ACTIVATION_DATE, astate.status_description as status_description, sa.SAVINGS_BALANCE as SAVINGS_BALANCE, ec.SuperCsp as SuperCsp,ec.csptype as csptype,ec.tsm_id as tsm_id, ep.planname as PlanName, ec.advancestatus BlankChequeStatus, cd.email as email, CONCAT(ifnull(ekoadd.LINE_1,addr.LINE_1),\\\" \\\",ifnull(ekoadd.LINE_2,addr.LINE_2)) as Shop_Address, ifnull(ekoadd.CITY,pid.city) as CITY, ifnull((ifnull(ekoadd.state,pid.state)),mp.statename) as State, ifnull(ekoadd.zip,pid.pincode) as Pincode, cir.CirCleName as Circel from  customer c  left join savings_account sa on c.customer_id=sa.customerid  left join account a on a.account_id=sa.account_id  left join account_state astate on a.account_state_id=astate.account_state_id  left join ekocsp ec on c.customer_id=ec.customerid  left join subscription_detail sd on ec.customerid=sd.customer_id left join ekoplan ep on sd.ekoplan_id=ep.planid join customer_detail cd on cd.customer_id=ec.customerid left join customer_address_detail addr on (EC.customerid=addr.CUSTOMER_ID) left join ekocircle cir on c.Circle=cir.Circleid left join pincodedetail pid on addr.ZIP=pid.pincode left join ekoaddressdetail ekoadd on c.PermanentAddressId=ekoadd.EKO_ADDRESS_ID left join master_pincodes mp on ekoadd.zip=mp.pincode where  sa.prd_offering_id in (1,8) and c.cellnumber is not null and c.cellnumber= ?1 ) tmp join ekocsp scsp on scsp.EkoCspId=tmp.SuperCsp left join ekocsptype ect on tmp.csptype=ect.TypeId  left join ekotsm etsm on tmp.tsm_id=etsm.tsm_id", nativeQuery = true)
	List<Object[]> populateInputAccountDetailsByCellNumber(String cellNumber);

	@Query(value = "select tmp.CellNumber,tmp.Display_Name,tmp.Date_Of_Birth,tmp.PANCardNumber,tmp.CSPCode,tmp.ACTIVATION_DATE, tmp.status_description,tmp.SAVINGS_BALANCE, if(tmp.prd_offering_id =8 , 'Wallet', ect.typename) as Account_Type,   if(tmp.prd_offering_id =8 , '', scsp.DisplayName) as Distributor , if(tmp.prd_offering_id =8 , '', etsm.name) as Area_Manager,  if(tmp.prd_offering_id =8 , '', tmp.PlanName) as PlanName, if(tmp.prd_offering_id =8 , '', tmp.email) as Email, tmp.Shop_Address, tmp.CITY, tmp.State, tmp.Pincode, tmp.Circel, if(tmp.prd_offering_id =8 , '', tmp.BlankChequeStatus) as BlankChequeStatus  from ( select c.CellNumber as CellNumber, c.Display_Name as Display_Name, c.Date_Of_Birth as Date_Of_Birth, ec.PANCardNumber as PANCardNumber, ec.CSPCode as CSPCode,  sa.prd_offering_id as prd_offering_id,  sa.ACTIVATION_DATE as ACTIVATION_DATE, astate.status_description as status_description, sa.SAVINGS_BALANCE as SAVINGS_BALANCE, ec.SuperCsp as SuperCsp,ec.csptype as csptype,ec.tsm_id as tsm_id, ep.planname as PlanName, ec.advancestatus BlankChequeStatus, cd.email as email, CONCAT(ifnull(ekoadd.LINE_1,addr.LINE_1),\\\" \\\",ifnull(ekoadd.LINE_2,addr.LINE_2)) as Shop_Address, ifnull(ekoadd.CITY,pid.city) as CITY, ifnull((ifnull(ekoadd.state,pid.state)),mp.statename) as State, ifnull(ekoadd.zip,pid.pincode) as Pincode, cir.CirCleName as Circel from  customer c  join savings_account sa on c.customer_id=sa.customerid  join account a on a.account_id=sa.account_id  join account_state astate on a.account_state_id=astate.account_state_id  left join ekocsp ec on c.customer_id=ec.customerid  left join subscription_detail sd on ec.customerid=sd.customer_id left join ekoplan ep on sd.ekoplan_id=ep.planid join customer_detail cd on cd.customer_id=ec.customerid left join customer_address_detail addr on (EC.customerid=addr.CUSTOMER_ID) left join ekocircle cir on c.Circle=cir.Circleid left join pincodedetail pid on addr.ZIP=pid.pincode left join ekoaddressdetail ekoadd on c.PermanentAddressId=ekoadd.EKO_ADDRESS_ID left join master_pincodes mp on ekoadd.zip=mp.pincode where  sa.prd_offering_id in (1,8) and c.cellnumber is not null and ec.displayname like :?1 ) tmp join ekocsp scsp on scsp.EkoCspId=tmp.SuperCsp left join ekocsptype ect on tmp.csptype=ect.TypeId  left join ekotsm etsm on tmp.tsm_id=etsm.tsm_id", nativeQuery = true)
	List<Object[]> populateInputAccountDetailsByName(String name);
	
	@Query(value = "select ticketRefNo, ekoTrxnId, description, updatedAt, tat from issue_tracker it left join issue_reason ir on it.issue_reason_desc_id=ir.id where ticketRefNo = ?1", nativeQuery = true)
	List<Object[]> populateTicketDetails(String ticketId);

//	@Query(value = "select ap.customerid,cspcode,cellnumber,displayname,ap.rblagentid,deviceid,terminalid, case when status=1 then 'Active' when status=0 then 'InActive' when status=0 then 'Formfill/Pending' end as status,createdAt from aepsmerchantdetail ap join ekocsp csp where ap.customerid=csp.customerid and csp.cellnumber= ?1", nativeQuery = true)
	@Query(value = "select ap.customerid,cspcode,cellnumber,displayname,deviceid,terminalid, case when status=1 then 'Active' when status=0 then 'InActive' when status=0 then 'Formfill/Pending' end as status, ap.rblagentid, ap.createdat from aepsmerchantdetail ap join ekocsp csp where ap.customerid=csp.customerid and csp.cellnumber= ?1", nativeQuery = true)
	List<Object[]> populateAepsDetails(String jira);

	
	@Query(value = "select user_id, account_id,credit_amount, debit_amount, jira_id, timestamp, refund_tid, running_balance, service_provider, status_name, tid, tracking_number, txtype_id,metadata from account_statement_view where tracking_number=?1 and debit_amount is not null", nativeQuery = true)
	List<Object[]> getAccountStatementViewByRRN(String trackingNumber);
	
	@Query(value = "select sum(amount) from wallettransaction where typeid=392 and status=?1", nativeQuery = true)
	List<Object[]> getFundSettlementAmount();

}
