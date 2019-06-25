package com.testing.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestHier {
  @Test(priority=2)
  public void f3() {
	  WebDriver driver=new InternetExplorerDriver();
	  System.out.println("TestHier类中@Test方法3被执行");
  }
  
  @Test(groups="Roy",priority=1)
  public void f4() {
	  System.out.println("TestHier类中@Test方法4被执行");
  }
  
  @Test(dependsOnMethods="f3")
  public void f5() {
	  System.out.println("TestHier类中@Test方法5被执行");
  }
  
//  @BeforeTest
  public void beforeTest() {
	  System.out.println("TestHier@beforeTest方法被执行");
  }

//  @AfterTest
  public void afterTest() {
	  System.out.println("TestHier@afterTest方法被执行");
  }
  
//  @BeforeClass
  public void beforeClass() {
	  System.out.println("TestHier@beforeClass方法被执行");
  }

//  @AfterClass
  public void afterClass() {
	  System.out.println("TestHier@afterClass方法被执行");
  }
//  @BeforeGroups("Roy")
  public void beforeGroup() {
	  System.out.println("@beforeGroup方法被执行");
  }
//  @AfterGroups("Roy")
  public void afterGroup() {
	  System.out.println("@afterGroup方法被执行");
  }
  
}
