package com.sist.main;
import java.util.Scanner;

import com.sist.dao.*;
public class EmpMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpDAO dao=new EmpDAO();
		//dao.empListData();
		
		//dao.empNotCommListData();
		
		//dao.empCommListData();
		
		//Scanner scan=new Scanner(System.in);
		//System.out.print("검색어 입력:");
		//String ename=scan.next();
		//dao.empEnameListData(ename.toUpperCase());
		
		//dao.empRpadData();
		
		//dao.empsalInfoData();
		
		//dao.empGroupbyData();
		
		dao.subqueryNotData();
		
	}

}
