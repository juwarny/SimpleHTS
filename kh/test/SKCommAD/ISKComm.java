package test.SKCommAD  ;

import com4j.*;

/**
 * ISKComm 인터페이스
 */
@IID("{929135E7-09D8-43E8-A9FE-E331DE11D5A5}")
public interface ISKComm extends Com4jObject {
  // Methods:
  /**
   * <p>
   * 메서드 SaveToFileAsAdmin
   * </p>
   * @param pszPath Mandatory java.lang.String parameter.
   * @param pData Mandatory java.lang.String parameter.
   * @param nLen Mandatory int parameter.
   */

  @VTID(3)
  void saveToFileAsAdmin(
    @MarshalAs(NativeType.CSTR) java.lang.String pszPath,
    java.lang.String pData,
    int nLen);


  /**
   * <p>
   * 메서드 MakeDirectoryAsAdmin
   * </p>
   * @param szDirectoryPath Mandatory java.lang.String parameter.
   */

  @VTID(4)
  void makeDirectoryAsAdmin(
    @MarshalAs(NativeType.CSTR) java.lang.String szDirectoryPath);


  /**
   * <p>
   * 메서드 RemoveAsAdmin
   * </p>
   * @param strFile Mandatory java.lang.String parameter.
   */

  @VTID(5)
  void removeAsAdmin(
    @MarshalAs(NativeType.CSTR) java.lang.String strFile);


  /**
   * <p>
   * 메서드 RemoveDirAsAdmin
   * </p>
   * @param pszDir Mandatory java.lang.String parameter.
   */

  @VTID(6)
  void removeDirAsAdmin(
    @MarshalAs(NativeType.CSTR) java.lang.String pszDir);


  /**
   * <p>
   * 메서드 CopyAsAdmin
   * </p>
   * @param strFrom Mandatory java.lang.String parameter.
   * @param strTo Mandatory java.lang.String parameter.
   */

  @VTID(7)
  void copyAsAdmin(
    @MarshalAs(NativeType.CSTR) java.lang.String strFrom,
    @MarshalAs(NativeType.CSTR) java.lang.String strTo);


  /**
   * <p>
   * 메서드 CopyDirAsAdmin
   * </p>
   * @param pszFromDir Mandatory java.lang.String parameter.
   * @param pszToDir Mandatory java.lang.String parameter.
   */

  @VTID(8)
  void copyDirAsAdmin(
    @MarshalAs(NativeType.CSTR) java.lang.String pszFromDir,
    @MarshalAs(NativeType.CSTR) java.lang.String pszToDir);


  // Properties:
}
