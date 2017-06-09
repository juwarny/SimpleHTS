package stock;

import java.util.*;
import com4j.*;
import test.CpSysDib.*;

public class StockChart {
	private ISysDib stkchart;
	private long ulong;	
	private ArrayList<Object> getlist;
	private ArrayList<Object> getfield;
	public StockChart(){
		stkchart = test.CpSysDib.ClassFactory.createStockChart();
	}
	public Object invertUnsingedLong(String s_long){
		ulong = Long.parseUnsignedLong(s_long);
		return ulong;
	}	
	public void setvalStkchart(String code, char term_count, String s_close_day, String s_open_day, long[] field, int time_option, int gap_c, int adj_price, int volume_code){
		stkchart.setInputValue(0, (Object)code);
		stkchart.setInputValue(1, (Object)term_count);
		setvalTerm_Count(s_close_day, s_open_day);
		for(int i=0; i<field.length; i++){
			stkchart.setInputValue(5, (Object)field);
		}	
		stkchart.setInputValue(6, (Object)time_option);
		//stkchart.setInputValue(7, invertUnsingedLong("1"));
		stkchart.setInputValue(8, (Object)gap_c);
		stkchart.setInputValue(9, (Object)adj_price);
		stkchart.setInputValue(10,(Object)volume_code);		
	}
	public void setvalStkchart(String code, int term_count, String reqcount, ArrayList<Object> field, int time_option, int gap_c, int adj_price, int volume_code){
		stkchart.setInputValue(0, (Object)code);
		stkchart.setInputValue(1, (Object)term_count);
		setvalTerm_Count(reqcount);		
		stkchart.setInputValue(5, (Object)field.toArray());		
		stkchart.setInputValue(6, (Object)time_option);
		//stkchart.setInputValue(7, (Object)((short) 1));
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
	
	public ArrayList<Object> getHvalStkchart(){
		stkchart.blockRequest();
		getlist = new ArrayList<Object>();
		for(int i=0; i<24; i++){
			while(stkchart.getHeaderValue(i)==null) i++;
			getlist.add(stkchart.getHeaderValue(i));
		}		
		return getlist;		
	}
	public ArrayList<Object> getDvalStkchart(int counts){
		stkchart.blockRequest();
		getlist = new ArrayList<Object>();
		getfield = new ArrayList<Object>();
		for(int i=0; i<counts; i++){
			while(stkchart.getHeaderValue(i)==null) i++;
			for(int j=0; j<6; j++){
				getfield.add(stkchart.getDataValue(j, i));
				System.out.println(stkchart.getDataValue(j, i).toString());
			}
			getlist.add(getfield);
		}		
		return getlist;		
	}
	
	public static void main(String[] args){
		StockChart stc = new StockChart();
		ArrayList<Object> a = new ArrayList<Object>();
		ArrayList<Object> getlist = new ArrayList<Object>();
		a.add(0);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		
		
		stc.setvalStkchart("A003540", 50, "1000", a, 'm', '0', '1', '3');
		getlist = stc.getHvalStkchart();
		for(int i=0; i<getlist.size(); i++){
			System.out.println(getlist.get(i).toString());
		}
		
		Object[] s = (Object[])getlist.get(2);
		for(int i=0; i<s.length; i++){
			System.out.println(s[i]);
		}
		
		stc.getDvalStkchart(1000);
	}
}
