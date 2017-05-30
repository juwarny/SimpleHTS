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
		stglist = test.CpSysDib.ClassFactory.createCssStgList();//서버에 저장된 종목검색 전략 (내 전략) 의 전략명과 전략ID 리스트를 가져온다.
		stgfind = test.CpSysDib.ClassFactory.createCssStgFind();//각 종목검색 전략에 맞는 종목들을 리스트 형태로 가져온다.
		wstgcon = test.CpSysDib.ClassFactory.createCssWatchStgControl();//전략 감시 시작/중지 하기 위한 오브젝트입니다.		
		wstgsub = test.CpSysDib.ClassFactory.createCssWatchStgSubscribe();
		/*전략 감시를 위한 일련번호를 구해온다
		현재 감시되는 전략이 없다면 감시일련번호로 1을 리턴하고
		현재 감시되는 전략이 있다면 각 통신ID에 대응되는 새로운 일련번호를 리턴한다.*/		 
		stgalert = test.CpSysDib.ClassFactory.createCssAlert();//현재 종목검색 전략에 대한 종목들의 진입/퇴출 등 발생 신호에 대한 정보를 가져온다.
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
	
	public void setvalStgfind(){//수정이 필요함
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
