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
	private JTable jTable;
	
	//JScrollPane에 JTable을 담기
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
		jFrame.setSize(1270, 720);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public DefaultTableModel getTableModel(){		
		
		
		Object[] columnNames = fis.getCol_name().toArray();
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(); //DefaultTableModel을 선언하고 데이터 담기		
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