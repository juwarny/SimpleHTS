package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

import stock.StockCode;
import stock.StockMst;
import GUI.AutoSuggest;
import trade.Inorder;
import trade.Inquiry;
import trade.OdBeforeinit;

public class sellbuyOrder extends JPanel {
	private StockCode stc;//stock list
	private StockMst stm;	
	private ArrayList<String> stclist_name;
	private ArrayList<String> stclist_name_possible;
	private Long price_before;
	private JLabel lblNewLabel;
	private JComboBox sellbuy_comboBox;
	private JLabel label;
	private JComboBox accountNum_comboBox;
	private JLabel label_1;
	private JLabel label_2;
	private JSpinner orderUnitPrice_spinner;
	private JSpinner orderQuan_spinner;
	private JLabel label_3;
	private JLabel label_4;
	private AutoSuggest itemCode_comboBoxs;
	private Inquiry possible;
	private JPanel panel;
	private Jpbid1sUpdate jpu;
	private Object[] accountlist;
	
	public sellbuyOrder(ArrayList<String> list, Object[] accountNum){
		stc = new StockCode();
		stm = new StockMst();
		possible = new Inquiry();
		price_before = Long.parseUnsignedLong("0");
		stclist_name = list;
		stclist_name_possible = new ArrayList<String>();
		accountlist = accountNum;
		
		setLayout(new GridLayout(0, 2, 0, 0));
		SellBuyChangeListener sbListener = new SellBuyChangeListener();;
		
			
		
		ItemCodeListener itemcodeListener = new ItemCodeListener();		
		QuanChangeListener quanListener = new QuanChangeListener();
		PriceChangeListener priceListener = new PriceChangeListener();
		OrderActionListener orderListenr = new OrderActionListener();
		
		panel = new JPanel();
		
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNewLabel = new JLabel("매수/매도");
		panel.add(lblNewLabel);
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		sellbuy_comboBox = new JComboBox();
		panel.add(sellbuy_comboBox);
		sellbuy_comboBox.addItem("매수");
		sellbuy_comboBox.addItem("매도");
		
		label = new JLabel("계좌번호");
		panel.add(label);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		accountNum_comboBox = new JComboBox();
		panel.add(accountNum_comboBox);
		insert_Accountnum_Combobox(accountNum_comboBox);
		
		label_1 = new JLabel("종목명");
		panel.add(label_1);
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		itemCode_comboBoxs = new AutoSuggest(stclist_name.toArray());
		panel.add(itemCode_comboBoxs);
		itemCode_comboBoxs.setEditable(true);
		itemCode_comboBoxs.addItemListener(itemcodeListener);
		
		
		
		label_2 = new JLabel("주문 수량");
		panel.add(label_2);
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		orderQuan_spinner = new JSpinner();
		panel.add(orderQuan_spinner);
		orderQuan_spinner.setEnabled(false);
		orderQuan_spinner.setEditor(new JSpinner.DefaultEditor(orderQuan_spinner));
		orderQuan_spinner.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		
		label_3 = new JLabel("주문 단가");
		panel.add(label_3);
		label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		orderUnitPrice_spinner = new JSpinner();
		panel.add(orderUnitPrice_spinner);
		orderUnitPrice_spinner.setEnabled(false);
		orderUnitPrice_spinner.setEditor(new JSpinner.DefaultEditor(orderUnitPrice_spinner));
		orderUnitPrice_spinner.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		
		label_4 = new JLabel("총 합계 :");
		panel.add(label_4);
		label_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton orderButton = new JButton("주문");
		panel.add(orderButton);
		orderButton.addActionListener(orderListenr);
		orderUnitPrice_spinner.addChangeListener(priceListener);
		orderQuan_spinner.addChangeListener(quanListener);
		sellbuy_comboBox.addItemListener(sbListener);
		
		jpu = new Jpbid1sUpdate("삼성전자");
		itemCode_comboBoxs.setSelectedIndex(100);
		
		add(panel);
	}
	public void insert_Accountnum_Combobox(JComboBox accountNum_comboBox){
		
		for(int i=0; i<accountlist.length; i++){
			accountNum_comboBox.addItem(accountlist[i]);
		}	
	}
	public void insert_ItemCode_Combobox(ArrayList<Object[]> stclist){
		stclist_name = new ArrayList<String>();		
		for(int i=0; i<stclist.size(); i++){
			stclist_name.add(stclist.get(i)[1].toString());
		}	
	}
	public void insert_SellItem_Combobox(){
		stclist_name_possible = new ArrayList<String>();
		possible.setvalSella((String)accountNum_comboBox.getSelectedItem(), "10", "", '1', '1', "", "00", "", '0', "00", 20);
		int count = possible.getHvalSella().intValue();
		for(int i=0; i<count; i++){
			stclist_name_possible.add(possible.getDvalSella(i).get(1).toString());
		}				
	}
	public String itemName(){
		return itemCode_comboBoxs.getSelectedItem().toString();
	}	
	/*리스너*/
	
	
	
	public class SellBuyChangeListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getItem()=="매수"){
				itemCode_comboBoxs.removeAllItems();
				itemCode_comboBoxs.settags(stclist_name.toArray());
				for( String i : stclist_name){
					itemCode_comboBoxs.addItem(i);
				}
			}				
			else{
				insert_SellItem_Combobox();
				itemCode_comboBoxs.removeAllItems();
				itemCode_comboBoxs.settags(stclist_name_possible.toArray());
				for( String i : stclist_name_possible){
					itemCode_comboBoxs.addItem(i);
				}
			}
		}
	}	
	public class OrderActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Inorder oder = new Inorder();
			oder.setvalInod((String)sellbuy_comboBox.getSelectedItem(), (String)accountNum_comboBox.getSelectedItem(), "10", 
							(String)stc.NameToCode((String) itemCode_comboBoxs.getSelectedItem()), 
							Long.parseUnsignedLong(orderQuan_spinner.getValue().toString()), Long.parseUnsignedLong(orderUnitPrice_spinner.getValue().toString()),  "01");			
		}
	}
	public class PriceChangeListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {				
			Long a = Long.parseUnsignedLong(orderUnitPrice_spinner.getValue().toString());
			String s = stc.NameToCode((String)itemCode_comboBoxs.getSelectedItem());
			Long unit_up = stc.GetPriceUnit(s, price_before.intValue(), true);
			Long unit_down = stc.GetPriceUnit(s, price_before.intValue(), false);
			if(price_before!=Integer.toUnsignedLong(0)){
				if(a>price_before){
					orderUnitPrice_spinner.setModel(new SpinnerNumberModel(new Long(price_before+unit_up), null, null, new Long(unit_up)));
					price_before+=unit_up;
				}					
				else if(a<price_before){
					orderUnitPrice_spinner.setModel(new SpinnerNumberModel(new Long(price_before-unit_down), null, null, new Long(unit_down)));
					price_before-=unit_down;
				}		
			}
			Long total = Long.parseUnsignedLong(orderUnitPrice_spinner.getValue().toString())*Long.parseUnsignedLong(orderQuan_spinner.getValue().toString());
			String str = String.format("%,d 원", total);
			label_4.setText("총 합계 : "+str);
		}
	}
	public class QuanChangeListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {				
			 Long total = Long.parseUnsignedLong(orderUnitPrice_spinner.getValue().toString())*Long.parseUnsignedLong(orderQuan_spinner.getValue().toString());
			 String str = String.format("%,d 원", total);
			 label_4.setText("총 합계 : "+str);
		}
	}
	public class ItemCodeListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			 if (e.getStateChange() == ItemEvent.SELECTED) {
				 try{					 	
					 	if(stc.NameToCode(e.getItem().toString()) != "" && sellbuy_comboBox.getSelectedItem()=="매수"){
					 		String s = stc.NameToCode(e.getItem().toString());
							stm.setvalStockmst2(s);
							price_before = Long.parseUnsignedLong(stm.getDvalStockmst2(0).get(3).toString());// 현재가 입력
							orderUnitPrice_spinner.setValue(price_before);
							possible.setvalPurchase((String)accountNum_comboBox.getSelectedItem(), "10", s, "01", price_before, 'N', '2');
							orderQuan_spinner.setValue(possible.getvalPurchase().get(18));
							orderUnitPrice_spinner.setEnabled(true);
							orderQuan_spinner.setEnabled(true);
					 	}
					 	else if(stc.NameToCode(e.getItem().toString()) != "" && sellbuy_comboBox.getSelectedItem()=="매도"){
					 		String s = stc.NameToCode(e.getItem().toString());
							stm.setvalStockmst2(s);
							price_before = Long.parseUnsignedLong(stm.getDvalStockmst2(0).get(3).toString());// 현재가 입력
							orderUnitPrice_spinner.setValue(price_before);
							possible.setvalSella((String)accountNum_comboBox.getSelectedItem(), "10", s, '1', '1', "", "00", "", '0', "00", 1);
							if(possible.getHvalSella()!= Long.parseLong("0")){
								orderQuan_spinner.setValue(possible.getDvalSella(0).get(12));
								orderUnitPrice_spinner.setEnabled(true);
								orderQuan_spinner.setEnabled(true);
							}							
					 	}
					 	remove(jpu);
					 	jpu = new Jpbid1sUpdate(e.getItem().toString());
						add(jpu, 0);					 
					}catch(Exception ex){
						ex.printStackTrace();
					}			        
			    }			
		}
		
	}
}
