package GUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import test.CpSysDib.*;
import trade.Inquiry;
import trade.OdBeforeinit;
import com4j.*;
import stock.Finance_info_Scraping;

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
	
	/*
	//DefaultTableModel�� �����ϰ� ������ ���
	DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames);
	*/
	//JTable�� DefaultTableModel�� ���
	private JTable jTable;
	
	//JScrollPane�� JTable�� ���
	private JScrollPane jScollPane;	
	private Finance_info_Scraping fis;
	
	public FinanceInfoTable()
	{
		fis = new Finance_info_Scraping();		
		fis.setFinanceTableData("A225530");
		jTable = new JTable(getTableModel());
		jScollPane = new JScrollPane(jTable);
		jFrame.add(jScollPane);		
		/*
		//�� ���� �߰�!
		Object [] temporaryObject = { 4, "���ڼ���", 500, "��ǰ���� ���밭��" };
		defaultTableModel.addRow(temporaryObject);
		
		//��� �� ���� ���ϱ�
		System.out.println(defaultTableModel.getRowCount());
		System.out.println(defaultTableModel.getColumnCount());
		
		//�÷�(��)�� index�� 0���� �����Ѵ�!!
		System.out.println(defaultTableModel.getColumnName(0));
		
		//0���� �����ϸ� �������� �����ϰ� ù°���� �����Ѵ�!!
		defaultTableModel.removeRow(0);
		
		//���� ���� ���� 0���� index�� ���۵ȴٴ� �Ϳ� �����Ѵ�!!
		System.out.println(defaultTableModel.getValueAt(2, 2));
		
		//Ư�� ��ǥ�� ���� �ٲٴ� ���� setValueAt()
		defaultTableModel.setValueAt("5000", 2, 2);
		
		//���̺� Row�� �̸� ������ ���·� �����!
		jTable.setRowSelectionInterval(1, 1);
		*/
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
		//defaultTableModel.
		
		return defaultTableModel;
	}

	public static void main(String[] args)
	{
		FinanceInfoTable jt = new FinanceInfoTable();		
	}
}