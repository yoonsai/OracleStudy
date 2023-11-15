package com.sist.main;
import java.util.*;
import com.sist.dao.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentDAO dao=new StudentDAO();
		List<StudentVO> list=dao.stdListData();
		for(StudentVO vo:list)
		{
			System.out.println(vo.getHakbun()+" "
					+vo.getHakbun()+" "
					+vo.getName()+" "
					+vo.getKor()+" "
					+vo.getHakbun()+" "
					+vo.getEng()+" "
					+vo.getMath()+" "
					+vo.getTotal()+" "
				    +vo.getAvg()+" "
				    +vo.getRank()+" "
				    +vo.getDbday());
			
		}
	}

}
