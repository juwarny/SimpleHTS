package stock;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.jsoup.Jsoup; //import Jsoup
import org.jsoup.nodes.Document;  //import Jsoup
import org.jsoup.select.Elements;  //import Jsoup
import org.jsoup.nodes.Element; //import Jsoup

public class Finance_info_Scraping {
	private String link;
	private String link_front;
	private String link_back;
	private ArrayList<String> col_name;
	private ArrayList<String> data;
	private ArrayList<Object[]> record;
	private ImageIcon logo_image;
	private ArrayList<String> name_and_class;
	private String itemcode;
	
	public Finance_info_Scraping(String code){//종목코드를 받아와서 초기화 시킵니다.
		itemcode = code;
	}
	public void setItemcode(String code){
		itemcode = code;
	}	
	public ArrayList<String> getCol_name(){
		return col_name;
	}	
	public ArrayList<String> getData() {
		return data;
	}
	public ArrayList<Object[]> getRecord() {
		return record;
	}
	public ArrayList<String> getName_and_class(){
		return name_and_class;
	}	
	public ImageIcon getLogo_image() {
		return logo_image;
	}

	
	public void setSummary(String code){//간단한 시세 현황을 가져오기 위해 URL주소를 생성합니다.
		code = code.substring(1, code.length());
		link_front = "http://media.kisline.com/investinfo/mainInvestinfo.nice?paper_stock=";
		link_back = "&nav=3&header=N";
		link = link_front + code + link_back;
	}
	public void setFinnaceinfo(String code){//재무정보를 가져오기 위해 URL주소를 생성합니다.
		code = code.substring(1, code.length()); 

		link_front = "http://media.kisline.com/fininfo/mainFininfo.nice?paper_stock=";
		link_back = "&nav=4&header=N";
		link = link_front + code + link_back;
	}
	public void setName_and_class(){//종목 이름, 영문이름, 업종 분류 정보를 가져옵니다.
		setFinnaceinfo(itemcode);
		name_and_class = new ArrayList<String>();
		Document doc = null;
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element info = doc.select("div.cot p strong").first();
		name_and_class.add(info.text());
		info = doc.select("div.cot p span.en").first();
		name_and_class.add(info.text());
		info = doc.select("div.cot p.cot_tx").first();
		name_and_class.add(info.text());
	}
	public void setLogo(){//로고가  있는 사이트의 URL 주소를 가져와 이미지 아이콘에 집어넣습니다.
		setFinnaceinfo(itemcode);
		Document doc = null;
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element imageElement = doc.select("table.logo tbody tr td img").first();
		 
		try {
			URL s = new URL(imageElement.absUrl("src"));
			logo_image = new ImageIcon(s);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
	}
	public void setSummaryTableData(){//간단한 시세현황 정보를 스트랩해서 리스트에 저장합니다.
		col_name = new ArrayList<String>();
		data = new ArrayList<String>();
		
		setSummary(itemcode);
		Document doc = null;
		
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements rowElements = doc.select("table.list_a1 tbody tr");		

		for (int i = 0; i < rowElements.size(); i++) {
			Element row = rowElements.get(i);
			Elements name = row.getElementsByTag("th");
		    Elements cols = row.getElementsByTag("td");		    
		    col_name.add(name.get(0).text());
		    data.add(cols.get(0).text());
		}
	}	
	
	public void setFinanceAnalysisTableData(){//재무분석 정보를 스크랩해서 리스트에 저장합니다.
		col_name = new ArrayList<String>();
		
		record = new ArrayList<Object[]>(); 
		String option = null;
		
		setFinnaceinfo(itemcode);
		Document doc = null;
		
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Elements rowElements = doc.select("div#Fin1 div.section1 table.list_b1 tbody tr td");
		if(rowElements.get(0).text().equals("자료가 없습니다.")){
			option = "div#Fin0 ";
		}
		else{
			option = "div#Fin1 ";
		}
		
		rowElements = doc.select(option + "div.section1 table.list_b1 thead tr");		

		for (int i = 0; i < rowElements.size(); i++) {
			Element row = rowElements.get(i);
			Elements name = row.getElementsByTag("th");
			 for(int j=0; j< name.size(); j++){
				 col_name.add(name.get(j).text());
		    }		    
		}
		
		rowElements = doc.select(option + "div.section1 table.list_b1 tbody tr");
		
		for (int i = 0; i < rowElements.size(); i++) {
			data = new ArrayList<String>();
			
			Element row = rowElements.get(i);
			Elements name = row.getElementsByTag("th");
			if(name.get(0).toString().contains("<span")){
				data.add("    "+name.get(0).text());
			}else{
				data.add(name.get(0).text());
			}
		    Elements cols = row.getElementsByTag("td");
		    for(int j=0; j< cols.size(); j++){
		    	 data.add(cols.get(j).text());
		    }
		    record.add(data.toArray());
		}
	}
	public void setFinanceStatementTableData(){//재무상태표를 스크랩해서 리스트에 저장합니다.
		col_name = new ArrayList<String>();
		
		record = new ArrayList<Object[]>(); 
		String option = null;
		
		setFinnaceinfo(itemcode);
		Document doc = null;
		
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Elements rowElements = doc.select("div#Sin1 div.section1 table.list_b1 tbody tr td");
		if(rowElements.get(0).text().equals("자료가 없습니다.")){
			option = "div#Sin0 ";
		}
		else{
			option = "div#Sin1 ";
		}
		
		rowElements = doc.select(option + "div.section1 table.list_b1 thead tr");		

		for (int i = 0; i < rowElements.size()-1; i++) {
			Element row = rowElements.get(i);
			Elements name = row.getElementsByTag("th");
			 for(int j=0; j< name.size(); j++){
				 col_name.add(name.get(j).text());
		    }		    
		}
		
		rowElements = doc.select(option + "div.section1 table.list_b1 tbody tr");
		
		for (int i = 0; i < rowElements.size()/2; i++) {
			data = new ArrayList<String>();
			
			Element row = rowElements.get(i);
			Elements name = row.getElementsByTag("th");
			if(name.get(0).toString().contains("<span")){
				data.add("    "+name.get(0).text());
			}else{
				data.add(name.get(0).text());
			}
		    Elements cols = row.getElementsByTag("td");
		    for(int j=0; j< cols.size(); j++){
		    	 data.add(cols.get(j).text());
		    }
		    record.add(data.toArray());
		}
	}
	
	public void setStatementOfComprehensiveIncomeTableData(){//포괄손익계산서를 리스트에 저장
		col_name = new ArrayList<String>();
		
		record = new ArrayList<Object[]>(); 
		String option = null;
		
		setFinnaceinfo(itemcode);
		Document doc = null;
		
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Elements rowElements = doc.select("div#Pin1 div.section1 table.list_b1 tbody tr td");
		if(rowElements.get(0).text().equals("자료가 없습니다.")){
			option = "div#Pin0 ";
		}
		else{
			option = "div#Pin1 ";
		}
		
		rowElements = doc.select(option + "div.section1 table.list_b1 thead tr");		

		for (int i = 0; i < rowElements.size()-1; i++) {
			Element row = rowElements.get(i);
			Elements name = row.getElementsByTag("th");
			 for(int j=0; j< name.size(); j++){
				 col_name.add(name.get(j).text());
		    }		    
		}
		
		rowElements = doc.select(option + "div.section1 table.list_b1 tbody tr");
		
		for (int i = 0; i < rowElements.size()/2; i++) {
			data = new ArrayList<String>();
			
			Element row = rowElements.get(i);
			Elements name = row.getElementsByTag("th");
			if(name.get(0).toString().contains("<span")){
				data.add("    "+name.get(0).text());
			}else{
				data.add(name.get(0).text());
			}
		    Elements cols = row.getElementsByTag("td");
		    for(int j=0; j< cols.size(); j++){
		    	 data.add(cols.get(j).text());
		    }
		    record.add(data.toArray());
		}
	}
	
	public void setStatementOfCashFlowTableData(){//현금흐름표를 리스트에 저장합니다.
		col_name = new ArrayList<String>();
		
		record = new ArrayList<Object[]>(); 
		String option = null;
		
		setFinnaceinfo(itemcode);
		Document doc = null;
		
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Elements rowElements = doc.select("div#Hin1 div.section1 table.list_b1 tbody tr td");
		if(rowElements.get(0).text().equals("자료가 없습니다.")){
			option = "div#Hin0 ";
		}
		else{
			option = "div#Hin1 ";
		}
		
		rowElements = doc.select(option + "div.section1 table.list_b1 thead tr");		

		for (int i = 0; i < rowElements.size()-1; i++) {
			Element row = rowElements.get(i);
			Elements name = row.getElementsByTag("th");
			 for(int j=0; j< name.size(); j++){
				 col_name.add(name.get(j).text());
		    }		    
		}
		
		rowElements = doc.select(option + "div.section1 table.list_b1 tbody tr");
		
		for (int i = 0; i < rowElements.size()/2; i++) {
			data = new ArrayList<String>();
			
			Element row = rowElements.get(i);
			Elements name = row.getElementsByTag("th");
			if(name.get(0).toString().contains("<span")){
				data.add("    "+name.get(0).text());
			}else{
				data.add(name.get(0).text());
			}
		    Elements cols = row.getElementsByTag("td");
		    for(int j=0; j< cols.size(); j++){
		    	 data.add(cols.get(j).text());
		    }
		    record.add(data.toArray());
		}
	}
}
