package test;

import com4j.*;
import test.cputil.*;

public class dstest {
	public static void main(String[] args){
		ICpCybos bos = ClassFactory.createCpCybos();
		System.out.println(bos.isConnect());
	}
}
