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
	
	
	public void setAccountNum(Object[] accountNum) {
		this.accountNum = accountNum;
	}
	public Object[] getAccountNum() {
		return accountNum;
	}
	public Object[] getAccountfilt() {
		return accountfilt;
	}
	
	
	public void tradeInit(){//주문을 하기 위한 예비 과정를 수행한다 계좌비밀번호 입력창이 생성됨
		if(od.tradeInit(0)==0){
			setAccountNum((Object[])od.accountNumber());
		}
		else{
			System.out.println("정상적으로 입력되지 않았습니다.");
		}		
	}
	
	
	public void GoodsList(int i){//좀더 살펴봐야할 듯
		if(i==-1)//전체  사인온한 계좌에 대해서 필터값에 따른 계좌 목록을 배열로 반환한다.
		{
			accountfilt = (Object[])od.goodsList((String)accountNum[0], test.cptrade.CPE_ACC_GOODS.CPC_ALL_ACC);
		}
		else{
			accountfilt = (Object[])od.goodsList((String)accountNum[0], test.cptrade.CPE_ACC_GOODS.CPC_STOCK_ACC);
		}
				
	}
}
