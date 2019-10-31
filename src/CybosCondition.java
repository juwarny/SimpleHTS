import test.cputil.*;
import com4j.*;

//CYBOS의 각종 상태를 확인할 수 있음.

public class CybosCondition {
	private ICpCybos bos = test.cputil.ClassFactory.createCpCybos();
	
	public int isConnect(){
		return bos.isConnect();
	}//반환값: 0- 연결 끊김, 1- 연결 정상
	public int serverType(){
		return bos.serverType();
	}//반환값: 0- 연결 끊김, 1- cybosplus 서버, 2- HTS 보통서버(cybosplus 서버 제외)
	public int limitRequestRemainTime(){
		return bos.limitRequestRemainTime();		
	}//요청 개수를 재계산하기까지 남은 시간을 반환합니다
	public int limitRequestRemainCount(int limitType){
		if(limitType == 0){
			return bos.getLimitRemainCount(test.cputil.LIMIT_TYPE.LT_TRADE_REQUEST);
			//주문관련 RQ 요청
		}
		else if(limitType == 1){
			return bos.getLimitRemainCount(test.cputil.LIMIT_TYPE.LT_NONTRADE_REQUEST);
			//시세관련 RQ 요청
		}
		else {
			return bos.getLimitRemainCount(test.cputil.LIMIT_TYPE.LT_SUBSCRIBE);
			//시세관련 SB
		}				
	}
}
