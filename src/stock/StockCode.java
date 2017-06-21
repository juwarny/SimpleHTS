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

public class StockCode {
	private ICpStockCode stcode;
	private ArrayList<Object[]> stlist;
	private ArrayList<Object> fieldvalue;
	private JProgressBar progressBar;
	private JFrame start;

	public StockCode(){
		stcode = test.cputil.ClassFactory.createCpStockCode();
	}
	public String CodeToName(String code){
		return stcode.codeToName(code);
	}
	public String NameToCode(String name){
		return stcode.nameToCode(name);
	}
	public String CodeToFullCode(String code){
		return stcode.codeToFullCode(code);
	}
	public String FullCodeToName(String fullcode){
		return stcode.fullCodeToName(fullcode);
	}
	public String FullCodeToCode(String fullcode){
		return stcode.fullCodeToCode(fullcode);
	}
	public int CodeToIndex(String code){
		return stcode.codeToIndex(code);
	}
	public int GetCount(){
		return stcode.getCount();
	}
	public Object GetData(short type, short index){
		return stcode.getData(type, index);
	}
	public long GetPriceUnit(String code, int basePrice, boolean directionUp){
		return stcode.getPriceUnit(code, basePrice, directionUp);	
	}
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
	}	
}
