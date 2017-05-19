package test;

//import com4j.*;
import test.cputil.*;
import com4j.*;
import test.cpdib.*;
import test.cpdib.events.*;

public class dstest extends _IDibEvents{
	private ICpCybos bos = test.cputil.ClassFactory.createCpCybos();
	
	
	
	public int connecting(){
		return bos.isConnect();
	}
	
	public static void main(String[] args){
		
		dstest dt = new dstest();
		IDib dis = test.cpdib.ClassFactory.createStockMst();
		IDib dib = test.cpdib.ClassFactory.createStockCur();
		ICpCybos bs = test.cputil.ClassFactory.createCpCybos();
		ICpStockCode bb = test.cputil.ClassFactory.createCpStockCode();
		
		Object b = "A005935";//bb.codeToFullCode("A005935");
		//System.out.println(b);
		//dt.received();
		
		
		dib.setInputValue(0, (Object) b);
		dib.subscribe();
		//Object a = (String)dib.getHeaderValue(1);
		
		dis.setInputValue(0, (Object) b);
		dis.blockRequest();
		Object c;
		
		for(int i = 0; i<71; i++) {
			while(dis.getHeaderValue(i)==null) i++;
			c = dis.getHeaderValue(i);
			System.out.println(i+": "+c.toString());
			
		}
		
		//c = dis.getHeaderValue(45);
		//System.out.println(c.toString());
		
		
		//Object c = dis.getHeaderValue(1);
		
		
		//System.out.println(c.toString());
		
		
		System.out.println(bs.serverType());
		System.out.println(dt.connecting());
		
	}
}
