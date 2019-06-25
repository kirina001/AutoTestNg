package com.testing.testng;

import org.testng.annotations.Test;

import com.testing.UI.DataDrivenOfWeb;
import com.testing.common.ExcelReader;
import com.testing.common.ExcelWriter;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class LoginTest {
	public DataDrivenOfWeb web;
	public ExcelReader caseExcel;
	public ExcelWriter resultExcel;

	@Test(dataProvider = "dp")
	public void f(String rowNo, String loginname, String password, String verifycode) throws Exception {
		System.out.println(rowNo + loginname + password + verifycode);
		//rowNo转换为int型，指定操作的行数
		int No = 0;
		No = Integer.parseInt(rowNo);
		web.line = No;
		//通过properties文件进行数据驱动
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/xpath.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
//		System.out.println(prop.getProperty("LoginButton"));
		web.openBrowser("chrome");
		web.visitWeb("http://112.74.191.10:8000/Home/user/login.html");
//////		直接填xpath
//		web.halt("3");
//		web.input("//input[@id='username']", loginname);
//		web.input("//input[@id='password']", password);
//		web.input("//input[@placeholder='验证码']", verifycode);
//		web.click("//a[@name='sbtbutton']");
//		web.closeBrowser();
		
//		//从properties文件中读取xpath
		web.input(prop.getProperty("username"), loginname);
		web.input(prop.getProperty("password"), password);
		web.input(prop.getProperty("verifyCode"), verifycode);
		web.click(prop.getProperty("button"));
		web.closeBrowser();
	}

	@BeforeSuite
	public void initiallize() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		String createdate = sdf.format(date);
		String rootpath = System.getProperty("user.dir");
		caseExcel = new ExcelReader(rootpath + "\\cases\\LoginTest.xlsx");
		resultExcel = new ExcelWriter(rootpath + "\\cases\\LoginTest.xlsx",
				rootpath + "\\cases\\result-" + createdate + "Login.xlsx");
		web = new DataDrivenOfWeb(resultExcel);
	}

	@AfterSuite
	public void afterMethod() {
		caseExcel.close();
		resultExcel.save();
	}

	@DataProvider
	public Object[][] dp() {
		return caseExcel.readAsMatrix();
	}
}
