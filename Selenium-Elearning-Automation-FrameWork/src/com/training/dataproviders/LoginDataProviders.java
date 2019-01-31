package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.GrantLoanBean;
import com.training.dao.GrantLoanDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<GrantLoanBean> list = new GrantLoanDAO().getloandata(); 

		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(GrantLoanBean temp : list){
			Object[]  obj = new Object[3]; 
			obj[0] = temp.getMemberlogin(); 
			obj[1] = temp.getAmount(); 
			obj[2] = temp.getLoan_decsription(); 
			result[count ++] = obj; 
		}


		return result;
	}

	@DataProvider(name = "excel-inputs_TC76")
	public Object[][] getExcelData(){
		String fileName ="C:\\Users\\SOUMITARAY\\ProjectCyclos\\Login_GrantLoan.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName,0); 
	}
	
	@DataProvider(name = "excel-inputs_TC78")
	public Object[][] getExcelDataRepay(){
		String fileName ="C:\\Users\\SOUMITARAY\\ProjectCyclos\\Login_GrantLoan.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName,1); 
	}
	
	@DataProvider(name = "excel-inputs_TC80")
	public Object[][] getExcelDataErrScenario(){
		String fileName ="C:\\Users\\SOUMITARAY\\ProjectCyclos\\Login_MemberPayError.xlsx"; 
		int sheetnum = 0;
		return new ApachePOIExcelRead().getExcelContent(fileName,0); 
	}
	

	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
	}
}