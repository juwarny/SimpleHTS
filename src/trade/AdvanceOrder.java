package trade;

import java.util.ArrayList;
import com4j.*;
import test.cptrade.*;
//내용이 많아서 사이보스 help 문서를 참고

public class AdvanceOrder {
	private ICpTdDib stadvanceOrder;
	private ICpTdDib stadvanceCancle;
	private ICpTdDib stadvanceOdList;
	
	private OdBeforeinit od;
	private Object[] accountNum;
	private ArrayList<Object> adorderinfo;
	
	public AdvanceOrder(){
		stadvanceOrder = test.cptrade.ClassFactory.createCpTdNew9061();//주식(거래소 코스닥) 예약 주문(신용포함)
		stadvanceCancle = test.cptrade.ClassFactory.createCpTdNew9064();//주식(거래소 코스닥) 예약 취소주문
		stadvanceOdList = test.cptrade.ClassFactory.createCpTd9065();//주식(거래소 코스닥) 예약 주문 내역조회
	}
	
	
	
	public void setvalStadOrder(String gdmgcode, String ordercode, String code, long orderQuan, String callcode, 
								long orderprice, String cacrdcode, String datecrdloan, String crdloancode){
		od = new OdBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		stadvanceOrder.setInputValue(0, (Object)accountNum[0]);
		stadvanceOrder.setInputValue(1, (Object)gdmgcode);
		stadvanceOrder.setInputValue(2, (Object)ordercode);
		stadvanceOrder.setInputValue(3, (Object)code);
		stadvanceOrder.setInputValue(4, (Object)orderQuan);
		stadvanceOrder.setInputValue(5, (Object)callcode);
		stadvanceOrder.setInputValue(6, (Object)orderprice);
		stadvanceOrder.setInputValue(7, (Object)cacrdcode);
		stadvanceOrder.setInputValue(8, (Object)datecrdloan);
		stadvanceOrder.setInputValue(9, (Object)crdloancode);		
		
		stadvanceOrder.blockRequest();		
	}
	public ArrayList<Object> getvalStadOrder(){
		adorderinfo = new ArrayList<Object>();
		stadvanceOrder.blockRequest();
		for(int i=0; i<11; i++){
		while(stadvanceOrder.getHeaderValue(i)==null) i++;
		adorderinfo.add(stadvanceOrder.getHeaderValue(i));			
		}		
		return adorderinfo;
	}
	
	
	
	public void setvalStadCancle(long advancenum, String gdmgcode, String code){
		od = new OdBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		stadvanceCancle.setInputValue(0, (Object)advancenum);
		stadvanceCancle.setInputValue(1, (Object)accountNum[0]);
		stadvanceCancle.setInputValue(2, (Object)gdmgcode);
		stadvanceCancle.setInputValue(3, (Object)code);
		
		
		stadvanceCancle.blockRequest();		
	}
	public ArrayList<Object> getvalStadCancle(){
		adorderinfo = new ArrayList<Object>();
		stadvanceCancle.blockRequest();
		for(int i=0; i<11; i++){
		while(stadvanceCancle.getHeaderValue(i)==null) i++;
		adorderinfo.add(stadvanceCancle.getHeaderValue(i));			
		}		
		return adorderinfo;
	}
	
	
	
	
	public void setvalStadOdList(String gdmgcode, long quirynum){
		od = new OdBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		stadvanceOdList.setInputValue(0, (Object)accountNum[0]);
		stadvanceOdList.setInputValue(1, (Object)gdmgcode);
		stadvanceOdList.setInputValue(2, (Object)quirynum);

		
		
		stadvanceCancle.blockRequest();		
	}
	public ArrayList<Object> getvalHStadOdList(){
		adorderinfo = new ArrayList<Object>();
		stadvanceCancle.blockRequest();
		for(int i=0; i<5; i++){
		while(stadvanceCancle.getHeaderValue(i)==null) i++;
		adorderinfo.add(stadvanceCancle.getHeaderValue(i));			
		}		
		return adorderinfo;
	}
	public ArrayList<Object> getvalDStadOdList(int index){
		adorderinfo = new ArrayList<Object>();
		stadvanceCancle.blockRequest();
		for(int i=0; i<5; i++){
		while(stadvanceCancle.getDataValue(i, index)==null) i++;
		adorderinfo.add(stadvanceCancle.getDataValue(i, index));			
		}		
		return adorderinfo;
	}
}
