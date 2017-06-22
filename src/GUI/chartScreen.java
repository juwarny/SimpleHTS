package GUI;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.*;
import org.jfree.data.time.*;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;

import GUI.sellbuyOrder.ItemCodeListener;
import stock.*;
import test.*;

public class chartScreen extends JPanel {
	private StockCode stc;// stock list
	private StockMst stm;
	private ArrayList<String> stclist_name;
	private StockChart stchart;
	private AutoSuggest itemCode_comboBoxs;
	private ArrayList<Object> fields;
	private ArrayList<Object[]> series;
	private JFreeChart chart;
	private List<OHLCDataItem> dataItems;
	private JPanel setting;
	private JComboBox day_option;
	private ChartPanel chartpanel;
	private JLabel lblNewLabel;
	private JLabel label;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JTable table;
	private Finance_info_Scraping fis;
	private JScrollPane scrollPane;

	public chartScreen( ArrayList<String> list) {
		stc = new StockCode();
		stm = new StockMst();
		stchart = new StockChart();
		stclist_name = list;
		setLayout(new BorderLayout(0, 0));
		setting = new JPanel();
		day_option = new JComboBox();

		itemCode_comboBoxs = new AutoSuggest(stclist_name.toArray());
		itemCode_comboBoxs.setSelectedIndex(100);
		itemCode_comboBoxs.setEditable(true);
		itemCode_comboBoxs.setVisible(true);
		ItemCodeListener itemcodeListener = new ItemCodeListener();
		itemCode_comboBoxs.addItemListener(itemcodeListener);

		day_option.addItem("일");
		day_option.addItem("주");
		day_option.addItem("월");
		day_option.addItem("분");
		Day_OptionListener dayoptionListener = new Day_OptionListener();
		day_option.addItemListener(dayoptionListener);
		day_option.setSelectedIndex(0);

		setSeries(0, 0, null, 0);
		setChart(insert_OHLCData());
		setting.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("\uC885\uBAA9\uBA85");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		setting.add(lblNewLabel);
		setting.add(itemCode_comboBoxs);
		
		label = new JLabel("\uBD09 \uB2E8\uC704");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		setting.add(label);
		setting.add(day_option);
		
		chartpanel = new ChartPanel(chart);
		add(chartpanel, BorderLayout.CENTER);
		
		panel = new JPanel();
		add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_1, BorderLayout.SOUTH);
		
		
		table = new JTable();
		table.setVisible(true);
		setFis();
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(300, 402));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(setting, BorderLayout.NORTH);
		//add(scrollPane, BorderLayout.EAST);
	}
	public void setFis(){
		String s = itemCode_comboBoxs.getSelectedItem().toString();
		s = stc.NameToCode(s);
		fis = new Finance_info_Scraping(s);
		table.setModel(getTableModel());
		fis.setName_and_class();
		String info = fis.getName_and_class().get(0)+" | "+fis.getName_and_class().get(1)+" | "+fis.getName_and_class().get(2);
		lblNewLabel_1.setText(info);
	}
	
	public DefaultTableModel getTableModel(){
		fis.setSummaryTableData();
		DefaultTableModel defaultTableModel = new DefaultTableModel(); //DefaultTableModel을 선언하고 데이터 담기
		String[] col = {"시세현황", ""};
		defaultTableModel.setColumnIdentifiers(col);
		for(int i=0; i<fis.getData().size(); i++){
			Object[] a = {fis.getCol_name().get(i), fis.getData().get(i)};
			defaultTableModel.insertRow(i, a);
		}		
		return defaultTableModel;
	}

	public void setSeries(int option, int counts, ArrayList<Object> field, int date_option) {
		String s = itemCode_comboBoxs.getSelectedItem().toString();
		s = stc.NameToCode(s);
		if (option == 0) {// 기본설정
			fields = new ArrayList<Object>(Arrays.asList(0, 1, 2, 3, 4, 5, 8));
			counts = 100;
			stchart.setvalStkchart(s, '2', Integer.toString(counts), fields, 'D', '1', '1', '3');
			series = stchart.getDvalStkchart(counts);
		} else {
			fields = field;
			stchart.setvalStkchart(s, '2', Integer.toString(counts), fields, date_option, '1', '1', '3');
			series = stchart.getDvalStkchart(counts);
		}

	}

	public OHLCDataset insert_OHLCData() {
		dataItems = new ArrayList<OHLCDataItem>();
		for (Object[] i : series) {
			Date date = null;
			if (Long.parseLong(i[1].toString()) == Long.parseLong("0")) {
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				try {
					date = df.parse(i[0].toString());

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				DateFormat df = new SimpleDateFormat("yyyyMMddkmm");
				try {
					date = df.parse(i[0].toString() + i[1].toString());

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			double open = Double.parseDouble(i[2].toString());
			double high = Double.parseDouble(i[3].toString());
			double low = Double.parseDouble(i[4].toString());
			double close = Double.parseDouble(i[5].toString());
			double volume = Double.parseDouble(i[6].toString());

			OHLCDataItem item = new OHLCDataItem(date, open, high, low, close, volume);
			dataItems.add(item);
		}
		Collections.reverse(dataItems);
		OHLCDataItem[] data = dataItems.toArray(new OHLCDataItem[dataItems.size()]);
		OHLCDataset dataset = new DefaultOHLCDataset(itemCode_comboBoxs.getSelectedItem().toString(), data);
		return dataset;
	}

	public void setChart(OHLCDataset dataset) {
		chart = ChartFactory.createCandlestickChart("", "Time", "Price", dataset, false);
		
		chart.setBackgroundPaint(Color.white);

		// 4. Set a few custom plot features
		chart.getXYPlot().setRenderer(new MyCandlestickRenderer());
		XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.WHITE); // light yellow = new
												// Color(0xffffe0)
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.lightGray);
		((NumberAxis) plot.getRangeAxis()).setAutoRangeIncludesZero(false);
		// 5. Skip week-ends on the date axis
		((DateAxis) plot.getDomainAxis()).setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());

		// 6. No volume drawn
		((CandlestickRenderer) plot.getRenderer()).setDrawVolume(true);
		((CandlestickRenderer) plot.getRenderer()).setUpPaint(Color.red);
		((CandlestickRenderer) plot.getRenderer()).setDownPaint(Color.blue);
		((CandlestickRenderer) plot.getRenderer()).setUseOutlinePaint(false);	
		((CandlestickRenderer) plot.getRenderer()).setBaseOutlinePaint(Color.black);	   
	}
	
	public class MyCandlestickRenderer extends CandlestickRenderer {

	    @Override
	    public Paint getItemPaint(int row, int column) {

	        //determine up or down candle 
	        XYDataset dataset = getPlot().getDataset();
	        OHLCDataset highLowData = (OHLCDataset) dataset;
	        int series = row, item = column;
	        Number yOpen = highLowData.getOpen(series, item);
	        Number yClose = highLowData.getClose(series, item);
	        boolean isUpCandle = yClose.doubleValue() > yOpen.doubleValue();

	        //return the same color as that used to fill the candle
	        if (isUpCandle) {
	            return getUpPaint();
	        }
	        else {
	            return getDownPaint();
	        }
	    }
	}

	public class ItemCodeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				remove(chartpanel);
				setSeries(1, 100, fields, 'D');
				setChart(insert_OHLCData());
				day_option.setSelectedIndex(0);
				chartpanel = new ChartPanel(chart);
				add(chartpanel, BorderLayout.CENTER);
				setFis();
				updateUI();
			}

		}
	}

	public class Day_OptionListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if(e.getItem().toString().equals("분")){
					setSeries(1, 100, fields, 'm');
					setChart(insert_OHLCData());
				}
				else if(e.getItem().toString().equals("일")){
					setSeries(1, 100, fields, 'D');
					setChart(insert_OHLCData());
				}
				else if(e.getItem().toString().equals("월")){
					setSeries(1, 100, fields, 'M');
					setChart(insert_OHLCData());
				}
				else if(e.getItem().toString().equals("주")){
					setSeries(1, 100, fields, 'W');
					setChart(insert_OHLCData());
				}							
				remove(chartpanel);
				chartpanel = new ChartPanel(chart);
				add(chartpanel, BorderLayout.CENTER);
				updateUI();
			}
		}
	}
	/*
	public static void main(String[] args) {
		chartScreen c = new chartScreen();
		JFrame myFrame = new JFrame();
		myFrame.setResizable(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.getContentPane().add(c, BorderLayout.CENTER);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Insets insets = kit.getScreenInsets(myFrame.getGraphicsConfiguration());
		Dimension screen = kit.getScreenSize();
		myFrame.setSize((int) (screen.getWidth() - insets.left - insets.right),
				(int) (screen.getHeight() - insets.top - insets.bottom));
		myFrame.setLocation((int) (insets.left), (int) (insets.top));
		myFrame.setVisible(true);
	}
	*/
}




