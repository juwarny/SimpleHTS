package test;

import com4j.*;
import test.cputil.*;
import test.cpdib.*;

public class dstest{
	public static void main(String[] args){
		ICpCybos bos = test.cputil.ClassFactory.createCpCybos();
		IDib dib = test.cpdib.ClassFactory.createStockCur();
		
		dib.setInputValue(0, "A005935");
		dib.subscribe();
		System.out.println(dib.getHeaderValue(1));
		System.out.println(bos.isConnect());
		
	}
}
