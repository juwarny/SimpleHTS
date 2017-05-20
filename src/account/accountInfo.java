package account;

import java.util.*;
import com4j.*;
import test.cpdib.*;

public class accountInfo {
	private IDib cpConclu = test.cpdib.ClassFactory.createCpConclusion();
	private ArrayList<Object> sellbuyInfo;// = new ArrayList<Object>();
	
	
	public ArrayList<Object> sellbutinfo(){
		sellbuyInfo = new ArrayList<Object>();
		cpConclu.subscribe();
		for(int i=1; i<24; i++)
		{
			sellbuyInfo.add(cpConclu.getHeaderValue(i));
		}				
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
