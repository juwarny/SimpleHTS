import test.cputil.*;
import com4j.*;

//CYBOS�� ���� ���¸� Ȯ���� �� ����.

public class CybosCondition {
	private ICpCybos bos = test.cputil.ClassFactory.createCpCybos();
	
	public int isConnect(){
		return bos.isConnect();
	}//��ȯ��: 0- ���� ����, 1- ���� ����
	public int serverType(){
		return bos.serverType();
	}//��ȯ��: 0- ���� ����, 1- cybosplus ����, 2- HTS ���뼭��(cybosplus ���� ����)
	public int limitRequestRemainTime(){
		return bos.limitRequestRemainTime();		
	}//��û ������ �����ϱ���� ���� �ð��� ��ȯ�մϴ�
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
}
