package GUI;

import java.awt.Color;
import java.awt.GridLayout;
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

public class cancelalterOrder extends JPanel{
	private StockCode stc;//stock list
	private StockMst stm;	
	private ArrayList<Object[]> stclist;
	private ArrayList<String> stclist_name;
	private ArrayList<String> stclist_name_possible;
	private Long price_before;
	private JLabel lblNewLabel;
	private JComboBox cancelalter_comboBox;
	private JLabel label;
	private JComboBox accountNum_comboBox;
	private JLabel label_1;
	private JLabel label_2;
	private JSpinner orderUnitPrice_spinner;
	private JSpinner orderQuan_spinner;
	private JLabel label_3;
	private JLabel label_4;
	private JComboBox itemCode_comboBoxs;
	private Inquiry possible;
	private JLabel lblNewLabel_1;
	private JComboBox odCode_comboBox;
	
	public cancelalterOrder(){
		stc = new StockCode();
		stm = new StockMst();
		possible = new Inquiry();
		price_before = Long.parseUnsignedLong("0");
		
		
		setLayout(new GridLayout(0, 2, 0, 0));
		
		
		
		cancelalter_comboBox = new JComboBox();
		accountNum_comboBox = new JComboBox();
		odCode_comboBox = new JComboBox();
		itemCode_comboBoxs = new JComboBox();
		orderQuan_spinner = new JSpinner();
		orderUnitPrice_spinner = new JSpinner();
		
		insert_Accountnum_Combobox(accountNum_comboBox);
		insert_Combobox_Info();	

		
		lblNewLabel = new JLabel("취소/정정");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		cancelalter_comboBox.addItem("취소");
		cancelalter_comboBox.addItem("정정");
		CancelAlterChangeListener caListener = new CancelAlterChangeListener();
		cancelalter_comboBox.addItemListener(caListener);;
		add(cancelalter_comboBox);
		
		label = new JLabel("계좌번호");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
		
		add(accountNum_comboBox);
		
		lblNewLabel_1 = new JLabel("원주문번호");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_1);
		
		odCode_comboBox.setEditable(false);
		OrderCodeChangeListener odcodeListener = new OrderCodeChangeListener();
		odCode_comboBox.addItemListener(odcodeListener);
		add(odCode_comboBox);
		
		label_1 = new JLabel("종목명");
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1);	 			
		
		
		itemCode_comboBoxs.setEditable(false);		
		ItemCodeListener itemcodeListener = new ItemCodeListener();		
		itemCode_comboBoxs.addItemListener(itemcodeListener);
		add(itemCode_comboBoxs);
			
		
		label_2 = new JLabel("주문 수량");
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_2);
		
		orderQuan_spinner.setEnabled(true);
		orderQuan_spinner.setEditor(new JSpinner.DefaultEditor(orderQuan_spinner));
		orderQuan_spinner.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		QuanChangeListener quanListener = new QuanChangeListener();
		orderQuan_spinner.addChangeListener(quanListener);
		add(orderQuan_spinner);
		
		label_3 = new JLabel("주문 단가");
		label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_3);
		
		orderUnitPrice_spinner.setEnabled(true);
		orderUnitPrice_spinner.setEditor(new JSpinner.DefaultEditor(orderUnitPrice_spinner));
		orderUnitPrice_spinner.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		PriceChangeListener priceListener = new PriceChangeListener();
		orderUnitPrice_spinner.addChangeListener(priceListener);
		add(orderUnitPrice_spinner);
		
		label_4 = new JLabel("총 합계 :");
		label_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_4);
		
		JButton orderButton = new JButton("주문");
		OrderActionListener orderListenr = new OrderActionListener();
		orderButton.addActionListener(orderListenr);
		add(orderButton);
		
	}
	public void insert_Accountnum_Combobox(JComboBox accountNum_comboBox){
		OdBeforeinit od = new OdBeforeinit();
		od.tradeInit();
		Object[] a = od.getAccountNum();
		for(int i=0; i<a.length; i++){
			accountNum_comboBox.addItem(a[i]);
		}	
	}
	public void insert_Combobox_Info(){
		stclist = new ArrayList<Object[]>();
		possible.setvalDayNconclud(accountNum_comboBox.getSelectedItem().toString(), "10", "", "0", "0", "0", Long.parseLong("1"));
		int count = possible.getHvalDayNconclud().intValue();
		
		if(count!=0){
			for(int i = 0; i<count; i++){
				stclist.add(possible.getDvalDayNconclud(i).toArray());
				odCode_comboBox.addItem(possible.getDvalDayNconclud(i).get(2));
				itemCode_comboBoxs.addItem(possible.getDvalDayNconclud(i).get(4));
			}
		}
		
		
	}

	
	/*리스너*/
	public class OrderCodeChangeListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			itemCode_comboBoxs.setSelectedItem(odCode_comboBox.getSelectedItem());			
		}
	}	
	
	
	public class CancelAlterChangeListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getItem()=="취소"){
				orderUnitPrice_spinner.setEnabled(false);
			}				
			else{
				orderUnitPrice_spinner.setEnabled(true);
			}
		}
	}	
	public class OrderActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Inorder oder = new Inorder();
			if(cancelalter_comboBox.getSelectedItem()=="취소"){
				oder.setvalInodCancle(Long.parseLong(odCode_comboBox.getSelectedItem().toString()), (String)accountNum_comboBox.getSelectedItem(), "10", 
						(String)stc.NameToCode((String) itemCode_comboBoxs.getSelectedItem()), Long.parseUnsignedLong(orderQuan_spinner.getValue().toString())
						);
			}
			else{
				oder.setvalInodAlter(Long.parseLong(odCode_comboBox.getSelectedItem().toString()), (String)accountNum_comboBox.getSelectedItem(), "10", 
						(String)stc.NameToCode((String) itemCode_comboBoxs.getSelectedItem()), Long.parseUnsignedLong(orderQuan_spinner.getValue().toString()),
						Long.parseUnsignedLong(orderUnitPrice_spinner.getValue().toString())
						);
			}					
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
					 	if(stc.NameToCode(e.getItem().toString()) != "" && cancelalter_comboBox.getSelectedItem()=="정정"){
					 		String s = stc.NameToCode(e.getItem().toString());
							stm.setvalStockmst2(s);
							price_before = Long.parseUnsignedLong(stclist.get(itemCode_comboBoxs.getSelectedIndex())[7].toString());// 현재가 입력
							orderUnitPrice_spinner.setValue(price_before);
							orderQuan_spinner.setValue(Long.parseUnsignedLong(stclist.get(itemCode_comboBoxs.getSelectedIndex())[11].toString()));
							orderUnitPrice_spinner.setEnabled(true);
							orderQuan_spinner.setEnabled(true);
					 	}
					 	else if(stc.NameToCode(e.getItem().toString()) != "" && cancelalter_comboBox.getSelectedItem()=="취소"){
					 		String s = stc.NameToCode(e.getItem().toString());
							stm.setvalStockmst2(s);
							price_before = Long.parseUnsignedLong(stclist.get(itemCode_comboBoxs.getSelectedIndex())[7].toString());// 현재가 입력
							orderUnitPrice_spinner.setValue(price_before);
							orderQuan_spinner.setValue(Long.parseUnsignedLong(stclist.get(itemCode_comboBoxs.getSelectedIndex())[11].toString()));
							orderUnitPrice_spinner.setEnabled(true);
							orderQuan_spinner.setEnabled(true);				
					 	}
					}catch(Exception ex){
						ex.printStackTrace();
					}			        
			    }			
		}
		
	}
}
