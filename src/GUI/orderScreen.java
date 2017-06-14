package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import java.awt.Desktop.Action;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTabbedPane;
import java.awt.Scrollbar;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.ComponentOrientation;
import javax.swing.JLabel;
import stock.StockCode;
import stock.StockMst;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import test.AutoSuggest;
import AutoSuggestCore.*;
import trade.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;




public class orderScreen extends JFrame {
	private StockCode stc;//stock list
	private StockMst stm;	
	private ArrayList<Object[]> stclist;
	private ArrayList<String> stclist_name;
	private ArrayList<String> stclist_name_possible;
	private Long price_before;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
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
	//private
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderScreen frame = new orderScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public orderScreen() {
		stc = new StockCode();
		stm = new StockMst();
		stclist = stc.getStockList();
		possible = new Inquiry();
		price_before = Integer.toUnsignedLong(0);
		stclist_name = new ArrayList<String>();
		stclist_name_possible = new ArrayList<String>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 518);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("주문 신청(현금)", null, panel, null);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNewLabel = new JLabel("매수/매도");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		sellbuy_comboBox = new JComboBox();
		sellbuy_comboBox.addItem("매수");
		sellbuy_comboBox.addItem("매도");
		SellBuyChangeListener sbListener = new SellBuyChangeListener();
		sellbuy_comboBox.addItemListener(sbListener);;
		panel.add(sellbuy_comboBox);
		
		label = new JLabel("계좌번호");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
		
		accountNum_comboBox = new JComboBox();
		insert_Accountnum_Combobox(accountNum_comboBox);	
		panel.add(accountNum_comboBox);
		
		label_1 = new JLabel("종목명");
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);		
		
			
		
		insert_ItemCode_Combobox(stclist);		
		itemCode_comboBoxs = new AutoSuggest(stclist_name.toArray());
		itemCode_comboBoxs.setEditable(true);
		ItemCodeListener itemcodeListener = new ItemCodeListener();		
		itemCode_comboBoxs.addItemListener(itemcodeListener);
		panel.add(itemCode_comboBoxs);
		
		
		label_2 = new JLabel("주문 수량");
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);
		
		orderQuan_spinner = new JSpinner();
		orderQuan_spinner.setEnabled(false);
		orderQuan_spinner.setEditor(new JSpinner.DefaultEditor(orderQuan_spinner));
		orderQuan_spinner.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		QuanChangeListener quanListener = new QuanChangeListener();
		orderQuan_spinner.addChangeListener(quanListener);
		panel.add(orderQuan_spinner);
		
		label_3 = new JLabel("주문 단가");
		label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);
		
		orderUnitPrice_spinner = new JSpinner();
		orderUnitPrice_spinner.setEnabled(false);
		orderUnitPrice_spinner.setEditor(new JSpinner.DefaultEditor(orderUnitPrice_spinner));
		orderUnitPrice_spinner.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		PriceChangeListener priceListener = new PriceChangeListener();
		orderUnitPrice_spinner.addChangeListener(priceListener);
		panel.add(orderUnitPrice_spinner);
		
		label_4 = new JLabel("총 합계 :");
		label_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);
		
		JButton orderButton = new JButton("주문");
		OrderActionListener orderListenr = new OrderActionListener();
		orderButton.addActionListener(orderListenr);
		panel.add(orderButton);
	}	
	
	
	public void insert_Accountnum_Combobox(JComboBox accountNum_comboBox){
		OdBeforeinit od = new OdBeforeinit();
		od.tradeInit();
		Object[] a = od.getAccountNum();
		for(int i=0; i<a.length; i++){
			accountNum_comboBox.addItem(a[i]);
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
		System.out.println(count);
		for(int i=0; i<count; i++){
			stclist_name_possible.add(possible.getDvalSella(i).get(1).toString());
		}				
	}
	
	/*리스너*/
	
	
	
	public class SellBuyChangeListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getItem()=="매수"){
				itemCode_comboBoxs = new AutoSuggest(stclist_name.toArray());
				itemCode_comboBoxs.setEditable(true);
				ItemCodeListener itemcodeListener = new ItemCodeListener();		
				itemCode_comboBoxs.addItemListener(itemcodeListener);
			}				
			else{
				insert_SellItem_Combobox();
				itemCode_comboBoxs = new AutoSuggest(stclist_name_possible.toArray());
				itemCode_comboBoxs.setEditable(true);
				ItemCodeListener itemcodeListener = new ItemCodeListener();		
				itemCode_comboBoxs.addItemListener(itemcodeListener);
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
							//System.out.println(price_before);
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
							//System.out.println(price_before);							
							possible.setvalSella((String)accountNum_comboBox.getSelectedItem(), "10", s, '1', '1', "", "00", "", '0', "00", 1);
							orderQuan_spinner.setValue(possible.getDvalSella(0).get(12));
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
