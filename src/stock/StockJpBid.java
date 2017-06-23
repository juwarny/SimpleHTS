package stock;

import com4j.*;
import test.cpdib.*;
import test.cpdib.events._IDibEvents;

import java.awt.Event;
import java.util.*;

public class StockJpBid {
	private IDib stockjpbid;//�ֽ�/ETF/ELW ���� �ŵ�,�ż��� ���� 1�� ~10�� ȣ��/LPȣ�� �� ȣ���ܷ� ����
	private ArrayList<Object> stkinfo;
	private ArrayList<Object[]> stkdata;
	private ArrayList<Object> stkdata_row;

	public StockJpBid(){
		stockjpbid = test.cpdib.ClassFactory.createStockJpbid2();		
	}
	public void setvalStockJpBid(String code){//0 - (string) ���� �ڵ�
		stockjpbid.setInputValue(0, (Object)code);
	}	
	public ArrayList<Object> getHvalStockmst(){//��� �����͸� ��ȯ�մϴ�
		stkinfo = new ArrayList<Object>();
		stockjpbid.blockRequest();
		for(int i = 0; i<12; i++) {
			while(stockjpbid.getHeaderValue(i)==null) i++;
			stkinfo.add(stockjpbid.getHeaderValue(i));			
		}	
		return stkinfo;	
	}

	/*
	 * 0 - (string) ���� �ڵ� 
	 * 1 - (long) COUNT [10�� ȣ�� �������� 10�� ��������] 
	 * 3 - (short) �ð�
	 * 4 - (long) �Ѹŵ��ܷ� 
	 * 5 - (long) �Ѹŵ��ܷ���� 
	 * 6 - (long) �Ѹż��ܷ�
	 * 7 - (long) �Ѹż��ܷ���� 
	 * 8 - (long) �ð����Ѹŵ��ܷ� 
	 * 9 - (long) �ð����Ѹŵ��ܷ���� 
	 * 10 - (long) �ð����Ѹż��ܷ� 
	 * 11 - (long) �ð����Ѹż��ܷ����
	 */
	public ArrayList<Object[]> getDvalStockmst(){//�����͸� ����Ʈ�� �����ɴϴ�.
		stkdata = new ArrayList<Object[]>();		
		stockjpbid.blockRequest();
		for(int i = 0; i<10; i++) {
			stkdata_row = new ArrayList<Object>();
			for(int j=0; j<6; j++){
				stkdata_row.add(stockjpbid.getDataValue(j, i));
			}			
			stkdata.add(stkdata_row.toArray());			
		}	
		return stkdata;	
	}
	/*
	 * 0 - (long) �ŵ�ȣ�� 
	 * 1 - (long) �ż�ȣ�� 
	 * 2 - (long) �ŵ��ܷ� 
	 * 3 - (long) �ż��ܷ� 
	 * 4 - (long) �ŵ��ܷ���� 
	 * 5 - (long) �ż��ܷ����
	 */
	
}
