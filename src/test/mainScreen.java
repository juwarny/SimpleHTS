package test;

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

public class mainScreen extends JFrame {

	private JPanel contentPane;
	private JTable stockBuyTable;

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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton accountSetButton = new JButton("계정");
		accountSetButton.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		accountSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(accountSetButton);
		
		JButton strategySetButton = new JButton("전략");
		strategySetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		strategySetButton.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		toolBar.add(strategySetButton);
		
		JButton settingButton = new JButton("설정");
		settingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		settingButton.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		toolBar.add(settingButton);
		
		stockBuyTable = new JTable();
		stockBuyTable.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		contentPane.add(stockBuyTable, BorderLayout.CENTER);
		
		JList list = new JList();
		contentPane.add(list, BorderLayout.EAST);
	}

}
