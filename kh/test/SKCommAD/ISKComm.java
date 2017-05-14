package test.SKCommAD  ;

import com4j.*;

/**
 * ISKComm �������̽�
 */
@IID("{929135E7-09D8-43E8-A9FE-E331DE11D5A5}")
public interface ISKComm extends Com4jObject {
  // Methods:
  /**
   * <p>
   * �޼��� SaveToFileAsAdmin
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
   * �޼��� MakeDirectoryAsAdmin
   * </p>
   * @param szDirectoryPath Mandatory java.lang.String parameter.
   */

  @VTID(4)
  void makeDirectoryAsAdmin(
    @MarshalAs(NativeType.CSTR) java.lang.String szDirectoryPath);


  /**
   * <p>
   * �޼��� RemoveAsAdmin
   * </p>
   * @param strFile Mandatory java.lang.String parameter.
   */

  @VTID(5)
  void removeAsAdmin(
    @MarshalAs(NativeType.CSTR) java.lang.String strFile);


  /**
   * <p>
   * �޼��� RemoveDirAsAdmin
   * </p>
   * @param pszDir Mandatory java.lang.String parameter.
   */

  @VTID(6)
  void removeDirAsAdmin(
    @MarshalAs(NativeType.CSTR) java.lang.String pszDir);


  /**
   * <p>
   * �޼��� CopyAsAdmin
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
   * �޼��� CopyDirAsAdmin
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
