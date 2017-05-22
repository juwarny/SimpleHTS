import test.cputil.*;
import com4j.*;

public class CybosCondition {
	private ICpCybos bos = test.cputil.ClassFactory.createCpCybos();
	
	public int isConnect(){
		return bos.isConnect();
	}
	public int serverType(){
		return bos.serverType();
	}
	public int limitRequestRemainTime(){
		return bos.limitRequestRemainTime();		
	}
	public int limitRequestRemainCount(int limitType){
		if(limitType == 0){
			return bos.getLimitRemainCount(test.cputil.LIMIT_TYPE.LT_TRADE_REQUEST);
			//�ֹ����� RQ ��û
		}
		else if(limitType == 1){
			return bos.getLimitRemainCount(test.cputil.LIMIT_TYPE.LT_NONTRADE_REQUEST);
			//�ü����� RQ ��û
		}
		else {
			return bos.getLimitRemainCount(test.cputil.LIMIT_TYPE.LT_SUBSCRIBE);
			//�ü����� SB
		}				
	}
	public static void main(String[]args){
		CybosCondition bos = new CybosCondition();
		System.out.println(bos.isConnect());
		System.out.println(bos.limitRequestRemainTime());
		System.out.println(bos.limitRequestRemainCount(0));
		System.out.println(bos.limitRequestRemainCount(1));
		System.out.println(bos.limitRequestRemainCount(2));
		System.out.println(bos.serverType());
	}

}
