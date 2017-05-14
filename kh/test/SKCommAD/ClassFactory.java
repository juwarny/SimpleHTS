package test.SKCommAD  ;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed


  /**
   * SKComm Class
   */
  public static test.SKCommAD.ISKComm createSKComm() {
    return COM4J.createInstance( test.SKCommAD.ISKComm.class, "{4EA4DD2D-8805-45E8-A2C5-6916FD03F665}" );
  }
}
