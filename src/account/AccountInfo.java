package account;

import java.util.*;
import com4j.*;
import test.cpdib.*;

public class AccountInfo {
	private IDib cpConclu;//�ֽ��ֹ��� �Ϳ� ���� ü�� ������ ��û�ϰ� �����մϴ�

	private ArrayList<Object> sellbuyInfo;
	
	public AccountInfo(){
		cpConclu = test.cpdib.ClassFactory.createCpConclusion();//�ֽ��ֹ��� �Ϳ� ���� ü�� ������ ��û�ϰ� �����մϴ�
	}
		
	public ArrayList<Object> sellbuyinfo(){
		sellbuyInfo = new ArrayList<Object>();
		cpConclu.subscribe();//�ֹ� ü�� data ������ ��û�Ѵ�.

		for(int i=1; i<24; i++)//ü�� ���� ������ ���۹޽��ϴ�
		{
			sellbuyInfo.add(cpConclu.getHeaderValue(i));
		}				
		cpConclu.unsubscribe();//�ֹ� ü�� data ������ �����Ѵ�

		return sellbuyInfo;		
	}	
}
