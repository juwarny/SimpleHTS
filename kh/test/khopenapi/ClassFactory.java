package test.khopenapi  ;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed


  public static test.khopenapi._DKHOpenAPI createKHOpenAPI() {
    return COM4J.createInstance( test.khopenapi._DKHOpenAPI.class, "{A1574A0D-6BFA-4BD7-9020-DED88711818D}" );
  }
}
