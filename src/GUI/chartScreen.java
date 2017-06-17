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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
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
	private ArrayList<Object[]> stclist;
	private ArrayList<String> stclist_name;
	private ArrayList<String> stclist_name_possible;
	private StockChart stchart;
	private AutoSuggest itemCode_comboBoxs;
	private ArrayList<Object> fields;
	private ArrayList<Object[]> series;
	private JFreeChart chart;
	private List<OHLCDataItem> dataItems;
	private JPanel setting;
	private JComboBox day_option;
	private ChartPanel chartpanel;

	public chartScreen() {
		stc = new StockCode();
		stm = new StockMst();
		stchart = new StockChart();
		stclist = stc.getStockList();
		stclist_name = new ArrayList<String>();
		setLayout(new BorderLayout(0, 0));
		setting = new JPanel();
		day_option = new JComboBox();

		insert_ItemCode_Combobox(stclist);

		itemCode_comboBoxs = new AutoSuggest(stclist_name.toArray());
		itemCode_comboBoxs.setSelectedIndex(100);
		itemCode_comboBoxs.setEditable(true);
		ItemCodeListener itemcodeListener = new ItemCodeListener();
		itemCode_comboBoxs.addItemListener(itemcodeListener);

		day_option.addItem("일");
		day_option.addItem("주");
		day_option.addItem("월");
		day_option.addItem("분");
		day_option.addItem("틱");
		Day_OptionListener dayoptionListener = new Day_OptionListener();
		day_option.addItemListener(dayoptionListener);
		day_option.setSelectedIndex(0);

		setSeries(0, 0, null, 0);
		setChart(insert_OHLCData());

		setting.setLayout(new GridLayout(0, 2));
		setting.add(itemCode_comboBoxs);
		setting.add(day_option);
		add(setting, BorderLayout.EAST);
		chartpanel = new ChartPanel(chart);
		add(chartpanel, BorderLayout.CENTER);

	}

	public void insert_ItemCode_Combobox(ArrayList<Object[]> stclist) {
		stclist_name = new ArrayList<String>();
		for (int i = 0; i < stclist.size(); i++) {
			stclist_name.add(stclist.get(i)[1].toString());
		}
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
		XYPlot plot = (XYPlot) chart.getPlot();
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
		((CandlestickRenderer) plot.getRenderer()).setUpPaint(Color.RED);
		((CandlestickRenderer) plot.getRenderer()).setDownPaint(Color.BLUE);
		((CandlestickRenderer) plot.getRenderer()).setBaseOutlinePaint(Color.black);
	}

	public class ItemCodeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				remove(chartpanel);
				setSeries(1, 100, fields, 'D');
				setChart(insert_OHLCData());
				chartpanel = new ChartPanel(chart);
				add(chartpanel, BorderLayout.CENTER);
				updateUI();
			}

		}
	}

	public class Day_OptionListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				remove(chartpanel);
				switch (e.getItem().toString()) {
					case "분": {
						setSeries(1, 100, fields, 'm');
						setChart(insert_OHLCData());
					}
					case "일": {
						setSeries(1, 100, fields, 'D');
						setChart(insert_OHLCData());
					}
					case "월": {
						setSeries(1, 100, fields, 'M');
						setChart(insert_OHLCData());
					}
					case "주": {
						setSeries(1, 100, fields, 'W');
						setChart(insert_OHLCData());
					}
					case "틱": {
						setSeries(1, 100, fields, 'T');
						setChart(insert_OHLCData());
					}
				}
				chartpanel = new ChartPanel(chart);
				add(chartpanel, BorderLayout.CENTER);
				updateUI();
			}
		}

	}

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

}
