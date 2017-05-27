import java.util.*;
import com4j.*;
import test.cputil.*;

public class StockCode {
	private ICpStockCode stcode = test.cputil.ClassFactory.createCpStockCode();
	private ArrayList<Object[]> stlist;
	private Object[] fieldvalue;
	
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
	public ArrayList<Object[]> getStockList(){//수정좀 해야됨
		stlist = new ArrayList<Object[]>();
		fieldvalue = new Object[3];
		for(short j = 0; j<3; j++)
		{
			fieldvalue[j] = new Object();
		}		
		for(int i=0; i<stcode.getCount(); i++)
		{
			for(int j = 0; j<3; j++)
			{
				fieldvalue[j] = stcode.getData((short)j, (short)i);
			}
			stlist.add(fieldvalue);
		}
		return stlist;
	}
	public static void main(String[]args){
		StockCode st = new StockCode();
		ArrayList<Object[]> stli = new ArrayList<Object[]>();
		
		stli = st.getStockList();
		for(int i=0; i<stli.size(); i++)
		{
			for(int j = 0; j<3; j++)
			{
				System.out.println(stli.get(i)[j]);
			}
		}
		
		System.out.println(st.GetData((short)1, (short)0));
	}
}
