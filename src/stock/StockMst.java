package stock;

import com4j.*;
import test.cpdib.*;
import java.util.*;
//내용이 많아서 사이보스 help 문서를 참고
public class StockMst {
	private IDib stockmst;//주식 종목의 현재가에 관련된 데이터
	private IDib stockmst2;//주식 복수 종목에 대해 일괄 조회를 요청하고 수신한다
	private IDib stockmstm;//주식 복수 종목에 대해 간단한 내용을 일괄 조회 요청하고 수신한다
	private ArrayList<Object> stkinfo;
	private ArrayList<Object> stkinfo2;
	private ArrayList<Object> stkinfom;
	
	public StockMst(){
		stockmst = test.cpdib.ClassFactory.createStockMst();
		stockmst2 = test.cpdib.ClassFactory.createStockMst2();
		stockmstm = test.cpdib.ClassFactory.createStockMstm();
	}
	
	
	
	public void setvalStockmst(String code){
		stockmst.setInputValue(0, (Object)code);
	}
	public ArrayList<Object> getHvalStockmst(){
		stkinfo = new ArrayList<Object>();
		stockmst.blockRequest();
		for(int i = 0; i<71; i++) {
			while(stockmst.getHeaderValue(i)==null) i++;
			stkinfo.add(stockmst.getHeaderValue(i));			
		}		
		return stkinfo;		
	}
	
	
	
	
	public void setvalStockmst2(String code){
		stockmst2.setInputValue(0, (Object)code);
	}
	public short getHvalStockmst2(){
		stockmst2.blockRequest();
		return (short)stockmst2.getHeaderValue(0); 		
	}
	public ArrayList<Object> getDvalStockmst2(int index){
		stkinfo2 = new ArrayList<Object>();
		stockmst2.blockRequest();
		for(int i = 0; i<30; i++) {
			while(stockmst2.getDataValue(i, index)==null) i++;
			stkinfo2.add(stockmst2.getDataValue(i, index));			
		}
		return stkinfo2;		
	}
	
	
	
	
	public void setvalStockmstm(String code){
		stockmstm.setInputValue(0, (Object)code);
	}
	public ArrayList<Object> getHvalStockmstm(){
		stkinfom = new ArrayList<Object>();
		stockmstm.blockRequest();
		for(int i = 0; i<12; i++) {
			while(stockmst.getHeaderValue(i)==null) i++;
			stkinfom.add(stockmst.getHeaderValue(i));			
		}		
		return stkinfom;		
	}	
	
}
