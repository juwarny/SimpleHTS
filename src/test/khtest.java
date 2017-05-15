package test;

import com4j.*;
import test.khopenapi.events.*;
import test.khopenapi.*;



public class khtest {
	public static void main(String[] args){
		_DKHOpenAPI kh = ClassFactory.createKHOpenAPI();
		kh.commConnect();
	}	
}
