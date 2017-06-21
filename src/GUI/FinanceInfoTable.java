package GUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import test.CpSysDib.*;
import trade.Inquiry;
import trade.OdBeforeinit;
import com4j.*;
import stock.Finance_info_Scraping;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class FinanceInfoTable
{
	JFrame jFrame = new JFrame("JTable ����");
	

	String columnNames[] =
	{ "��ǰ��ȣ", "��ǰ�̸�", "��ǰ����", "��ǰ����" };

	Object rowData[][] =
	{
	{ 1, "������", 100, "������" },
	{ 2, "������", 200, "�ҷ���ǰ" },
	{ 3, "ĭ����", 300, "���ڰ��� ������" } };
	
	
	private JTable jTable;	
	private JScrollPane jScollPane;	
	private Finance_info_Scraping fis;
	private JPanel panel;
	private JLabel lblNewLabel;
	
	public FinanceInfoTable()
	{
		fis = new Finance_info_Scraping("A005930");		
		fis.setFinanceStatementTableData();
		fis.setLogo();
		fis.setName_and_class();
		String s = fis.getName_and_class().toString();
		lblNewLabel = new JLabel(s);
		lblNewLabel.setIcon(fis.getLogo_image());
		
		System.out.println(s);
		jTable = new JTable(getTableModel());
		jScollPane = new JScrollPane(jTable);
		jFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		jFrame.getContentPane().add(jScollPane);
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		panel.add(lblNewLabel);
		//panel.add(jScollPane);
		jFrame.getContentPane().add(panel, BorderLayout.NORTH);		
					
		//���̺� Row�� �̸� ������ ���·� �����!
		//jTable.setRowSelectionInterval(1, 1);
		jFrame.setSize(1270, 720);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public DefaultTableModel getTableModel(){		
		
		
		Object[] columnNames = fis.getCol_name().toArray();
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(); //DefaultTableModel�� �����ϰ� ������ ���		
		defaultTableModel.setColumnIdentifiers(columnNames);
		for(int i=0; i<fis.getRecord().size(); i++){
			defaultTableModel.insertRow(i, fis.getRecord().get(i));
		}		
		return defaultTableModel;
	}

	public static void main(String[] args)
	{
		FinanceInfoTable jt = new FinanceInfoTable();		
	}
}