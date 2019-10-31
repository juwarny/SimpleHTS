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

//CYBOS에서 사용되는 주식코드 조회 작업을 함.

public class StockCode {
	private ICpStockCode stcode;
	private ArrayList<Object[]> stlist;
	private ArrayList<Object> fieldvalue;

	public StockCode(){
		stcode = test.cputil.ClassFactory.createCpStockCode();
	}
	public String CodeToName(String code){
		return stcode.codeToName(code);
	}//code에 해당하는 종목명을 반환합니다
	public String NameToCode(String name){
		return stcode.nameToCode(name);
	}//code에 해당하는 종목명을 반환합니다
	public String CodeToFullCode(String code){
		return stcode.codeToFullCode(code);
	}//code에 해당하는 FullCode를 반환한다
	public String FullCodeToName(String fullcode){
		return stcode.fullCodeToName(fullcode);
	}//fullcode에 해당하는 종목명을 반환한다
	public String FullCodeToCode(String fullcode){
		return stcode.fullCodeToCode(fullcode);
	}//fullcode에 해당하는 Code를 반환한다
	public int CodeToIndex(String code){
		return stcode.codeToIndex(code);
	}//code에 해당하는 Index를 반환한다
	public int GetCount(){
		return stcode.getCount();
	}//종목 코드 수를 반환한다
	public Object GetData(short type, short index){
		return stcode.getData(type, index);
	}//해당 인덱스의 종목 데이터를 구한다
	//0 - 종목코드, 1 - 종목명, 2 - FullCode
	public long GetPriceUnit(String code, int basePrice, boolean directionUp){
		return stcode.getPriceUnit(code, basePrice, directionUp);	
	}//주식/ETF/ELW의 호가 단위를 구한다
	public ArrayList<Object[]> getStockList(JFrame f){
		JDialog jd = new JDialog(f);	
		jd.setTitle("종목 리스트 불러오는 중...");
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
	}//종목 전체 리스트를 받아옵니다.	
}
