package account;

import java.util.*;
import com4j.*;
import test.cpdib.*;

public class AccountInfo {
	private IDib cpConclu;
	private ArrayList<Object> sellbuyInfo;
	
	public AccountInfo(){
		cpConclu = test.cpdib.ClassFactory.createCpConclusion();//주식주문한 것에 대한 체결 내역을 요청하고 수신합니다
	}
		
	public ArrayList<Object> sellbuyinfo(){
		sellbuyInfo = new ArrayList<Object>();
		cpConclu.subscribe();
		for(int i=1; i<24; i++)
		{
			sellbuyInfo.add(cpConclu.getHeaderValue(i));
		}				
		cpConclu.unsubscribe();
		return sellbuyInfo;		
	}
	public static void main(String[]args){
		AccountInfo ainfo = new AccountInfo();
		ArrayList<Object> sellbuyInfo = ainfo.sellbuyinfo();
		for(Object i : sellbuyInfo){
			i.toString();
		}
	}	
}
