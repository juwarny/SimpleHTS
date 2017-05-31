package stock;

import java.util.*;
import com4j.*;
import test.CpSysDib.*;

public class StockChart {
	private ISysDib stkchart;
	private long ulong;	
	private ArrayList<Object> getlist;
	public StockChart(){
		stkchart = test.CpSysDib.ClassFactory.createStockChart();
	}
	public Object invertUnsingedLong(String s_long){
		ulong = Long.parseUnsignedLong(s_long);
		return ulong;
	}	
	public void setvalStkchart(String code, char term_count, String s_close_day, String s_open_day, long[] field, char time_option, char gap_c, char adj_price, char volume_code){
		stkchart.setInputValue(0, (Object)code);
		stkchart.setInputValue(1, (Object)term_count);
		setvalTerm_Count(s_close_day, s_open_day);
		stkchart.setInputValue(5, (Object)field);
		stkchart.setInputValue(6, (Object)time_option);
		stkchart.setInputValue(7, invertUnsingedLong("1"));
		stkchart.setInputValue(8, (Object)gap_c);
		stkchart.setInputValue(9, (Object)adj_price);
		stkchart.setInputValue(10,(Object)volume_code);		
	}
	public void setvalStkchart(String code, char term_count, String reqcount, long[] field, char time_option, char gap_c, char adj_price, char volume_code){
		stkchart.setInputValue(0, (Object)code);
		stkchart.setInputValue(1, (Object)term_count);
		setvalTerm_Count(reqcount);
		stkchart.setInputValue(5, (Object)field);
		stkchart.setInputValue(6, (Object)time_option);
		stkchart.setInputValue(7, invertUnsingedLong("1"));
		stkchart.setInputValue(8, (Object)gap_c);
		stkchart.setInputValue(9, (Object)adj_price);
		stkchart.setInputValue(10,(Object)volume_code);
	}
	public void setvalTerm_Count(String s_close_day, String s_open_day){
		stkchart.setInputValue(2, invertUnsingedLong(s_close_day));
		stkchart.setInputValue(3, invertUnsingedLong(s_open_day));
		
	}
	public void setvalTerm_Count(String reqcount){
		stkchart.setInputValue(4, invertUnsingedLong(reqcount));
	}
	
	public ArrayList<Object> getvalStkchart(){
		stkchart.blockRequest();
		for(int i=0; i<24; i++){
			getlist.add(stkchart.getHeaderValue(i));
		}		
		return getlist;		
	}
}
