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
import javax.swing.JTabbedPane;
import trade.*;
import java.awt.Scrollbar;
import java.awt.FlowLayout;

public class accountScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private ArrayList<Object> records;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accountScreen frame = new accountScreen();
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
	public accountScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1078, 644);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		table = new JTable(getTableModel());		
		panel.add(table);
		
		//JScrollPane jScollPane = new JScrollPane(table);
	}
	
	public DefaultTableModel getTableModel(){		
		String columnNames[] =
			{ "결제 잔고수량", "체결 잔고수량", "평가금액", "평가손익", "수익율", "D+2 예상 예수금", "잔고평가금액"};
		
		Inquiry concribalance = new Inquiry();
		records = new ArrayList<Object>();
		concribalance.setvalConcribalance("10", 14);
		records.add(concribalance.getHvalConcribalance(0).get(1));
		records.add(concribalance.getHvalConcribalance(0).get(2));
		records.add(concribalance.getHvalConcribalance(0).get(3));
		records.add(concribalance.getHvalConcribalance(0).get(4));
		records.add(concribalance.getHvalConcribalance(0).get(6));
		records.add(concribalance.getHvalConcribalance(0).get(8));
		records.add(concribalance.getHvalConcribalance(0).get(9));
		records.add(concribalance.getHvalConcribalance(0).get(11));

		Object[][] datas = {records.toArray()};
		//대주금액, 대주평가금액, 수신개수, 계좌명 삭제		
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(datas, columnNames); //DefaultTableModel을 선언하고 데이터 담기		
		for(Object j : concribalance.getHvalConcribalance(1)){
			System.out.println(j);
		}
		return defaultTableModel;
	}
}
