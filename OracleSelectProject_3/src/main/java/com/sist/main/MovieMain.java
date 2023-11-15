package com.sist.main;
import java.util.*;
import com.sist.dao.*;
public class MovieMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    System.err.println("=======메뉴=========");
	    System.err.println("1. 제목으로 검색");
	    System.err.println("2. 장르로 검색");
	    System.err.println("3. 출연으로 검색");
	    System.err.println("==================");
	    Scanner scan=new Scanner(System.in);
	    int menu=scan.nextInt();
	    String s="";
	    if(menu==1) s="title";
	    else if(menu==2) s="genre";
	    else if(menu==3) s="actor";
	    
	    System.out.println("검색어 입력:");
	    String fd=scan.next();
	    
	    //오라클에서 데이터를 찾아온다
	    MovieDAO dao=new MovieDAO();
	    int count=dao.movieFindCount(s, fd);
	    if(count==0)
	    {
	    	System.out.println("검색결과가 없습니다");
	    }
	    else
	    {
	        System.out.println("검색 결과:");    	
	        
	    }
	    
	    
	    
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
	}

}
