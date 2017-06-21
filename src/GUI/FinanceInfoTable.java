package GUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import stock.Finance_info_Scraping;
import stock.StockCode;

import javax.swing.JLabel;
import java.awt.BorderLayout;

public class FinanceInfoTable extends JPanel
{
	private JTable jTable;	
	private JScrollPane jScollPane;	
	private Finance_info_Scraping fis;
	private JPanel panel;
	private JLabel logolabel;
	private AutoSuggest itemCode_comboBoxs;
	private StockCode stc;//stock list
	private ArrayList<String> stclist_name;
	private JButton fa;
	private JButton fs;
	private JButton cf;
	private JButton cs;
	private JPanel option;
	private OptionListener optionlistener;

	public FinanceInfoTable(ArrayList<String> list)
	{
		fis = new Finance_info_Scraping("A005930");		
		fis.setFinanceAnalysisTableData();
		fis.setLogo();
		fis.setName_and_class();
		String s = fis.getName_and_class().toString();
		
		stc = new StockCode();
		stclist_name = list;
		
		itemCode_comboBoxs = new AutoSuggest(stclist_name.toArray());
		ItemCodeListener itemcodeListener = new ItemCodeListener();		
		itemCode_comboBoxs.addItemListener(itemcodeListener);

		logolabel = new JLabel(s);
		logolabel.setIcon(fis.getLogo_image());
		
		jTable = new JTable(getTableModel());
		jScollPane = new JScrollPane(jTable);		
		
		setLayout(new BorderLayout());
		setVisible(true);
		
		fa = new JButton("재무분석");
		fs = new JButton("재무상태표");
		cs = new JButton("포괄손익계산서");
		cf = new JButton("현금흐름표");
		
		optionlistener = new OptionListener();
		
		fa.addActionListener(optionlistener);
		fs.addActionListener(optionlistener);
		cs.addActionListener(optionlistener);
		cf.addActionListener(optionlistener);
		
		option = new JPanel();
		option.setLayout(new GridLayout(0, 1));
		option.add(itemCode_comboBoxs);
		option.add(fa);
		option.add(fs);
		option.add(cs);
		option.add(cf);
		
		add(option, BorderLayout.EAST);
		add(logolabel, BorderLayout.NORTH);
		add(jScollPane, BorderLayout.CENTER);
	}
	
	public void insert_ItemCode_Combobox(ArrayList<Object[]> stclist){
		stclist_name = new ArrayList<String>();		
		for(int i=0; i<stclist.size(); i++){
			stclist_name.add(stclist.get(i)[1].toString());
		}	
	}
	
	public DefaultTableModel getTableModel(){		
		Object[] columnNames = fis.getCol_name().toArray();		
		DefaultTableModel defaultTableModel = new DefaultTableModel(); //DefaultTableModel을 선언하고 데이터 담기		
		defaultTableModel.setColumnIdentifiers(columnNames);
		for(int i=0; i<fis.getRecord().size(); i++){
			defaultTableModel.insertRow(i, fis.getRecord().get(i));
		}		
		return defaultTableModel;
	}
	
	public class ItemCodeListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			 if (e.getStateChange() == ItemEvent.SELECTED) {
				 try{					 	
					 	String s = stc.NameToCode(e.getItem().toString());
					 	fis.setItemcode(s);
					 	fis.setLogo();
					 	fis.setName_and_class();
						String a = fis.getName_and_class().get(0)+" | "+fis.getName_and_class().get(1)+" | "+fis.getName_and_class().get(2);
						logolabel.setIcon(fis.getLogo_image());
						logolabel.setText(a);
						fis.setFinanceAnalysisTableData();
						jTable.setModel(getTableModel());					 						
					 
					}catch(Exception ex){
						ex.printStackTrace();
					}			        
			    }			
		}
		
	}
	
	public class OptionListener implements  ActionListener{
		public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("재무분석")){
					fis.setFinanceAnalysisTableData();
					jTable.setModel(getTableModel());
				}
				else if(e.getActionCommand().equals("재무상태표")){
					fis.setFinanceStatementTableData();
					jTable.setModel(getTableModel());
				}
				else if(e.getActionCommand().equals("포괄손익계산서")){
					fis.setStatementOfComprehensiveIncomeTableData();
					jTable.setModel(getTableModel());
				}
				else if(e.getActionCommand().equals("현금흐름표")){
					fis.setStatementOfCashFlowTableData();
					jTable.setModel(getTableModel());
				}
				
			}
	}
	
}