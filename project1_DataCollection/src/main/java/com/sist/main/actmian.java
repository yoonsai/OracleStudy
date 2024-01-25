package com.sist.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.sist.dao.acDAO;
import com.sist.dao.actVO;

public class actmian {
	private static ArrayList<actVO> flist=new ArrayList<actVO>();
	static acDAO dao=new acDAO();
	static
	{
		FileReader fr=null;
		BufferedReader br=null;
		ObjectInputStream ois=null;
		FileInputStream fis=null;
		StringBuffer sb=new StringBuffer();
		try {

			
			fr=new FileReader("c:\\java_data\\price.txt");
			String data="";
			int i=0;
			while((i=fr.read())!=-1)
			{
				data+=(char)i;
				
			}
			sb.append(data);
			fr.close();
			

//			fis.close();
//			br.close();
			
			String food_data=sb.toString();
			String[] fd=food_data.split("\n");
			
			i=1;
			for(String s:fd)
			{
				//StringTokenizer st=new StringTokenizer(s,"|");
				actVO vo=new actVO();
				vo.setRate(s.substring(0,s.indexOf("\r")));
				vo.setNo(i++);
				flist.add(vo);
				
				
				
				//s=s.substring(0,s.indexOf("?"));
				//vo.setTag(st.nextToken());
				//vo.setFno(Integer.parseInt(st.nextToken()));
				//vo.setTitle(st.nextToken());
				//System.out.println(detail.length);
			}
			dao.musicInsert(flist);
			
//			int index = 0;
//			for (String s : Ilist) {
//			    String p = s.substring(0, s.indexOf("\r"));
//			    flist.get(index).setPoster(p);
//			    index++;
//			}
			
			
			
		}catch(Exception e)
        {
			e.printStackTrace();
        }finally {
        	try {
        		fr.close();
        	}catch(Exception e)
        	{
        		
        	}
        }
		
	}
	public static void main(String[] args) {
		actmian fm=new actmian();
		//System.out.println("저장완료");
		for(actVO vo:flist) 
		{
			System.out.println("태그:"+vo.getNo());
			System.out.println("태그:"+vo.getRate());
			System.out.println("===========================");
		}
		
	}
}
