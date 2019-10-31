package stock;

import java.util.*;
import com4j.*;
import test.CpSysDib.*;

public class StockChart {//주식, 업종, ELW의 차트데이터를 수신합니다.
	private ISysDib stkchart;
	private long ulong;	
	private ArrayList<Object> getlist;
	private ArrayList<Object> getfield;
	private ArrayList<Object[]> series;
	public StockChart(){
		stkchart = test.CpSysDib.ClassFactory.createStockChart();
	}
	public Object invertUnsingedLong(String s_long){
		ulong = Long.parseUnsignedLong(s_long);
		return ulong;
	}
	//요청형식이 기간일 경우
	public void setvalStkchart(String code, char term_count, String s_close_day, String s_open_day, long[] field, int time_option, int gap_c, int adj_price, int volume_code){
		stkchart.setInputValue(0, (Object)code);
		stkchart.setInputValue(1, (Object)term_count);
		setvalTerm_Count(s_close_day, s_open_day);
		for(int i=0; i<field.length; i++){
			stkchart.setInputValue(5, (Object)field);
		}	
		stkchart.setInputValue(6, (Object)time_option);
		stkchart.setInputValue(8, (Object)gap_c);
		stkchart.setInputValue(9, (Object)adj_price);
		stkchart.setInputValue(10,(Object)volume_code);		
	}
	//요청 형식이 개수일 경우
	public void setvalStkchart(String code, int term_count, String reqcount, ArrayList<Object> field, int time_option, int gap_c, int adj_price, int volume_code){
		stkchart.setInputValue(0, (Object)code);
		stkchart.setInputValue(1, (Object)term_count);
		setvalTerm_Count(reqcount);		
		stkchart.setInputValue(5, (Object)field.toArray());	//필드 값을 요청 (날짜, 시간, 고가, 저가, 종가 등등...)	
		stkchart.setInputValue(6, (Object)time_option);
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
	//헤더 데이터를 받아와서 리스트에 저장합니다.
	public ArrayList<Object> getHvalStkchart(){
		stkchart.blockRequest();
		getlist = new ArrayList<Object>();
		for(int i=0; i<24; i++){
			while(stkchart.getHeaderValue(i)==null) i++;
			getlist.add(stkchart.getHeaderValue(i));
		}		
		return getlist;		
	}
	//요청한 필드 값을 전송받아 리스트에 저장합니다.
	public ArrayList<Object[]> getDvalStkchart(int counts){
		stkchart.blockRequest();
		series = new ArrayList<Object[]>();
		
		int fields_num = Integer.parseInt(getHvalStkchart().get(1).toString());
		
		for(int i=0; i<counts; i++){
			getfield = new ArrayList<Object>();
			for(int j=0; j<fields_num; j++){
				getfield.add(stkchart.getDataValue(j, i));
				System.out.println(stkchart.getDataValue(j, i).toString());
			}
			series.add(getfield.toArray());
		}		
		return series;		
	}
}
