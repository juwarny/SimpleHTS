package strategy;

import test.CpSysDib.*;
import java.util.ArrayList;
import com4j.*;

public class Search {
	private ISysDib stglist;
	private ISysDib stgfind;
	private ISysDib wstgcon;
	private ISysDib wstgsub;
	private ISysDib stgalert;

	private ArrayList<Object> stginfo;
	private ArrayList<Object> stgcount;
	private ArrayList<Object> searchcount;
	private ArrayList<Object> searchinfo;


	private Object[] itemcode;
	private	Object stgWcode;
	
	public Search(){
		stglist = test.CpSysDib.ClassFactory.createCssStgList();//������ ����� ����˻� ���� (�� ����) �� ������� ����ID ����Ʈ�� �����´�.
		stgfind = test.CpSysDib.ClassFactory.createCssStgFind();//�� ����˻� ������ �´� ������� ����Ʈ ���·� �����´�.
		wstgcon = test.CpSysDib.ClassFactory.createCssWatchStgControl();//���� ���� ����/���� �ϱ� ���� ������Ʈ�Դϴ�.		
		wstgsub = test.CpSysDib.ClassFactory.createCssWatchStgSubscribe();
		/*���� ���ø� ���� �Ϸù�ȣ�� ���ؿ´�
		���� ���õǴ� ������ ���ٸ� �����Ϸù�ȣ�� 1�� �����ϰ�
		���� ���õǴ� ������ �ִٸ� �� ���ID�� �����Ǵ� ���ο� �Ϸù�ȣ�� �����Ѵ�.*/		 
		stgalert = test.CpSysDib.ClassFactory.createCssAlert();//���� ����˻� ������ ���� ������� ����/���� �� �߻� ��ȣ�� ���� ������ �����´�.
	}	
	
	public ArrayList<Object> getHvalStglist(){
		stgcount = new ArrayList<Object>();
		stglist.blockRequest();
		for(int i=0; i<2; i++){
			while(stglist.getHeaderValue(i)==null) i++;
			stgcount.add(stglist.getHeaderValue(i));			
		}		
		return stgcount;
	}
	public ArrayList<Object> getDvalStglist(int index){
		stginfo = new ArrayList<Object>();
		stglist.blockRequest();
		for(int i=0; i<8; i++){
			while(stglist.getDataValue(i, index)==null) i++;
			stginfo.add(stglist.getDataValue(i, index));			
		}		
		return stginfo;
	}
	
	public void setvalStgfind(){//������ �ʿ���
		stgfind.setInputValue(0, (Object)stginfo.get(1));		
	}
	public ArrayList<Object> getHvalStgfind(){
		searchcount = new ArrayList<Object>();
		stgfind.blockRequest();
		for(int i=0; i<3; i++){
			while(stgfind.getHeaderValue(i)==null) i++;
			searchcount.add(stgfind.getHeaderValue(i));			
		}		
		return searchcount;
	}
	public Object[] getDvalStgfind(int index){
		itemcode = (Object[])stgfind.getDataValue(0, index);
		return itemcode;
	}
	
	public void setvalWstgsub(int index){
		wstgsub.setInputValue(0, stginfo.get(1));
	}
	public Object getHvalWstgsub(){
		stgWcode = wstgsub.getHeaderValue(0);
		return stgWcode;
	}
	
	public void setvalWstgcon(int option){
		wstgcon.setInputValue(0, stginfo.get(1));
		wstgcon.setInputValue(1, stgWcode);
		wstgcon.setInputValue(3, (Object)option);
	}
	public Object getHvalWstgcon(){
		return wstgcon.getHeaderValue(0);
	}
	
	public ArrayList<Object> getHvalStgalert(){
		searchinfo = new ArrayList<Object>();
		stgalert.blockRequest();
		for(int i=0; i<6; i++){
			while(stgfind.getHeaderValue(i)==null) i++;
			searchinfo.add(stgalert.getHeaderValue(i));			
		}		
		return searchinfo;
	}
	public static void main(String[]args){
		Search s = new Search();
		ArrayList<Object> a;
		a = s.getHvalStglist();
		System.out.println(a.get(0));		
	}
}
