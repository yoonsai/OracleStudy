package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;
public class ShopDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.113:1521:XE";
	// 에러 => output => this.conn NULL
	public ShopDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {}
	}
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception e)
		{
			
		}
	}
	public void disConnection()
	{
        try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e)
		{
			
		}
	}
	
	public void musicInsert(ArrayList<ShopVO> voList)
	{
		try {
			getConnection();
			String sql="INSERT INTO goods VALUES("
					+"goods_gno_seq.nextval,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			for (ShopVO vo : voList) {
                ps.setString(1, vo.getGname());
                ps.setString(2, vo.getPoster());
                ps.setString(3, vo.getOrigin());
                ps.setString(4, vo.getManufacturer());
                ps.setString(5, vo.getPrice());
                ps.setString(6, vo.getDimage());
                ps.executeUpdate();
            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		
	}
}