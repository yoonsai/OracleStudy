package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// 에러 => output => this.conn NULL
	public MusicDAO()
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
//	public void foodInsert(FoodVO vo)
//	{
//		try {
//			getConnection();
//			String sql="INSERT INTO music VALUES("
//					+"?,?,?,0,?,?,?,?,?,?,?,0,0)";
//			ps=conn.prepareStatement(sql);
//			
//			
//			
//			
//			
//			ps.executeUpdate();
//
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			disConnection();
//		}
//		
//	}
}
