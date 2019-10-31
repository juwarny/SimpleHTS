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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.*;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.time.*;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;

import GUI.sellbuyOrder.ItemCodeListener;
import stock.*;
import test.*;
//10차 호가 데이터를 차트로 보여주는 패널 클라스
public class Jpbid1sUpdate extends JPanel {
	private StockCode stc;// stock list
	private StockJpBid jbid;
	private StockChart stchart;
	private JFreeChart chart;
	private ChartPanel chartpanel;
	private String name; 
	private DefaultCategoryDataset barChartData;
	
	public Jpbid1sUpdate(String name) {
		stc = new StockCode();
		jbid = new StockJpBid();
		stchart = new StockChart();
		this.name = name;
		barChartData = new DefaultCategoryDataset();
		
		setLayout(new BorderLayout(0, 0));
		chart = ChartFactory.createBarChart("", "", "Call Volume", setData(), PlotOrientation.HORIZONTAL, false, true, false);
		setChart();		
		
		chartpanel = new ChartPanel(chart);
		add(chartpanel, BorderLayout.CENTER);		
	}
	public void go() throws InterruptedException{//1초에 한번씩 데이터와 차트를 갱신...
		while(true){
			Thread.sleep(1000);
			barChartData = new DefaultCategoryDataset();
			chart = ChartFactory.createBarChart("", "", "Call Volume", setData(), PlotOrientation.HORIZONTAL, false, true, false);
			setChart();
			chartpanel.removeAll();
			chartpanel.setChart(chart);			
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDataset setData() {//선택된 종목명으로 다시 데이터를 받아와 차트를 갱신한다.
		
		String s = stc.NameToCode(name);
		jbid.setvalStockJpBid(s);
		String buy = "buy";
		String sell = "sell";		
		ArrayList<Object[]> list = jbid.getDvalStockmst();
		int count = barChartData.getRowCount();
				
		
		//하다보니 하드코딩이...
		barChartData.setValue(Long.parseLong(list.get(9)[3].toString()), buy, list.get(9)[1].toString());
		barChartData.setValue(Long.parseLong(list.get(8)[3].toString()), buy, list.get(8)[1].toString());
		barChartData.setValue(Long.parseLong(list.get(7)[3].toString()), buy, list.get(7)[1].toString());
		barChartData.setValue(Long.parseLong(list.get(6)[3].toString()), buy, list.get(6)[1].toString());
		barChartData.setValue(Long.parseLong(list.get(5)[3].toString()), buy, list.get(5)[1].toString());
		barChartData.setValue(Long.parseLong(list.get(4)[3].toString()), buy, list.get(4)[1].toString());
		barChartData.setValue(Long.parseLong(list.get(3)[3].toString()), buy, list.get(3)[1].toString());
		barChartData.setValue(Long.parseLong(list.get(2)[3].toString()), buy, list.get(2)[1].toString());
		barChartData.setValue(Long.parseLong(list.get(1)[3].toString()), buy, list.get(1)[1].toString());
		barChartData.setValue(Long.parseLong(list.get(0)[3].toString()), buy, list.get(0)[1].toString());
		barChartData.setValue(Long.parseLong(list.get(0)[2].toString()), sell, list.get(0)[0].toString());
		barChartData.setValue(Long.parseLong(list.get(1)[2].toString()), sell, list.get(1)[0].toString());
		barChartData.setValue(Long.parseLong(list.get(2)[2].toString()), sell, list.get(2)[0].toString());
		barChartData.setValue(Long.parseLong(list.get(3)[2].toString()), sell, list.get(3)[0].toString());
		barChartData.setValue(Long.parseLong(list.get(4)[2].toString()), sell, list.get(4)[0].toString());
		barChartData.setValue(Long.parseLong(list.get(5)[2].toString()), sell, list.get(5)[0].toString());
		barChartData.setValue(Long.parseLong(list.get(6)[2].toString()), sell, list.get(6)[0].toString());
		barChartData.setValue(Long.parseLong(list.get(7)[2].toString()), sell, list.get(7)[0].toString());
		barChartData.setValue(Long.parseLong(list.get(8)[2].toString()), sell, list.get(8)[0].toString());
		barChartData.setValue(Long.parseLong(list.get(9)[2].toString()), sell, list.get(9)[0].toString());
			
		return barChartData;
	}

	
	public void setChart() {//차트 설정값 세팅
				
		chart.setBackgroundPaint(Color.white);	
		CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinePaint(Color.white);
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        renderer.setSeriesPaint(1, Color.BLUE, false);		   
	}	
	

}




