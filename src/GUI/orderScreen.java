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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
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

import GUI.AutoSuggest;
import trade.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;



public class orderScreen extends JFrame {
	
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private sellbuyOrder sellbuy_panel;
	private cancelalterOrder alter_panel;
	private chartScreen chart_panel;
	private UpdateListener update;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 518);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		update = new UpdateListener();
		addContainerListener(update);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		sellbuy_panel = new sellbuyOrder();
		tabbedPane.addTab("주문 신청(현금)", null, sellbuy_panel, null);
			
		alter_panel = new cancelalterOrder();
		tabbedPane.addTab("주문 정정/취소", null, alter_panel, null);
		
		chart_panel = new chartScreen();
		tabbedPane.addTab("차트", null, chart_panel, null);	
		
	}
	public class UpdateListener implements ContainerListener{

		@Override
		public void componentAdded(ContainerEvent e) {
			tabbedPane.addTab("차트", null, chart_panel, null);	
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentRemoved(ContainerEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		
	}
	

		

}
