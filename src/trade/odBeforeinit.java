package trade;

import java.util.ArrayList;
import com4j.*;
import test.cptrade.*;

public class odBeforeinit {
	private ICpTdUtil od = test.cptrade.ClassFactory.createCpTdUtil();
	private Object[] accountNum;
	private Object[] accountfilt;
	
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
			accountNum = (Object[])od.accountNumber();
		}
		else{
			System.out.println("정상적으로 입력되지 않았습니다.");
		}
		
	}
	public void GoodsList(CPE_ACC_GOODS i){
		accountfilt = (Object[])od.goodsList((String)accountNum[0], i);		
	}
	public static void main(String[]args){
		
	}
}
