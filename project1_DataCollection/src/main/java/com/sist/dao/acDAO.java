package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class acDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.113:1521:XE";
	// 에러 => output => this.conn NULL
	public acDAO()
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
	
	public void musicInsert(ArrayList<actVO> voList)
	{
		try {
			getConnection();
			String sql="UPDATE activity SET rate=? "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			for (actVO vo : voList) {
                
				ps.setString(1, vo.getRate());
				ps.setInt(2, vo.getNo());
				ps.executeUpdate();
                
            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		
	}
}
