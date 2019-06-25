package com.testing.testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class paramTest {
  @Test
  @Parameters({"param1","param2"})
  public void f(String param1,String param2) {
	  System.out.println(param1+param2);
  }
}
