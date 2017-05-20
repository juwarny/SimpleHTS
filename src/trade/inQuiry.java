package trade;

import java.util.ArrayList;
import com4j.*;
import test.cptrade.*;

public class inQuiry {
	private ICpTdDib stpaydeposit = test.cptrade.ClassFactory.createCpTd0732();
	private ICpTdDib purchaseavail = test.cptrade.ClassFactory.createCpTdNew5331A();
	private ICpTdDib sellableavail = test.cptrade.ClassFactory.createCpTdNew5331B();
	private ICpTdDib dayNconclud = test.cptrade.ClassFactory.createCpTd5339();
	private ICpTdDib dayconclud = test.cptrade.ClassFactory.createCpTd5341();
	private ICpTdDib ytdayconcri = test.cptrade.ClassFactory.createCpTd5342();
	private ICpTdDib concribalance = test.cptrade.ClassFactory.createCpTd6033();
	private ArrayList<Object> quiryinfo;
	private odBeforeinit od;
	private Object[] accountNum;
	
	
	public void setvalStpay(int set, long accountnum) {
		stpaydeposit.setInputValue(set, (Object)accountnum);
		stpaydeposit.blockRequest();
	}
	public void setvalStpay(int set, String gdmgcode) {
		stpaydeposit.setInputValue(set, (Object)gdmgcode);
		stpaydeposit.blockRequest();
	}
	public ArrayList<Object> getvalStpay(){
		quiryinfo = new ArrayList<Object>();
		stpaydeposit.blockRequest();
		for(int i=0; i<67; i++){
			while(stpaydeposit.getHeaderValue(i)==null) i++;
			quiryinfo.add(stpaydeposit.getHeaderValue(i));			
		}		
		return quiryinfo;
	}
	
	public void setvalPurchase(String gdmgcode, String code, String callcode, long unitprice, String recimarginYN, String quirycode ){
		od = new odBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		purchaseavail.setInputValue(0, (Object)accountNum[0]);
		purchaseavail.setInputValue(1, (Object)gdmgcode);
		purchaseavail.setInputValue(2, (Object)code);
		purchaseavail.setInputValue(3, (Object)callcode);		
		purchaseavail.setInputValue(5, (Object)recimarginYN);
		purchaseavail.setInputValue(6, (Object)quirycode);
		if(quirycode == "2"){
			purchaseavail.setInputValue(4, (Object)unitprice);
		}
		purchaseavail.blockRequest();		
	}
	public ArrayList<Object> getvalPurchase(){
		quiryinfo = new ArrayList<Object>();
		purchaseavail.blockRequest();
		for(int i=0; i<56; i++){
			while(purchaseavail.getHeaderValue(i)==null) i++;
			quiryinfo.add(purchaseavail.getHeaderValue(i));			
		}		
		return quiryinfo;
	}
	
	public void setvalSella(String gdmgcode, String code, String stbdcode, String cacrdcode, 
							String datecrdloan, String crdloancode, String purdate, 
							String stbdbalcode, String taxcode, long quirynum ){
		od = new odBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		sellableavail.setInputValue(0, (Object)accountNum[0]);
		sellableavail.setInputValue(1, (Object)gdmgcode);
		sellableavail.setInputValue(2, (Object)code);
		sellableavail.setInputValue(3, (Object)stbdcode);		
		sellableavail.setInputValue(4, (Object)cacrdcode);
		sellableavail.setInputValue(5, (Object)datecrdloan);
		sellableavail.setInputValue(6, (Object)crdloancode);
		sellableavail.setInputValue(7, (Object)purdate);
		sellableavail.setInputValue(8, (Object)stbdbalcode);
		sellableavail.setInputValue(9, (Object)taxcode);
		sellableavail.setInputValue(10, (Object)quirynum);
		
		sellableavail.blockRequest();		
	}
	public ArrayList<Object> getvalSella(){
		quiryinfo = new ArrayList<Object>();
		sellableavail.blockRequest();
		for(int i=0; i<19; i++){
			while(sellableavail.getHeaderValue(i)==null) i++;
			quiryinfo.add(sellableavail.getHeaderValue(i));			
		}		
		return quiryinfo;
	}
	
	public void setvalDayNconclud(String gdmgcode, String code, String callcode, String sortcode, String qrclosecode, long quirynum ){
		od = new odBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		dayNconclud.setInputValue(0, (Object)accountNum[0]);
		dayNconclud.setInputValue(1, (Object)gdmgcode);
		dayNconclud.setInputValue(3, (Object)code);		
		dayNconclud.setInputValue(4, (Object)callcode);
		dayNconclud.setInputValue(5, (Object)sortcode);
		dayNconclud.setInputValue(6, (Object)qrclosecode);
		dayNconclud.setInputValue(7, (Object)quirynum);

		dayNconclud.blockRequest();		
	}
	public ArrayList<Object> getvalDayNconclud(int conti){
		quiryinfo = new ArrayList<Object>();
		dayNconclud._continue(conti);
		dayNconclud.blockRequest();
		for(int i=0; i<29; i++){
			while(dayNconclud.getHeaderValue(i)==null) i++;
			quiryinfo.add(dayNconclud.getHeaderValue(i));			
		}		
		return quiryinfo;
	}
	
	public void setvalDayconclud(String gdmgcode, String code, long startcallcode, String sortcode, long quirynum, String quirycode){
		od = new odBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		dayconclud.setInputValue(0, (Object)accountNum[0]);
		dayconclud.setInputValue(1, (Object)gdmgcode);
		dayconclud.setInputValue(2, (Object)code);		
		dayconclud.setInputValue(3, (Object)startcallcode);
		dayconclud.setInputValue(4, (Object)sortcode);
		dayconclud.setInputValue(5, (Object)quirynum);
		dayconclud.setInputValue(6, (Object)quirycode);

		dayconclud.blockRequest();		
	}
	public ArrayList<Object> getvalDayconclud(int conti){
		quiryinfo = new ArrayList<Object>();
		dayconclud._continue(conti);
		dayconclud.blockRequest();
		for(int i=0; i<37; i++){
			while(dayconclud.getHeaderValue(i)==null) i++;
			quiryinfo.add(dayconclud.getHeaderValue(i));			
		}		
		return quiryinfo;
	}
	
	
	public void setvalYtdaycon(String gdmgcode,  long quirynum, String quirydatecode, String quiryitemcode){
		od = new odBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		ytdayconcri.setInputValue(0, (Object)accountNum[0]);
		ytdayconcri.setInputValue(1, (Object)gdmgcode);
		ytdayconcri.setInputValue(2, (Object)quirynum);		
		ytdayconcri.setInputValue(3, (Object)quirydatecode);
		ytdayconcri.setInputValue(4, (Object)quiryitemcode);
		
		ytdayconcri.blockRequest();		
	}
	public ArrayList<Object> getHvalYtdaycon(int conti){
		quiryinfo = new ArrayList<Object>();
		ytdayconcri._continue(conti);
		ytdayconcri.blockRequest();
		for(int i=0; i<11; i++){
			while(ytdayconcri.getHeaderValue(i)==null) i++;
			quiryinfo.add(ytdayconcri.getHeaderValue(i));			
		}		
		return quiryinfo;
	}
	public ArrayList<Object> getDvalYtdaycon(int conti, int index){
		quiryinfo = new ArrayList<Object>();
		ytdayconcri._continue(conti);
		ytdayconcri.blockRequest();
		for(int i=0; i<25; i++){
			while(ytdayconcri.getDataValue(i, index)==null) i++;
			quiryinfo.add(ytdayconcri.getDataValue(i, index));			
		}		
		return quiryinfo;
	}
	
	public void setvalConcribalance(String gdmgcode,  long quirynum){
		od = new odBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
		
		concribalance.setInputValue(0, (Object)accountNum[0]);
		concribalance.setInputValue(1, (Object)gdmgcode);
		concribalance.setInputValue(2, (Object)quirynum);		
				
		concribalance.blockRequest();		
	}
	public ArrayList<Object> getHvalConcribalance(int conti){
		quiryinfo = new ArrayList<Object>();
		concribalance._continue(conti);
		concribalance.blockRequest();
		for(int i=0; i<13; i++){
			while(concribalance.getHeaderValue(i)==null) i++;
			quiryinfo.add(concribalance.getHeaderValue(i));			
		}		
		return quiryinfo;
	}
	public ArrayList<Object> getDvalConcribalance(int conti, int index){
		quiryinfo = new ArrayList<Object>();
		concribalance._continue(conti);
		concribalance.blockRequest();
		for(int i=0; i<18; i++){
			while(concribalance.getDataValue(i, index)==null) i++;
			quiryinfo.add(concribalance.getDataValue(i, index));			
		}		
		return quiryinfo;
	}
	
}
