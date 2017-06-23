package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.INTERNAL;

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
import javax.swing.JTabbedPane;
import trade.*;
import java.awt.Scrollbar;
import java.awt.FlowLayout;
//계좌정보를 띄워주는 패널 클라스
public class accountScreen extends JPanel {

	private JPanel contentPane;
	private JTable total_table;
	private JTable item_table;
	private JScrollPane scroller;
	private JPanel panel;
	private ArrayList<Object> records;
	private ArrayList<Object> allinfo;
	private ArrayList<Object[]> data;
	private Object[] accountlist;
	private JLabel account;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JPanel panel_1;
	
	public accountScreen(Object[] accountNum) {
		accountlist = accountNum;
		setBorder(new EmptyBorder(5, 5, 5, 5));
			
		account = new JLabel("계좌명 : "+accountlist[0].toString());
		account.setHorizontalAlignment(SwingConstants.CENTER);

		String[] total_columnNames =
			{ "결제 잔고수량", "체결 잔고수량", "평가금액", "평가손익", "수익률(%)", "D+2 예상 예수금", "잔고평가금액"};
	
		String[] item_columnNames =
			{ "종목명", "결제잔고수량", "결제장부단가", "체결잔고수량", "평가금액(원)", "평가손익(원)", "수익률(%)", "매도가능수량","체결장부단가","손익단가"}; 
		
		setLayout(new BorderLayout(0, 0));
		
				
		panel_1 = new JPanel();
		
		JLabel total_label = new JLabel("계좌 잔고 및 평가");		
		total_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		total_table = new JTable();	
		total_table.setVisible(true);
		total_table.setModel(getTotalTableModel(total_columnNames));
		
		JLabel item_label = new JLabel("종목별 주문 및 잔고 현광");		
		item_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		item_table = new JTable();
		item_table.setVisible(true);
		item_table.setModel(getItemTableModel(item_columnNames));		
		
		scrollPane = new JScrollPane(total_table);
		scrollPane_1 = new JScrollPane(item_table);
		
	
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		panel_1.add(total_label);
		panel_1.add(scrollPane);
		panel_1.add(item_label);
		panel_1.add(scrollPane_1);
		add(account,BorderLayout.NORTH);
		add(panel_1, BorderLayout.CENTER);
	}
	
	public DefaultTableModel getTotalTableModel(String[] columnNames){		
		
		Inquiry concribalance = new Inquiry();
		records = new ArrayList<Object>();
		
		concribalance.setvalConcribalance(accountlist[0].toString(),"10", 1);
		allinfo = concribalance.getHvalConcribalance(1);
		
		records.add(allinfo.get(1));
		records.add(allinfo.get(2));
		records.add(allinfo.get(3));
		records.add(allinfo.get(4));
		records.add(allinfo.get(8));
		records.add(allinfo.get(9));
		records.add(allinfo.get(11));

		//대주금액, 대주평가금액, 수신개수, 계좌명 삭제		
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(columnNames, 1);
		defaultTableModel.addRow(records.toArray());
		System.out.println(records.get(1));
		
		return defaultTableModel;
	}
	public DefaultTableModel getItemTableModel(String[] columnNames){		
		
		Inquiry concribalance = new Inquiry();
		
		data = new ArrayList<Object[]>();
		
		concribalance.setvalConcribalance(accountlist[0].toString(),"10", 1);
		int count = Integer.parseInt(concribalance.getHvalConcribalance(1).get(7).toString());//수신개수
		
		for(int i=0; i<count; i++){
			concribalance.setvalConcribalance(accountlist[0].toString(),"10", 1);
			allinfo = concribalance.getDvalConcribalance(1, i);
			records = new ArrayList<Object>();
			
			records.add(allinfo.get(0));
			records.add(allinfo.get(3));
			records.add(allinfo.get(4));
			records.add(allinfo.get(7));
			records.add(allinfo.get(9));
			records.add(allinfo.get(10));
			records.add(allinfo.get(11));
			records.add(allinfo.get(15));
			records.add(allinfo.get(17));
			records.add(allinfo.get(18));
			
			data.add(records.toArray());
		}		

		//대주금액, 대주평가금액, 수신개수, 계좌명 삭제		
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(columnNames, data.size());
		for(Object[]j : data){
			defaultTableModel.addRow(j);
		}	
		
		return defaultTableModel;
	}
}
