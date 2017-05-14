package test.khopenapi.events;

import com4j.*;

@IID("{7335F12D-8973-4BD5-B7F0-12DF03D175B7}")
public abstract class _DKHOpenAPIEvents {
  // Methods:
  /**
   * @param sScrNo Mandatory java.lang.String parameter.
   * @param sRQName Mandatory java.lang.String parameter.
   * @param sTrCode Mandatory java.lang.String parameter.
   * @param sRecordName Mandatory java.lang.String parameter.
   * @param sPrevNext Mandatory java.lang.String parameter.
   * @param nDataLength Mandatory int parameter.
   * @param sErrorCode Mandatory java.lang.String parameter.
   * @param sMessage Mandatory java.lang.String parameter.
   * @param sSplmMsg Mandatory java.lang.String parameter.
   */

  @DISPID(1)
  public void onReceiveTrData(
    java.lang.String sScrNo,
    java.lang.String sRQName,
    java.lang.String sTrCode,
    java.lang.String sRecordName,
    java.lang.String sPrevNext,
    int nDataLength,
    java.lang.String sErrorCode,
    java.lang.String sMessage,
    java.lang.String sSplmMsg) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param sRealKey Mandatory java.lang.String parameter.
   * @param sRealType Mandatory java.lang.String parameter.
   * @param sRealData Mandatory java.lang.String parameter.
   */

  @DISPID(2)
  public void onReceiveRealData(
    java.lang.String sRealKey,
    java.lang.String sRealType,
    java.lang.String sRealData) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param sScrNo Mandatory java.lang.String parameter.
   * @param sRQName Mandatory java.lang.String parameter.
   * @param sTrCode Mandatory java.lang.String parameter.
   * @param sMsg Mandatory java.lang.String parameter.
   */

  @DISPID(3)
  public void onReceiveMsg(
    java.lang.String sScrNo,
    java.lang.String sRQName,
    java.lang.String sTrCode,
    java.lang.String sMsg) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param sGubun Mandatory java.lang.String parameter.
   * @param nItemCnt Mandatory int parameter.
   * @param sFIdList Mandatory java.lang.String parameter.
   */

  @DISPID(4)
  public void onReceiveChejanData(
    java.lang.String sGubun,
    int nItemCnt,
    java.lang.String sFIdList) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param nErrCode Mandatory int parameter.
   */

  @DISPID(5)
  public void onEventConnect(
    int nErrCode) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param sRealKey Mandatory java.lang.String parameter.
   */

  @DISPID(6)
  public void onReceiveInvestRealData(
    java.lang.String sRealKey) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   * @param strType Mandatory java.lang.String parameter.
   * @param strConditionName Mandatory java.lang.String parameter.
   * @param strConditionIndex Mandatory java.lang.String parameter.
   */

  @DISPID(7)
  public void onReceiveRealCondition(
    java.lang.String sTrCode,
    java.lang.String strType,
    java.lang.String strConditionName,
    java.lang.String strConditionIndex) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param sScrNo Mandatory java.lang.String parameter.
   * @param strCodeList Mandatory java.lang.String parameter.
   * @param strConditionName Mandatory java.lang.String parameter.
   * @param nIndex Mandatory int parameter.
   * @param nNext Mandatory int parameter.
   */

  @DISPID(8)
  public void onReceiveTrCondition(
    java.lang.String sScrNo,
    java.lang.String strCodeList,
    java.lang.String strConditionName,
    int nIndex,
    int nNext) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param lRet Mandatory int parameter.
   * @param sMsg Mandatory java.lang.String parameter.
   */

  @DISPID(9)
  public void onReceiveConditionVer(
    int lRet,
    java.lang.String sMsg) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
