package test;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import test.CpSysDib.*;
import trade.Inquiry;
import trade.OdBeforeinit;
import com4j.*;

public class JTableExam
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
	//JTable jTable = new JTable(getTableModel());
	
	//JScrollPane�� JTable�� ���
	//JScrollPane jScollPane = new JScrollPane(jTable);
	
	
	public JTableExam()
	{
		//jFrame.add(jScollPane);
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
		jFrame.setSize(500, 300);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	/*
	public DefaultTableModel getTableModel(){		
		String columnNames[] =
			{ "���� �ܰ����", "ü�� �ܰ����", "�򰡱ݾ�", "�򰡼���", "����ݾ�", "������", "D+2 ���� ������", "�ܰ��򰡱ݾ�" };
		
		Inquiry concribalance = new Inquiry();
		OdBeforeinit od = new OdBeforeinit();
		Object[] a = od.getAccountNum();
		
		concribalance.setvalConcribalance("01", 14);		
		Object[][] record = {concribalance.getHvalConcribalance(1).toArray()};
		record[0][11] = null; record[0][9] = null; record[0][6] = null; record[0][0] = null;//���ֱݾ�, �����򰡱ݾ�, ���Ű���, ���¸� ����		
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(record, columnNames); //DefaultTableModel�� �����ϰ� ������ ���		
		
		System.out.println(concribalance.getHvalConcribalance(1).get(0).toString());
		return defaultTableModel;
	}

	public static void main(String[] args)
	{
		JTableExam jt = new JTableExam();		
	}
	*/
}