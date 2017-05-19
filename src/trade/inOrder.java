package trade;

import java.util.ArrayList;

import com4j.*;
import test.cptrade.*;

public class inOrder {
	private ICpTdDib inod = test.cptrade.ClassFactory.createCpTd0311();//장내주문
	private ICpTdDib inodcredit = test.cptrade.ClassFactory.createCpTd0312();//장내 신용주분
	private ICpTdDib inodalter = test.cptrade.ClassFactory.createCpTd0313();//장내주문 정정
	private ICpTdDib inodcancle = test.cptrade.ClassFactory.createCpTd0314();//장내 주문취소
	private ICpTdDib inodtypeccl = test.cptrade.ClassFactory.createCpTd0303();//장내 주문 유형 정정
	private odBeforeinit od;
	private Object[] accountNum;
	private ArrayList<Object> orderinfo;
	
	public void setvalInod(String sb, String gdmgcode, String code, long quan, long unitprice, String callcode){
		od = new odBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		inod.setInputValue(0, (Object)sb);
		inod.setInputValue(1, (Object)accountNum[0]);
		inod.setInputValue(2, (Object)gdmgcode);
		inod.setInputValue(3, (Object)code);
		inod.setInputValue(4, (Object)quan);
		inod.setInputValue(5, (Object)unitprice);
		inod.setInputValue(7, (Object)0);
		inod.setInputValue(8, (Object)callcode);
		inod.blockRequest();
		/*
		'주문 결과와 주문결과 메시지를 얻어옵니다
	    MsgBox "GetDibStatus:" + CStr(m_0311.GetDibStatus) + " GetDibMsg1:" + m_0311.GetDibMsg1
	    'GetDibStatus 결과값
	        '정상주문결과는 0 리턴,
	        '주문가격오류, 주문가능금액부족등의 에러는 -1을 리턴
	    'GetDibMsg1 값
	        '주문결과 메시지를 얻을수 있습니다.
		 */
	}
	public ArrayList<Object> getvalInod(){
		orderinfo = new ArrayList<Object>();
		inod.blockRequest();
		for(int i=0; i<14; i++){
			while(inod.getHeaderValue(i)==null) i++;
			orderinfo.add(inod.getHeaderValue(i));			
		}		
		return orderinfo;
	}
	
	public void setvalInodcredit(String sb, String gdmgcode, String code, long quan, long unitprice, String callcode, long loanperiod ){
		od = new odBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		inodcredit.setInputValue(0, (Object)sb);
		inodcredit.setInputValue(1, (Object)accountNum[0]);
		inodcredit.setInputValue(2, (Object)gdmgcode);
		inodcredit.setInputValue(3, (Object)code);
		inodcredit.setInputValue(4, (Object)quan);
		inodcredit.setInputValue(5, (Object)unitprice);
		inodcredit.setInputValue(7, (Object)0);
		inodcredit.setInputValue(8, (Object)callcode);
		if(sb == "1")
		{
			inodcredit.setInputValue(9, (Object)loanperiod);
		}
		inodcredit.blockRequest();
		/*
		'주문 결과와 주문결과 메시지를 얻어옵니다
	    MsgBox "GetDibStatus:" + CStr(m_0311.GetDibStatus) + " GetDibMsg1:" + m_0311.GetDibMsg1
	    'GetDibStatus 결과값
	        '정상주문결과는 0 리턴,
	        '주문가격오류, 주문가능금액부족등의 에러는 -1을 리턴
	    'GetDibMsg1 값
	        '주문결과 메시지를 얻을수 있습니다.
		 */
	}
	public ArrayList<Object> getvalInodcredit(){
		orderinfo = new ArrayList<Object>();
		inodcredit.blockRequest();
		for(int i=0; i<16; i++){
			while(inodcredit.getHeaderValue(i)==null) i++;
			orderinfo.add(inodcredit.getHeaderValue(i));			
		}		
		return orderinfo;
	}
	
	public void setvalInodAlter(long ordercode, String gdmgcode, String code, long quan, long unitprice){
		od = new odBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		inodalter.setInputValue(1, (Object)ordercode);
		inodalter.setInputValue(2, (Object)accountNum[0]);
		inodalter.setInputValue(3, (Object)gdmgcode);
		inodalter.setInputValue(4, (Object)code);
		inodalter.setInputValue(5, (Object)quan);
		inodalter.setInputValue(6, (Object)unitprice);		
		inodalter.blockRequest();
		/*
		'주문 결과와 주문결과 메시지를 얻어옵니다
	    MsgBox "GetDibStatus:" + CStr(m_0311.GetDibStatus) + " GetDibMsg1:" + m_0311.GetDibMsg1
	    'GetDibStatus 결과값
	        '정상주문결과는 0 리턴,
	        '주문가격오류, 주문가능금액부족등의 에러는 -1을 리턴
	    'GetDibMsg1 값
	        '주문결과 메시지를 얻을수 있습니다.
		 */
	}
	public ArrayList<Object> getvalInodAlter(){
		orderinfo = new ArrayList<Object>();
		inodalter.blockRequest();
		for(int i=0; i<10; i++){
			while(inodalter.getHeaderValue(i)==null) i++;
			orderinfo.add(inodalter.getHeaderValue(i));			
		}		
		return orderinfo;
	}
	
	public void setvalInodCancle(long ordercode, String gdmgcode, String code, long quan, long unitprice, String callcode){
		od = new odBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		inodcancle.setInputValue(1, (Object)ordercode);
		inodcancle.setInputValue(2, (Object)accountNum[0]);
		inodcancle.setInputValue(3, (Object)gdmgcode);
		inodcancle.setInputValue(4, (Object)code);
		inodcancle.setInputValue(5, (Object)quan);			
		inodcancle.blockRequest();
		/*
		'주문 결과와 주문결과 메시지를 얻어옵니다
	    MsgBox "GetDibStatus:" + CStr(m_0311.GetDibStatus) + " GetDibMsg1:" + m_0311.GetDibMsg1
	    'GetDibStatus 결과값
	        '정상주문결과는 0 리턴,
	        '주문가격오류, 주문가능금액부족등의 에러는 -1을 리턴
	    'GetDibMsg1 값
	        '주문결과 메시지를 얻을수 있습니다.
		 */
	}
	public ArrayList<Object> getvalInodCancle(){
		orderinfo = new ArrayList<Object>();
		inodcancle.blockRequest();
		for(int i=0; i<9; i++){
			while(inodcancle.getHeaderValue(i)==null) i++;
			orderinfo.add(inodcancle.getHeaderValue(i));			
		}		
		return orderinfo;
	}
	
	public void setvalInodTypeccl(long ordercode, String gdmgcode, String code, long quan, long unitprice, String callcode){
		od = new odBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		inodtypeccl.setInputValue(1, (Object)ordercode);
		inodtypeccl.setInputValue(2, (Object)accountNum[0]);
		inodtypeccl.setInputValue(3, (Object)gdmgcode);
		inodtypeccl.setInputValue(4, (Object)code);
		inodtypeccl.setInputValue(5, (Object)quan);
		inodtypeccl.setInputValue(6, (Object)unitprice);
		inodtypeccl.setInputValue(8, (Object)0);
		inodtypeccl.setInputValue(9, (Object)callcode);
		inodtypeccl.blockRequest();
		/*
		'주문 결과와 주문결과 메시지를 얻어옵니다
	    MsgBox "GetDibStatus:" + CStr(m_0311.GetDibStatus) + " GetDibMsg1:" + m_0311.GetDibMsg1
	    'GetDibStatus 결과값
	        '정상주문결과는 0 리턴,
	        '주문가격오류, 주문가능금액부족등의 에러는 -1을 리턴
	    'GetDibMsg1 값
	        '주문결과 메시지를 얻을수 있습니다.
		 */
	}
	public ArrayList<Object> getvalInodTypeccl(){
		orderinfo = new ArrayList<Object>();
		inodtypeccl.blockRequest();
		for(int i=0; i<15; i++){
			while(inodtypeccl.getHeaderValue(i)==null) i++;
			orderinfo.add(inodtypeccl.getHeaderValue(i));			
		}		
		return orderinfo;
	}
}
