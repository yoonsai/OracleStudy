package com.sist.dao;
import java.util.*;
import java.sql.*;
public class MovieDAO {
	//오라클 연결
	private Connection conn;
	//sql송수신
	private PreparedStatement ps;
	//오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버 등록 => 생성자
	public MovieDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 클래스 메모리 할당(new,Class.forName)
			// 클래스명을 등록 => 패키지부터 등록 => Spring
		}catch(Exception e) {}
	}
	// 오라클 연결
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception e)
		{
			
		}
	}
	// 오라클 듣기
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	// 기능설정
	//검색 => 여러개 ArrayList, 한개 => VO 
	// VO => 영화 1개에 대한 정보
	public ArrayList<MovieVO> movieFindData(String column, String fd)
	{
		ArrayList<MovieVO> list=new ArrayList<MovieVO>();
		try {
			getConnection();
			String sql="SELECT title,genre,actor "
					+"FROM movie "
					+"WHERE "+column+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, fd);
			//결과값 받기 => ?가 있는 값을 설정하지 않는경우에는 오류남 
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				//rs.next()를 하면 한줄씩 읽어온다 
				MovieVO vo=new MovieVO();
				vo.setTitle(rs.getString(1));
				vo.setGenre(rs.getString(2));
				vo.setActor(rs.getString(3));
				
				list.add(vo);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	public int movieFindCount(String column,String fd) {
		int count=0;
		try {
			
			getConnection();
			String sql="SELECT title,genre,actor "
					+"FROM movie "
					+"WHERE "+column+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, fd);
			//결과값 받기 => ?가 있는 값을 설정하지 않는경우에는 오류남 
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();			
		}catch(Exception e)
			{
			e.printStackTrace();
			}
		finally
		{
			disConnection();
		}
		return count;
	}
}
