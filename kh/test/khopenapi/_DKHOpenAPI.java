package test.khopenapi  ;

import com4j.*;

@IID("{00020400-0000-0000-C000-000000000046}")
public interface _DKHOpenAPI extends Com4jObject {
  // Methods:
  /**
   */

  @DISPID(1)
  int commConnect();


  /**
   */

  @DISPID(2)
  void commTerminate();


  /**
   * @param sRQName Mandatory java.lang.String parameter.
   * @param sTrCode Mandatory java.lang.String parameter.
   * @param nPrevNext Mandatory int parameter.
   * @param sScreenNo Mandatory java.lang.String parameter.
   */

  @DISPID(3)
  int commRqData(
    java.lang.String sRQName,
    java.lang.String sTrCode,
    int nPrevNext,
    java.lang.String sScreenNo);


  /**
   * @param sTag Mandatory java.lang.String parameter.
   */

  @DISPID(4)
  java.lang.String getLoginInfo(
    java.lang.String sTag);


  /**
   * @param sRQName Mandatory java.lang.String parameter.
   * @param sScreenNo Mandatory java.lang.String parameter.
   * @param sAccNo Mandatory java.lang.String parameter.
   * @param nOrderType Mandatory int parameter.
   * @param sCode Mandatory java.lang.String parameter.
   * @param nQty Mandatory int parameter.
   * @param nPrice Mandatory int parameter.
   * @param sHogaGb Mandatory java.lang.String parameter.
   * @param sOrgOrderNo Mandatory java.lang.String parameter.
   */

  @DISPID(5)
  int sendOrder(
    java.lang.String sRQName,
    java.lang.String sScreenNo,
    java.lang.String sAccNo,
    int nOrderType,
    java.lang.String sCode,
    int nQty,
    int nPrice,
    java.lang.String sHogaGb,
    java.lang.String sOrgOrderNo);


  /**
   * @param sRQName Mandatory java.lang.String parameter.
   * @param sScreenNo Mandatory java.lang.String parameter.
   * @param sAccNo Mandatory java.lang.String parameter.
   * @param sCode Mandatory java.lang.String parameter.
   * @param lOrdKind Mandatory int parameter.
   * @param sSlbyTp Mandatory java.lang.String parameter.
   * @param sOrdTp Mandatory java.lang.String parameter.
   * @param lQty Mandatory int parameter.
   * @param sPrice Mandatory java.lang.String parameter.
   * @param sOrgOrdNo Mandatory java.lang.String parameter.
   */

  @DISPID(6)
  int sendOrderFO(
    java.lang.String sRQName,
    java.lang.String sScreenNo,
    java.lang.String sAccNo,
    java.lang.String sCode,
    int lOrdKind,
    java.lang.String sSlbyTp,
    java.lang.String sOrdTp,
    int lQty,
    java.lang.String sPrice,
    java.lang.String sOrgOrdNo);


  /**
   * @param sID Mandatory java.lang.String parameter.
   * @param sValue Mandatory java.lang.String parameter.
   */

  @DISPID(7)
  void setInputValue(
    java.lang.String sID,
    java.lang.String sValue);


  /**
   * @param sID Mandatory java.lang.String parameter.
   */

  @DISPID(8)
  int setOutputFID(
    java.lang.String sID);


  /**
   * @param sJongmokCode Mandatory java.lang.String parameter.
   * @param sRealType Mandatory java.lang.String parameter.
   * @param sFieldName Mandatory java.lang.String parameter.
   * @param nIndex Mandatory int parameter.
   * @param sInnerFieldName Mandatory java.lang.String parameter.
   */

  @DISPID(9)
  java.lang.String commGetData(
    java.lang.String sJongmokCode,
    java.lang.String sRealType,
    java.lang.String sFieldName,
    int nIndex,
    java.lang.String sInnerFieldName);


  /**
   * @param sScnNo Mandatory java.lang.String parameter.
   */

  @DISPID(10)
  void disconnectRealData(
    java.lang.String sScnNo);


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   * @param sRecordName Mandatory java.lang.String parameter.
   */

  @DISPID(11)
  int getRepeatCnt(
    java.lang.String sTrCode,
    java.lang.String sRecordName);


  /**
   * @param sArrCode Mandatory java.lang.String parameter.
   * @param bNext Mandatory int parameter.
   * @param nCodeCount Mandatory int parameter.
   * @param nTypeFlag Mandatory int parameter.
   * @param sRQName Mandatory java.lang.String parameter.
   * @param sScreenNo Mandatory java.lang.String parameter.
   */

  @DISPID(12)
  int commKwRqData(
    java.lang.String sArrCode,
    int bNext,
    int nCodeCount,
    int nTypeFlag,
    java.lang.String sRQName,
    java.lang.String sScreenNo);


  /**
   */

  @DISPID(13)
  java.lang.String getAPIModulePath();


  /**
   * @param sMarket Mandatory java.lang.String parameter.
   */

  @DISPID(14)
  java.lang.String getCodeListByMarket(
    java.lang.String sMarket);


  /**
   */

  @DISPID(15)
  int getConnectState();


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   */

  @DISPID(16)
  java.lang.String getMasterCodeName(
    java.lang.String sTrCode);


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   */

  @DISPID(17)
  int getMasterListedStockCnt(
    java.lang.String sTrCode);


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   */

  @DISPID(18)
  java.lang.String getMasterConstruction(
    java.lang.String sTrCode);


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   */

  @DISPID(19)
  java.lang.String getMasterListedStockDate(
    java.lang.String sTrCode);


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   */

  @DISPID(20)
  java.lang.String getMasterLastPrice(
    java.lang.String sTrCode);


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   */

  @DISPID(21)
  java.lang.String getMasterStockState(
    java.lang.String sTrCode);


  /**
   * @param strRecordName Mandatory java.lang.String parameter.
   */

  @DISPID(22)
  int getDataCount(
    java.lang.String strRecordName);


  /**
   * @param strRecordName Mandatory java.lang.String parameter.
   * @param nRepeatIdx Mandatory int parameter.
   * @param nItemIdx Mandatory int parameter.
   */

  @DISPID(23)
  java.lang.String getOutputValue(
    java.lang.String strRecordName,
    int nRepeatIdx,
    int nItemIdx);


  /**
   * @param strTrCode Mandatory java.lang.String parameter.
   * @param strRecordName Mandatory java.lang.String parameter.
   * @param nIndex Mandatory int parameter.
   * @param strItemName Mandatory java.lang.String parameter.
   */

  @DISPID(24)
  java.lang.String getCommData(
    java.lang.String strTrCode,
    java.lang.String strRecordName,
    int nIndex,
    java.lang.String strItemName);


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   * @param nFid Mandatory int parameter.
   */

  @DISPID(25)
  java.lang.String getCommRealData(
    java.lang.String sTrCode,
    int nFid);


  /**
   * @param nFid Mandatory int parameter.
   */

  @DISPID(26)
  java.lang.String getChejanData(
    int nFid);


  /**
   * @param nType Mandatory int parameter.
   */

  @DISPID(27)
  java.lang.String getThemeGroupList(
    int nType);


  /**
   * @param strThemeCode Mandatory java.lang.String parameter.
   */

  @DISPID(28)
  java.lang.String getThemeGroupCode(
    java.lang.String strThemeCode);


  /**
   */

  @DISPID(29)
  java.lang.String getFutureList();


  /**
   * @param nIndex Mandatory int parameter.
   */

  @DISPID(30)
  java.lang.String getFutureCodeByIndex(
    int nIndex);


  /**
   */

  @DISPID(31)
  java.lang.String getActPriceList();


  /**
   */

  @DISPID(32)
  java.lang.String getMonthList();


  /**
   * @param strActPrice Mandatory java.lang.String parameter.
   * @param nCp Mandatory int parameter.
   * @param strMonth Mandatory java.lang.String parameter.
   */

  @DISPID(33)
  java.lang.String getOptionCode(
    java.lang.String strActPrice,
    int nCp,
    java.lang.String strMonth);


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   * @param nCp Mandatory int parameter.
   * @param strMonth Mandatory java.lang.String parameter.
   */

  @DISPID(34)
  java.lang.String getOptionCodeByMonth(
    java.lang.String sTrCode,
    int nCp,
    java.lang.String strMonth);


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   * @param nCp Mandatory int parameter.
   * @param nTick Mandatory int parameter.
   */

  @DISPID(35)
  java.lang.String getOptionCodeByActPrice(
    java.lang.String sTrCode,
    int nCp,
    int nTick);


  /**
   * @param strBaseAssetCode Mandatory java.lang.String parameter.
   */

  @DISPID(36)
  java.lang.String getSFutureList(
    java.lang.String strBaseAssetCode);


  /**
   * @param strBaseAssetCode Mandatory java.lang.String parameter.
   * @param nIndex Mandatory int parameter.
   */

  @DISPID(37)
  java.lang.String getSFutureCodeByIndex(
    java.lang.String strBaseAssetCode,
    int nIndex);


  /**
   * @param strBaseAssetGb Mandatory java.lang.String parameter.
   */

  @DISPID(38)
  java.lang.String getSActPriceList(
    java.lang.String strBaseAssetGb);


  /**
   * @param strBaseAssetGb Mandatory java.lang.String parameter.
   */

  @DISPID(39)
  java.lang.String getSMonthList(
    java.lang.String strBaseAssetGb);


  /**
   * @param strBaseAssetGb Mandatory java.lang.String parameter.
   * @param strActPrice Mandatory java.lang.String parameter.
   * @param nCp Mandatory int parameter.
   * @param strMonth Mandatory java.lang.String parameter.
   */

  @DISPID(40)
  java.lang.String getSOptionCode(
    java.lang.String strBaseAssetGb,
    java.lang.String strActPrice,
    int nCp,
    java.lang.String strMonth);


  /**
   * @param strBaseAssetGb Mandatory java.lang.String parameter.
   * @param sTrCode Mandatory java.lang.String parameter.
   * @param nCp Mandatory int parameter.
   * @param strMonth Mandatory java.lang.String parameter.
   */

  @DISPID(41)
  java.lang.String getSOptionCodeByMonth(
    java.lang.String strBaseAssetGb,
    java.lang.String sTrCode,
    int nCp,
    java.lang.String strMonth);


  /**
   * @param strBaseAssetGb Mandatory java.lang.String parameter.
   * @param sTrCode Mandatory java.lang.String parameter.
   * @param nCp Mandatory int parameter.
   * @param nTick Mandatory int parameter.
   */

  @DISPID(42)
  java.lang.String getSOptionCodeByActPrice(
    java.lang.String strBaseAssetGb,
    java.lang.String sTrCode,
    int nCp,
    int nTick);


  /**
   */

  @DISPID(43)
  java.lang.String getSFOBasisAssetList();


  /**
   */

  @DISPID(44)
  java.lang.String getOptionATM();


  /**
   * @param strBaseAssetGb Mandatory java.lang.String parameter.
   */

  @DISPID(45)
  java.lang.String getSOptionATM(
    java.lang.String strBaseAssetGb);


  /**
   */

  @DISPID(46)
  java.lang.String getBranchCodeName();


  /**
   * @param sMarketGb Mandatory java.lang.String parameter.
   * @param sRQName Mandatory java.lang.String parameter.
   * @param sScreenNo Mandatory java.lang.String parameter.
   */

  @DISPID(47)
  int commInvestRqData(
    java.lang.String sMarketGb,
    java.lang.String sRQName,
    java.lang.String sScreenNo);


  /**
   * @param sRQName Mandatory java.lang.String parameter.
   * @param sScreenNo Mandatory java.lang.String parameter.
   * @param sAccNo Mandatory java.lang.String parameter.
   * @param nOrderType Mandatory int parameter.
   * @param sCode Mandatory java.lang.String parameter.
   * @param nQty Mandatory int parameter.
   * @param nPrice Mandatory int parameter.
   * @param sHogaGb Mandatory java.lang.String parameter.
   * @param sCreditGb Mandatory java.lang.String parameter.
   * @param sLoanDate Mandatory java.lang.String parameter.
   * @param sOrgOrderNo Mandatory java.lang.String parameter.
   */

  @DISPID(48)
  int sendOrderCredit(
    java.lang.String sRQName,
    java.lang.String sScreenNo,
    java.lang.String sAccNo,
    int nOrderType,
    java.lang.String sCode,
    int nQty,
    int nPrice,
    java.lang.String sHogaGb,
    java.lang.String sCreditGb,
    java.lang.String sLoanDate,
    java.lang.String sOrgOrderNo);


  /**
   * @param sFunctionName Mandatory java.lang.String parameter.
   * @param sParam Mandatory java.lang.String parameter.
   */

  @DISPID(49)
  java.lang.String koA_Functions(
    java.lang.String sFunctionName,
    java.lang.String sParam);


  /**
   * @param sInfoData Mandatory java.lang.String parameter.
   */

  @DISPID(50)
  int setInfoData(
    java.lang.String sInfoData);


  /**
   * @param strScreenNo Mandatory java.lang.String parameter.
   * @param strCodeList Mandatory java.lang.String parameter.
   * @param strFidList Mandatory java.lang.String parameter.
   * @param strOptType Mandatory java.lang.String parameter.
   */

  @DISPID(51)
  int setRealReg(
    java.lang.String strScreenNo,
    java.lang.String strCodeList,
    java.lang.String strFidList,
    java.lang.String strOptType);


  /**
   */

  @DISPID(52)
  int getConditionLoad();


  /**
   */

  @DISPID(53)
  java.lang.String getConditionNameList();


  /**
   * @param strScrNo Mandatory java.lang.String parameter.
   * @param strConditionName Mandatory java.lang.String parameter.
   * @param nIndex Mandatory int parameter.
   * @param nSearch Mandatory int parameter.
   */

  @DISPID(54)
  int sendCondition(
    java.lang.String strScrNo,
    java.lang.String strConditionName,
    int nIndex,
    int nSearch);


  /**
   * @param strScrNo Mandatory java.lang.String parameter.
   * @param strConditionName Mandatory java.lang.String parameter.
   * @param nIndex Mandatory int parameter.
   */

  @DISPID(55)
  void sendConditionStop(
    java.lang.String strScrNo,
    java.lang.String strConditionName,
    int nIndex);


  /**
   * @param strTrCode Mandatory java.lang.String parameter.
   * @param strRecordName Mandatory java.lang.String parameter.
   */

  @DISPID(56)
  java.lang.Object getCommDataEx(
    java.lang.String strTrCode,
    java.lang.String strRecordName);


  /**
   * @param strScrNo Mandatory java.lang.String parameter.
   * @param strDelCode Mandatory java.lang.String parameter.
   */

  @DISPID(57)
  void setRealRemove(
    java.lang.String strScrNo,
    java.lang.String strDelCode);


  /**
   * @param sTrCode Mandatory java.lang.String parameter.
   */

  @DISPID(58)
  int getMarketType(
    java.lang.String sTrCode);


  // Properties:
}
