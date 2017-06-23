package stock;

import com4j.*;
import test.cpdib.*;
import test.cpdib.events._IDibEvents;

import java.awt.Event;
import java.util.*;

public class StockJpBid {
	private IDib stockjpbid;//주식/ETF/ELW 종목 매도,매수에 관한 1차 ~10차 호가/LP호가 및 호가잔량 수신
	private ArrayList<Object> stkinfo;
	private ArrayList<Object[]> stkdata;
	private ArrayList<Object> stkdata_row;

	public StockJpBid(){
		stockjpbid = test.cpdib.ClassFactory.createStockJpbid2();		
	}
	public void setvalStockJpBid(String code){//0 - (string) 종목 코드
		stockjpbid.setInputValue(0, (Object)code);
	}	
	public ArrayList<Object> getHvalStockmst(){//헤더 데이터를 반환합니다
		stkinfo = new ArrayList<Object>();
		stockjpbid.blockRequest();
		for(int i = 0; i<12; i++) {
			while(stockjpbid.getHeaderValue(i)==null) i++;
			stkinfo.add(stockjpbid.getHeaderValue(i));			
		}	
		return stkinfo;	
	}

	/*
	 * 0 - (string) 종목 코드 
	 * 1 - (long) COUNT [10차 호가 변경으로 10을 고정리턴] 
	 * 3 - (short) 시각
	 * 4 - (long) 총매도잔량 
	 * 5 - (long) 총매도잔량대비 
	 * 6 - (long) 총매수잔량
	 * 7 - (long) 총매수잔량대비 
	 * 8 - (long) 시간외총매도잔량 
	 * 9 - (long) 시간외총매도잔량대비 
	 * 10 - (long) 시간외총매수잔량 
	 * 11 - (long) 시간외총매수잔량대비
	 */
	public ArrayList<Object[]> getDvalStockmst(){//데이터를 리스트로 가져옵니다.
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
	/*
	 * 0 - (long) 매도호가 
	 * 1 - (long) 매수호가 
	 * 2 - (long) 매도잔량 
	 * 3 - (long) 매수잔량 
	 * 4 - (long) 매도잔량대비 
	 * 5 - (long) 매수잔량대비
	 */
	
}
