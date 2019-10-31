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
	
	public Finance_info_Scraping(String code){//�����ڵ带 �޾ƿͼ� �ʱ�ȭ ��ŵ�ϴ�.
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

	
	public void setSummary(String code){//������ �ü� ��Ȳ�� �������� ���� URL�ּҸ� �����մϴ�.
		code = code.substring(1, code.length());
		link_front = "http://media.kisline.com/investinfo/mainInvestinfo.nice?paper_stock=";
		link_back = "&nav=3&header=N";
		link = link_front + code + link_back;
	}
	public void setFinnaceinfo(String code){//�繫������ �������� ���� URL�ּҸ� �����մϴ�.
		code = code.substring(1, code.length()); 

		link_front = "http://media.kisline.com/fininfo/mainFininfo.nice?paper_stock=";
		link_back = "&nav=4&header=N";
		link = link_front + code + link_back;
	}
	public void setName_and_class(){//���� �̸�, �����̸�, ���� �з� ������ �����ɴϴ�.
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
	public void setLogo(){//�ΰ�  �ִ� ����Ʈ�� URL �ּҸ� ������ �̹��� �����ܿ� ����ֽ��ϴ�.
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
	public void setSummaryTableData(){//������ �ü���Ȳ ������ ��Ʈ���ؼ� ����Ʈ�� �����մϴ�.
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
	
	public void setFinanceAnalysisTableData(){//�繫�м� ������ ��ũ���ؼ� ����Ʈ�� �����մϴ�.
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
		if(rowElements.get(0).text().equals("�ڷᰡ �����ϴ�.")){
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
	public void setFinanceStatementTableData(){//�繫����ǥ�� ��ũ���ؼ� ����Ʈ�� �����մϴ�.
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
		if(rowElements.get(0).text().equals("�ڷᰡ �����ϴ�.")){
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
	
	public void setStatementOfComprehensiveIncomeTableData(){//�������Ͱ�꼭�� ����Ʈ�� ����
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
		if(rowElements.get(0).text().equals("�ڷᰡ �����ϴ�.")){
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
	
	public void setStatementOfCashFlowTableData(){//�����帧ǥ�� ����Ʈ�� �����մϴ�.
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
		if(rowElements.get(0).text().equals("�ڷᰡ �����ϴ�.")){
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
