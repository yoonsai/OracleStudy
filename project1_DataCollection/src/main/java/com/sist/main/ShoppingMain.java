package com.sist.main;

import java.util.*;
import com.sist.dao.*;
import java.io.*;
public class ShoppingMain {
	private static ArrayList<ShopVO> flist=new ArrayList<ShopVO>();
	static ShopDAO dao=new ShopDAO();
	static
	{
		FileReader fr=null;
		BufferedReader br=null;
		ObjectInputStream ois=null;
		FileInputStream fis=null;
		StringBuffer sb=new StringBuffer();
		try {

			
			fr=new FileReader("c:\\java_data\\shopping.txt");
			String data="";
			int i=0;
			while((i=fr.read())!=-1)
			{
				data+=(char)i;
				
			}
			sb.append(data);
			fr.close();
			
			String food_data=sb.toString();
			String[] fd=food_data.split("\n");
			
			for(String s:fd)
			{
				//StringTokenizer st=new StringTokenizer(s,"|");
				ShopVO vo=new ShopVO();
				String[] detail=s.split("\\|");
				vo.setGname(detail[0]);
				vo.setPoster(detail[1]);
				vo.setPrice(detail[2]);
				vo.setOrigin(detail[3]);
				vo.setManufacturer(detail[4]);
				vo.setDimage(detail[5]);
				flist.add(vo);
				
				
				
				//s=s.substring(0,s.indexOf("?"));
				//vo.setTag(st.nextToken());
				//vo.setFno(Integer.parseInt(st.nextToken()));
				//vo.setTitle(st.nextToken());
				//System.out.println(detail.length);
			}
			dao.musicInsert(flist);
			
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
		ShoppingMain fm=new ShoppingMain();
		//System.out.println("저장완료");
		for(ShopVO vo:flist) 
		{
			System.out.println("이름:"+vo.getGname());
			System.out.println("대표이미지:"+vo.getPoster());
			System.out.println("원산지:"+vo.getOrigin());
			System.out.println("제조사:"+vo.getManufacturer());
			System.out.println("가격:"+vo.getPrice());
			System.out.println("상세이미지:"+vo.getDimage());
			System.out.println("===========================");
		}
		
	}
}
	
