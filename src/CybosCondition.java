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
	public int limitRequestRemainTime(int limitType){
		if(limitType == 0){
			return bos.getLimitRemainCount(test.cputil.LIMIT_TYPE.LT_TRADE_REQUEST);
			//林巩包访 RQ 夸没
		}
		else if(limitType == 1){
			return bos.getLimitRemainCount(test.cputil.LIMIT_TYPE.LT_NONTRADE_REQUEST);
			//矫技包访 RQ 夸没
		}
		else {
			return bos.getLimitRemainCount(test.cputil.LIMIT_TYPE.LT_SUBSCRIBE);
			//矫技包访 SB
		}				
	}

}
