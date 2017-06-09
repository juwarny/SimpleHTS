package trade;

import java.util.ArrayList;
import com4j.*;
import test.cptrade.*;

public class OdBeforeinit {
	private ICpTdUtil od;
	private Object[] accountNum;
	private Object[] accountfilt;
	
	public OdBeforeinit(){
		od = test.cptrade.ClassFactory.createCpTdUtil();//주문 오브젝트를 사용하기 위해 필요한 초기화 과정들을 수행한다.
	}
	
	public Object[] getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(Object[] accountNum) {
		this.accountNum = accountNum;
	}
	public Object[] getAccountfilt() {
		return accountfilt;
	}
	public void setAccountfilt(Object[] accountfilt) {
		this.accountfilt = accountfilt;
	}			
	public void tradeInit(){
		if(od.tradeInit(0)==0){
			setAccountNum((Object[])od.accountNumber());
		}
		else{
			System.out.println("정상적으로 입력되지 않았습니다.");
		}		
	}
	public void GoodsList(int i){//좀더 살펴봐야할 듯
		if(i==-1)
		{
			accountfilt = (Object[])od.goodsList((String)accountNum[0], test.cptrade.CPE_ACC_GOODS.CPC_ALL_ACC);
		}
		else{
			accountfilt = (Object[])od.goodsList((String)accountNum[0], test.cptrade.CPE_ACC_GOODS.CPC_STOCK_ACC);
		}
				
	}
	public static void main(String[]args){
		/*
		OdBeforeinit init = new OdBeforeinit();
		Object[] accountNum;
		Object[] accountfilt;
		
		init.tradeInit();
		accountNum = init.getAccountNum();
		for(int i = 0; i<accountNum.length; i++){
			System.out.println(accountNum[i]);
		}
		 init.GoodsList(-1);
		accountfilt = init.getAccountfilt();
		for(int i = 0; i<accountfilt.length; i++){
			System.out.println(accountfilt[i]);
		}
		*/
	}
}
