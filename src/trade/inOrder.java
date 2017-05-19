package trade;

import java.util.ArrayList;

import com4j.*;
import test.cptrade.*;

public class inOrder {
	private ICpTdDib inod = test.cptrade.ClassFactory.createCpTd0311();//�峻�ֹ�
	private ICpTdDib inodcredit = test.cptrade.ClassFactory.createCpTd0312();//�峻 �ſ��ֺ�
	private ICpTdDib inodalter = test.cptrade.ClassFactory.createCpTd0313();//�峻�ֹ� ����
	private ICpTdDib inodcancle = test.cptrade.ClassFactory.createCpTd0314();//�峻 �ֹ����
	private ICpTdDib inodtypeccl = test.cptrade.ClassFactory.createCpTd0303();//�峻 �ֹ� ���� ����
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
		'�ֹ� ����� �ֹ���� �޽����� ���ɴϴ�
	    MsgBox "GetDibStatus:" + CStr(m_0311.GetDibStatus) + " GetDibMsg1:" + m_0311.GetDibMsg1
	    'GetDibStatus �����
	        '�����ֹ������ 0 ����,
	        '�ֹ����ݿ���, �ֹ����ɱݾ׺������� ������ -1�� ����
	    'GetDibMsg1 ��
	        '�ֹ���� �޽����� ������ �ֽ��ϴ�.
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
		'�ֹ� ����� �ֹ���� �޽����� ���ɴϴ�
	    MsgBox "GetDibStatus:" + CStr(m_0311.GetDibStatus) + " GetDibMsg1:" + m_0311.GetDibMsg1
	    'GetDibStatus �����
	        '�����ֹ������ 0 ����,
	        '�ֹ����ݿ���, �ֹ����ɱݾ׺������� ������ -1�� ����
	    'GetDibMsg1 ��
	        '�ֹ���� �޽����� ������ �ֽ��ϴ�.
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
		'�ֹ� ����� �ֹ���� �޽����� ���ɴϴ�
	    MsgBox "GetDibStatus:" + CStr(m_0311.GetDibStatus) + " GetDibMsg1:" + m_0311.GetDibMsg1
	    'GetDibStatus �����
	        '�����ֹ������ 0 ����,
	        '�ֹ����ݿ���, �ֹ����ɱݾ׺������� ������ -1�� ����
	    'GetDibMsg1 ��
	        '�ֹ���� �޽����� ������ �ֽ��ϴ�.
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
		'�ֹ� ����� �ֹ���� �޽����� ���ɴϴ�
	    MsgBox "GetDibStatus:" + CStr(m_0311.GetDibStatus) + " GetDibMsg1:" + m_0311.GetDibMsg1
	    'GetDibStatus �����
	        '�����ֹ������ 0 ����,
	        '�ֹ����ݿ���, �ֹ����ɱݾ׺������� ������ -1�� ����
	    'GetDibMsg1 ��
	        '�ֹ���� �޽����� ������ �ֽ��ϴ�.
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
		'�ֹ� ����� �ֹ���� �޽����� ���ɴϴ�
	    MsgBox "GetDibStatus:" + CStr(m_0311.GetDibStatus) + " GetDibMsg1:" + m_0311.GetDibMsg1
	    'GetDibStatus �����
	        '�����ֹ������ 0 ����,
	        '�ֹ����ݿ���, �ֹ����ɱݾ׺������� ������ -1�� ����
	    'GetDibMsg1 ��
	        '�ֹ���� �޽����� ������ �ֽ��ϴ�.
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
