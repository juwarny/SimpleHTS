package trade;

import java.util.ArrayList;
import com4j.*;
import test.cptrade.*;

public class OdBeforeinit {
	private ICpTdUtil od;
	private Object[] accountNum;
	private Object[] accountfilt;
	
	public OdBeforeinit(){
		od = test.cptrade.ClassFactory.createCpTdUtil();//�ֹ� ������Ʈ�� ����ϱ� ���� �ʿ��� �ʱ�ȭ �������� �����Ѵ�.
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
	
	
	public void tradeInit(){//�ֹ��� �ϱ� ���� ���� ������ �����Ѵ� ���º�й�ȣ �Է�â�� ������
		if(od.tradeInit(0)==0){
			setAccountNum((Object[])od.accountNumber());
		}
		else{
			System.out.println("���������� �Էµ��� �ʾҽ��ϴ�.");
		}		
	}
	
	
	public void GoodsList(int i){//���� ��������� ��
		if(i==-1)//��ü  ���ο��� ���¿� ���ؼ� ���Ͱ��� ���� ���� ����� �迭�� ��ȯ�Ѵ�.
		{
			accountfilt = (Object[])od.goodsList((String)accountNum[0], test.cptrade.CPE_ACC_GOODS.CPC_ALL_ACC);
		}
		else{
			accountfilt = (Object[])od.goodsList((String)accountNum[0], test.cptrade.CPE_ACC_GOODS.CPC_STOCK_ACC);
		}
				
	}
}
