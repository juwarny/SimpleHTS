package account;

import java.util.*;
import com4j.*;
import test.cpdib.*;

public class AccountInfo {
	private IDib cpConclu;//주식주문한 것에 대한 체결 내역을 요청하고 수신합니다

	private ArrayList<Object> sellbuyInfo;
	
	public AccountInfo(){
		cpConclu = test.cpdib.ClassFactory.createCpConclusion();//주식주문한 것에 대한 체결 내역을 요청하고 수신합니다
	}
		
	public ArrayList<Object> sellbuyinfo(){
		sellbuyInfo = new ArrayList<Object>();
		cpConclu.subscribe();//주문 체결 data 수신을 신청한다.

		for(int i=1; i<24; i++)//체결 내역 정보를 전송받습니다
		{
			sellbuyInfo.add(cpConclu.getHeaderValue(i));
		}				
		cpConclu.unsubscribe();//주문 체결 data 수신을 해지한다

		return sellbuyInfo;		
	}	
}
