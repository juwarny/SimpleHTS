package test;

//import com4j.*;
import test.cputil.*;
import test.cpdib.*;
import test.cpdib.events.*;

public class dstest{
	private ICpCybos bos;// = test.cputil.ClassFactory.createCpCybos();

	public void dstest(){
		

	}
	public int connecting(){
		return bos.isConnect();
	}
	
	public static void main(String[] args){
		
		dstest dt = new dstest();
		IDib dis = test.cpdib.ClassFactory.createStockMst();
		IDib dib = test.cpdib.ClassFactory.createStockCur();

		dib.subscribe();
		String b = "A005935";
		dib.setInputValue(0, b);
		String a = (String)dib.getHeaderValue(1);

		
		
		System.out.println(a);
		System.out.println(dt.connecting());
		
	}
}
