package com.sist.dao;
import java.util.*;
import java.sql.*;
public class TriggerDAO {
	//오라클 연결 => 객체
	private Connection conn; // Socket
	//sql을 전송하는 객체
	private PreparedStatement ps;
	// BufferedReader / OutputStream
	// 오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
                                              //---------ip주소로 변경
	//연결 => Driver 등록
	public TriggerDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// new 대신 사용 => 클래스명으로 메모리 할당 => 리플렉션
		}catch(Exception ex)
		{
			
		}
	}
	//오라클 연동
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//오라클 닫기
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {}
	}
	public void inputInsert(int no,int account, int price)
	{
		try {
			getConnection();
			String sqp="Insert INTO 입고 VALUES(?,?,?)";
			ps=conn.prepareStatement(sqp);
			ps.setInt(1, no);
			ps.setInt(2, account);
			ps.setInt(3, price);
			ps.executeUpdate();
		}catch(Exception e)
		{
			e.getStackTrace();
		}finally
		{
			disConnection();
		}
	}
	public void outputInsert(int no,int account,int price)
	   {
	      try
	      {
	         getConnection();
	         String sql="INSERT INTO 출고 VALUES(?,?,?)";
	         ps=conn.prepareStatement(sql);
	         ps.setInt(1, no);
	         ps.setInt(2, account);
	         ps.setInt(3, price);
	         ps.executeUpdate();
	      }catch(Exception ex)
	      {
	         ex.printStackTrace();
	      }
	      finally
	      {
	         disConnection();
	      }
	   }

	public void remainData()
	{
		try {
			getConnection();
			String sql="SELECT * FROM 재고";
			ps=conn.prepareStatement(sql);
			//결과값 받기
			ResultSet rs=ps.executeQuery();
			System.out.println("품번 수량 누적금액");
			while(rs.next())
			{
				System.err.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getInt(3));
			}
		}catch(Exception e)
		{
			e.getStackTrace();
		}finally
		{
			disConnection();
		}
	}
	public static void main(String[] args) {
		TriggerDAO dao=new TriggerDAO();
		dao.outputInsert(100, 5, 3000);
		dao.remainData();
	}
}