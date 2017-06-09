package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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


public class mainScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen frame = new mainScreen();
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
	public mainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1078, 644);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu accountMenu = new JMenu("계좌정보");
		menuBar.add(accountMenu);
		
		JMenuItem balanceMenuItem = new JMenuItem("체결기준 잔고 조회/평가");
		accountMenu.add(balanceMenuItem);
		
		JMenuItem dayconcludMenuItem = new JMenuItem("금일 주문/체결 내역");
		accountMenu.add(dayconcludMenuItem);
		
		JMenu strategyMenu = new JMenu("전략");
		menuBar.add(strategyMenu);
		
		JMenuItem autoOrderMenuItem = new JMenuItem("자동주문");
		autoOrderMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		strategyMenu.add(autoOrderMenuItem);
		
		JMenuItem orderMenuItem = new JMenuItem("수동주문");
		strategyMenu.add(orderMenuItem);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("종목조회");
		strategyMenu.add(mntmNewMenuItem);
		
		JMenu settingMenu = new JMenu("설정");
		menuBar.add(settingMenu);
		
		JMenu helpMenu = new JMenu("도움말");
		menuBar.add(helpMenu);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
	}

}