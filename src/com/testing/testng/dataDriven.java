package com.testing.testng;

import org.testng.annotations.Test;

import com.testing.UI.DataDrivenOfWeb;
import com.testing.common.ExcelReader;
import com.testing.common.ExcelWriter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.annotations.AfterSuite;

public class dataDriven {
	public DataDrivenOfWeb web;
	public ExcelReader caseExcel;
	public ExcelWriter resultExcel;
	
  @Test(dataProvider = "keywords")
  public void f(String rowNo, String kong,String casename,String keywords,String param1,String param2,String param3,String k1,String k2,String k3,String k4 ) {
	  int No=0;
	  No=Integer.parseInt(rowNo);
	  web.line=No;
	  System.out.println(rowNo+casename);
//	  runUIWithInvoke(keywords, param1, param2, param3);
  }

  @DataProvider
  public Object[][] keywords() {
	  return caseExcel.readAsMatrix();
  }
  @BeforeSuite
  public void beforeSuite() {
      Date date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
      String createdate = sdf.format(date);
      String rootpath = System.getProperty("user.dir");
	  caseExcel = new ExcelReader(rootpath+"\\cases\\WebCases2.xlsx");
	  resultExcel = new ExcelWriter(rootpath+"\\cases\\WebCases2.xlsx", rootpath+"\\cases\\result-"+createdate+"WebCases2.xlsx");
	  web =new DataDrivenOfWeb(resultExcel);
  }

  @AfterSuite
  public void afterSuite() {
	  caseExcel.close();
	  resultExcel.save();
  }

  private void runUIWithInvoke(String key,String param1,String param2,String param3) {
		try {
			Method appMethod = web.getClass().getDeclaredMethod(key);
			// invoke语法，需要输入类名以及相应的方法用到的参数
			appMethod.invoke(web);
			return;
		} catch (Exception e) {
		}
		try {
			Method uis = web.getClass().getDeclaredMethod(key, String.class);
			// invoke语法，需要输入类名以及相应的方法用到的参数
			uis.invoke(web, param1);
			return;
		} catch (Exception e) {
		}
		try {
			Method uis = web.getClass().getDeclaredMethod(key, String.class, String.class);
			// invoke语法，需要输入类名以及相应的方法用到的参数
			uis.invoke(web, param1, param2);
			return;
		} catch (Exception e) {
		}
		try {
			Method uis = web.getClass().getDeclaredMethod(key, String.class, String.class,
					String.class);
			// invoke语法，需要输入类名以及相应的方法用到的参数
			uis.invoke(web, param1, param2,param3);
			return;
		} catch (Exception e) {
		}
	}
  
}
