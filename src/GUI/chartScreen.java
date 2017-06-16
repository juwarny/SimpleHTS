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
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;

import GUI.sellbuyOrder.ItemCodeListener;
import stock.*;
import test.*;

public class chartScreen extends JPanel{
	private StockCode stc;//stock list
	private StockMst stm;	
	private ArrayList<Object[]> stclist;
	private ArrayList<String> stclist_name;
	private ArrayList<String> stclist_name_possible;
	private StockChart stchart;
	private JComboBox itemCode_comboBoxs;
	private ArrayList<Object> fields;
	private ArrayList<Object[]> series;
	private JFreeChart chart;
	private List<OHLCDataItem> dataItems;
	
	public chartScreen(){
		stc = new StockCode();
		stm = new StockMst();
		stchart = new StockChart();
		stclist = stc.getStockList();
		stclist_name = new ArrayList<String>();
		
		insert_ItemCode_Combobox(stclist);		
		itemCode_comboBoxs = new AutoSuggest(stclist_name.toArray());
		itemCode_comboBoxs.setEditable(true);
		ItemCodeListener itemcodeListener = new ItemCodeListener();		
		itemCode_comboBoxs.addItemListener(itemcodeListener);
		add(itemCode_comboBoxs);
		itemCode_comboBoxs.setSelectedIndex(100);
		
		setSeries(1);
		setChart(insert_OHLCData());
		
		add(new ChartPanel(chart));

	}
	public void insert_ItemCode_Combobox(ArrayList<Object[]> stclist){
		stclist_name = new ArrayList<String>();		
		for(int i=0; i<stclist.size(); i++){
			stclist_name.add(stclist.get(i)[1].toString());
		}	
	}
	public void setSeries(int option){
		String s = itemCode_comboBoxs.getSelectedItem().toString();
		s = stc.NameToCode(s);
		if(option == 1){//기본설정
			fields = new ArrayList<Object>(Arrays.asList(0,1,2,3,4,5,8));
			int counts = 1000;
			stchart.setvalStkchart(s, '2', Integer.toString(counts), fields, 'D', '1', '1', '3');
			series = stchart.getDvalStkchart(counts);			
		}
	}
	public OHLCDataset insert_OHLCData(){
		dataItems = new ArrayList<OHLCDataItem>();
		for(Object[] i : series){
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date date = null;
			try {
				date = df.parse(i[0].toString());
						
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	public void setChart(OHLCDataset dataset){
		chart = ChartFactory.createCandlestickChart(itemCode_comboBoxs.getSelectedItem().toString(), "Time", "Price", dataset, false);
		
		chart.setBackgroundPaint(Color.white);

	     // 4. Set a few custom plot features
	     XYPlot plot = (XYPlot) chart.getPlot();
	     plot.setBackgroundPaint(Color.WHITE); // light yellow = new Color(0xffffe0)
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
	
	public class ItemCodeListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			 if (e.getStateChange() == ItemEvent.SELECTED) {
				 try{					 	
					 	
					 	
					}catch(Exception ex){
						ex.printStackTrace();
					}			        
			    }			
		}
		
	}
	public static void main(String[]args){
		chartScreen c = new chartScreen();
		JFrame myFrame = new JFrame();
	     myFrame.setResizable(true);
	     myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     myFrame.add(c, BorderLayout.CENTER);
	     Toolkit kit = Toolkit.getDefaultToolkit();
	     Insets insets = kit.getScreenInsets(myFrame.getGraphicsConfiguration());
	     Dimension screen = kit.getScreenSize();
	     myFrame.setSize((int) (screen.getWidth() - insets.left - insets.right), (int) (screen.getHeight() - insets.top - insets.bottom));
	     myFrame.setLocation((int) (insets.left), (int) (insets.top));
	     myFrame.setVisible(true);
	}
}
