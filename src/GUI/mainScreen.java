package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import javafx.scene.control.TabPane;
import stock.StockCode;
import trade.OdBeforeinit;

import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import java.awt.Dimension;

//GUI에 있는 나머지 패널 클라스를 탭형태로 가져온다.

public class mainScreen extends JFrame {

	private JPanel contentPane;
	private menuActionListener menulistener;
	private JTabbedPane tabbedPane;
	private cancelalterOrder cao;
	private chartScreen cs;
	private accountScreen as;
	private FinanceNews2 fn;
	private sellbuyOrder sbo;
	private FinanceInfoTable fit;
	private StockCode stc;
	private ArrayList<Object[]> stclist;
	private ArrayList<String> stclist_name;
	private OdBeforeinit od;
	private Object[] accountNum;
	private click ck;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen frame = new mainScreen();
					frame.setVisible(true);
					frame.setListLoading(frame);
					frame.setAccountNumber();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		NativeInterface.runEventPump();
	}

	/**
	 * Create the frame.
	 */
	public mainScreen() {
		setTitle("Simple HTS");
		setSize(new Dimension(1280, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1078, 644);		
				
		menulistener = new menuActionListener();		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu accountMenu = new JMenu("계좌정보");
		menuBar.add(accountMenu);
		
		JMenuItem balanceMenuItem = new JMenuItem("체결기준 잔고 조회/평가");
		balanceMenuItem.addActionListener(menulistener);
		accountMenu.add(balanceMenuItem);	
		
		JMenu iteminfoMenu = new JMenu("종목");
		menuBar.add(iteminfoMenu);
		
		JMenuItem priceMenuItem = new JMenuItem("종목 시가");
		priceMenuItem.addActionListener(menulistener);
		iteminfoMenu.add(priceMenuItem);
		
		JMenuItem financeinfoMenuItem = new JMenuItem("종목 재무정보");
		financeinfoMenuItem.addActionListener(menulistener);
		iteminfoMenu.add(financeinfoMenuItem);
		
		JMenuItem newsMenuItem = new JMenuItem("종목 뉴스");
		newsMenuItem.addActionListener(menulistener);
		iteminfoMenu.add(newsMenuItem);
		
		JMenu orderMenu = new JMenu("주문");
		menuBar.add(orderMenu);
		
		JMenuItem sellbuyMenuItem = new JMenuItem("매수/매도");
		sellbuyMenuItem.addActionListener(menulistener);
		orderMenu.add(sellbuyMenuItem);
		
		JMenuItem altercancleMenuItem = new JMenuItem("정정/취소");
		altercancleMenuItem.addActionListener(menulistener);
		orderMenu.add(altercancleMenuItem);
		
		JMenu strategyMenu = new JMenu("전략");
		menuBar.add(strategyMenu);
		
		JMenuItem stgsettingMenuItem = new JMenuItem("전략설정");
		stgsettingMenuItem.addActionListener(menulistener);
		strategyMenu.add(stgsettingMenuItem);
		
		contentPane = new JPanel();
		contentPane.setAlignmentY(0.0f);
		contentPane.setAlignmentX(0.0f);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1062, 584);
		tabbedPane.setAlignmentX(0.0f);
		tabbedPane.setAlignmentY(0.0f);
		contentPane.add(tabbedPane);		
		
		ck = new click();
		tabbedPane.addMouseListener(ck);
	}
	public void setListLoading(JFrame f){//실행하기 전에 전체 종목명 리스트를 가져온다.		
		stc = new StockCode();
		stclist = stc.getStockList(f);
		insert_ItemCode_Combobox(stclist);				    
		
	}
	public void setAccountNumber(){//실행하기 전에 계좌비밀번호를 입력한다.
		od = new OdBeforeinit();
		od.tradeInit();		
		accountNum =  od.getAccountNum();
	}
	public void insert_ItemCode_Combobox(ArrayList<Object[]> stclist){//실행하기 전에 전체 종목명 리스트를 가져온다.
		stclist_name = new ArrayList<String>();		
		for(int i=0; i<stclist.size(); i++){
			stclist_name.add(stclist.get(i)[1].toString());
		}	
	}
	
	//리스너
	public class menuActionListener implements  ActionListener{//메뉴를 선택하면 해당 패널을 탭에 생성
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("체결기준 잔고 조회/평가")){
				as = new accountScreen(accountNum);
				tabbedPane.addTab("체결기준 잔고 조회/평가", null, as, null);
				tabbedPane.setSelectedComponent(as);
			}			
			else if(e.getActionCommand().equals("종목 시가")){
				cs = new chartScreen(stclist_name);
				tabbedPane.addTab("종목 시가", null, cs, null);
				tabbedPane.setSelectedComponent(cs);
			}
			else if(e.getActionCommand().equals("종목 재무정보")){
				fit = new FinanceInfoTable(stclist_name);
				tabbedPane.addTab("종목 재무정보", null, fit, null);
				tabbedPane.setSelectedComponent(fit);
			}
			else if(e.getActionCommand().equals("종목 뉴스")){
				fn = new FinanceNews2("A005935", stclist_name);
				tabbedPane.addTab("종목 뉴스", fn);	
				tabbedPane.setSelectedComponent(fn);
			}
			else if(e.getActionCommand().equals("매수/매도")){
				sbo = new sellbuyOrder(stclist_name, accountNum);
				tabbedPane.addTab("매수/매도", sbo);
				tabbedPane.setSelectedComponent(sbo);
			}
			else if(e.getActionCommand().equals("정정/취소")){
				cao = new cancelalterOrder(accountNum);
				tabbedPane.addTab("정정/취소", cao);
				tabbedPane.setSelectedComponent(cao);
			}
			else if(e.getActionCommand().equals("전략설정")){
				
			}			
		}
		
	}
	public class click implements MouseListener{//탭을 더블클릭하면 해당 탭을 삭제시킴

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2){				
				int index = tabbedPane.getSelectedIndex();
				tabbedPane.removeTabAt(index);
			}			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
}
