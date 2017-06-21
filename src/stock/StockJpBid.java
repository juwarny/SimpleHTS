package stock;

import com4j.*;
import test.cpdib.*;
import test.cpdib.events._IDibEvents;

import java.awt.Event;
import java.util.*;

public class StockJpBid {
	private IDib stockjpbid;//주식 종목의 현재가에 관련된 데이터	
	private ArrayList<Object> stkinfo;
	private ArrayList<Object[]> stkdata;
	private ArrayList<Object> stkdata_row;

	public StockJpBid(){
		stockjpbid = test.cpdib.ClassFactory.createStockJpbid2();
		
	}
	public void setvalStockJpBid(String code){
		stockjpbid.setInputValue(0, (Object)code);
	}
	public ArrayList<Object> getHvalStockmst(){
		stkinfo = new ArrayList<Object>();
		stockjpbid.blockRequest();
		for(int i = 0; i<12; i++) {
			while(stockjpbid.getHeaderValue(i)==null) i++;
			stkinfo.add(stockjpbid.getHeaderValue(i));			
		}	
		return stkinfo;	
	}
	public ArrayList<Object[]> getDvalStockmst(){
		stkdata = new ArrayList<Object[]>();		
		stockjpbid.blockRequest();
		for(int i = 0; i<10; i++) {
			stkdata_row = new ArrayList<Object>();
			for(int j=0; j<6; j++){
				stkdata_row.add(stockjpbid.getDataValue(j, i));
			}			
			stkdata.add(stkdata_row.toArray());			
		}	
		return stkdata;	
	}


	public static void main(String[] args){
		StockJpBid c = new StockJpBid();
		c.setvalStockJpBid("A036570");
		
		for(Object[] j : c.getDvalStockmst()){
			for (Object i: j){
				System.out.println(i);
			}
			
		}
	}

	
}
