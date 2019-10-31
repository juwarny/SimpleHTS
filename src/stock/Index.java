package stock;

import java.util.*;
import com4j.*;
import test.CpIndexes.*;

public class Index {
	private ICpIndex cpindex;
	
	public Index(){
		cpindex = test.CpIndexes.ClassFactory.createCpIndex();
		//cpindex.
	}
	public void put_IndexDefault(String index_name){
		cpindex.put_IndexDefault((Object)index_name);
		cpindex.signalMA(MovingAverageType.mvSimple);
	}
	public void put_IndexKind(String index_name){
		cpindex.put_IndexKind((Object)index_name);
	}
	public void calculate(){
		cpindex.calculate();
	}
	public void update(){
		cpindex.update();
	}
	public Long getCount(int item){
		return Long.parseLong(((Object)cpindex.getCount(item)).toString());
	}
	public Object[] getChartIndexCodeListByIndex(int index){
		return (Object[])cpindex.getChartIndexCodeListByIndex(index);
	}
	public Long getLineCount(int item){
		return Long.parseLong(((Object)cpindex.getLineCount(item)).toString());
	}
	public Object[] getLineResult(int indexID){
		return (Object[]) cpindex.getLineResults(indexID);
	}
	public void makeIndSignal(){
		cpindex.makeIndSignal();
	}
	public void updateIndSignal(){
		cpindex.updateIndSignal();
	}
	public int getIndSignalResult(int item, int index){
		return cpindex.getIndSignalResult(item, index);
	}
	public static void main(String[]args){
		Index c = new Index();
		c.put_IndexKind("CCI");
		c.put_IndexDefault("CCI");
		c.calculate();
		//c.get
	}
	
}
