package stock;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.*;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com4j.*;
import test.cputil.*;

//CYBOS���� ���Ǵ� �ֽ��ڵ� ��ȸ �۾��� ��.

public class StockCode {
	private ICpStockCode stcode;
	private ArrayList<Object[]> stlist;
	private ArrayList<Object> fieldvalue;

	public StockCode(){
		stcode = test.cputil.ClassFactory.createCpStockCode();
	}
	public String CodeToName(String code){
		return stcode.codeToName(code);
	}//code�� �ش��ϴ� ������� ��ȯ�մϴ�
	public String NameToCode(String name){
		return stcode.nameToCode(name);
	}//code�� �ش��ϴ� ������� ��ȯ�մϴ�
	public String CodeToFullCode(String code){
		return stcode.codeToFullCode(code);
	}//code�� �ش��ϴ� FullCode�� ��ȯ�Ѵ�
	public String FullCodeToName(String fullcode){
		return stcode.fullCodeToName(fullcode);
	}//fullcode�� �ش��ϴ� ������� ��ȯ�Ѵ�
	public String FullCodeToCode(String fullcode){
		return stcode.fullCodeToCode(fullcode);
	}//fullcode�� �ش��ϴ� Code�� ��ȯ�Ѵ�
	public int CodeToIndex(String code){
		return stcode.codeToIndex(code);
	}//code�� �ش��ϴ� Index�� ��ȯ�Ѵ�
	public int GetCount(){
		return stcode.getCount();
	}//���� �ڵ� ���� ��ȯ�Ѵ�
	public Object GetData(short type, short index){
		return stcode.getData(type, index);
	}//�ش� �ε����� ���� �����͸� ���Ѵ�
	//0 - �����ڵ�, 1 - �����, 2 - FullCode
	public long GetPriceUnit(String code, int basePrice, boolean directionUp){
		return stcode.getPriceUnit(code, basePrice, directionUp);	
	}//�ֽ�/ETF/ELW�� ȣ�� ������ ���Ѵ�
	public ArrayList<Object[]> getStockList(JFrame f){
		JDialog jd = new JDialog(f);	
		jd.setTitle("���� ����Ʈ �ҷ����� ��...");
		jd.setVisible(true);
		jd.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		jd.setModal(true);
		jd.setLocationRelativeTo(f);
		jd.setSize(300, 0);
		
		stlist = new ArrayList<Object[]>();		
		for(int i=0; i<stcode.getCount(); i++){
			fieldvalue = new ArrayList<Object>();			
  			for(int j= 0; j<3; j++)
  			{
  				fieldvalue.add(GetData((short)j, (short)i));
  			}
  			stlist.add(fieldvalue.toArray());
  			fieldvalue.removeAll(fieldvalue);
		}
			
        jd.dispose();
		return stlist;
	}//���� ��ü ����Ʈ�� �޾ƿɴϴ�.	
}
