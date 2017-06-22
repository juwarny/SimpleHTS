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
	JFrame jFrame = new JFrame("JTable 예제");
	

	String columnNames[] =
	{ "상품번호", "상품이름", "상품가격", "상품설명" };

	Object rowData[][] =
	{
	{ 1, "맛동산", 100, "오리온" },
	{ 2, "아폴로", 200, "불량식품" },
	{ 3, "칸쵸코", 300, "과자계의 레전드" } };
	
	/*
	//DefaultTableModel을 선언하고 데이터 담기
	DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames);
	*/
	//JTable에 DefaultTableModel을 담기
	//JTable jTable = new JTable(getTableModel());
	
	//JScrollPane에 JTable을 담기
	//JScrollPane jScollPane = new JScrollPane(jTable);
	
	
	public JTableExam()
	{
		//jFrame.add(jScollPane);
		/*
		//행 한줄 추가!
		Object [] temporaryObject = { 4, "초코송이", 500, "식품계의 절대강자" };
		defaultTableModel.addRow(temporaryObject);
		
		//행과 열 갯수 구하기
		System.out.println(defaultTableModel.getRowCount());
		System.out.println(defaultTableModel.getColumnCount());
		
		//컬럼(열)의 index는 0부터 시작한다!!
		System.out.println(defaultTableModel.getColumnName(0));
		
		//0행을 삭제하면 제목행을 제외하고 첫째행을 삭제한다!!
		defaultTableModel.removeRow(0);
		
		//값을 얻어올 때도 0부터 index가 시작된다는 것에 주의한다!!
		System.out.println(defaultTableModel.getValueAt(2, 2));
		
		//특정 좌표의 값을 바꾸는 것은 setValueAt()
		defaultTableModel.setValueAt("5000", 2, 2);
		
		//테이블에 Row를 미리 선택한 상태로 만들기!
		jTable.setRowSelectionInterval(1, 1);
		*/
		jFrame.setSize(500, 300);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	/*
	public DefaultTableModel getTableModel(){		
		String columnNames[] =
			{ "결제 잔고수량", "체결 잔고수량", "평가금액", "평가손익", "대출금액", "수익율", "D+2 예상 예수금", "잔고평가금액" };
		
		Inquiry concribalance = new Inquiry();
		OdBeforeinit od = new OdBeforeinit();
		Object[] a = od.getAccountNum();
		
		concribalance.setvalConcribalance("01", 14);		
		Object[][] record = {concribalance.getHvalConcribalance(1).toArray()};
		record[0][11] = null; record[0][9] = null; record[0][6] = null; record[0][0] = null;//대주금액, 대주평가금액, 수신개수, 계좌명 삭제		
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(record, columnNames); //DefaultTableModel을 선언하고 데이터 담기		
		
		System.out.println(concribalance.getHvalConcribalance(1).get(0).toString());
		return defaultTableModel;
	}

	public static void main(String[] args)
	{
		JTableExam jt = new JTableExam();		
	}
	*/
}