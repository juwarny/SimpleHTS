package account;

import java.util.*;
import com4j.*;
import test.cputil.*;
import test.cpdib.*;

public class accountInfo {
	private ICpCybos bos = test.cputil.ClassFactory.createCpCybos();
	private IDib cpConclu = test.cpdib.ClassFactory.createCpConclusion();
	private ArrayList<Object> sellbuyInfo;// = new ArrayList<Object>();
	
	public int isConnect(){
		return bos.isConnect();
	}
	public int serverType(){
		return bos.serverType();
	}
	public ArrayList<Object> sellbutinfo(){
		cpConclu.subscribe();
		for(int i=1; i<10; i++)
		{
			sellbuyInfo.add(cpConclu.getHeaderValue(i));
		}
		sellbuyInfo.add(cpConclu.getHeaderValue(12));
		sellbuyInfo.add(cpConclu.getHeaderValue(14));
		sellbuyInfo.add(cpConclu.getHeaderValue(15));
		sellbuyInfo.add(cpConclu.getHeaderValue(16));
		
		cpConclu.unsubscribe();
		return sellbuyInfo;		
	}
	public static void main(String[]args){
		accountInfo ainfo = new accountInfo();
		ArrayList<Object> sellbuyInfo = ainfo.sellbutinfo();
		for(Object i : sellbuyInfo){
			i.toString();
		}
	}	
}
