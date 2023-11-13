package com.sist.dao;
/*
 *  연동 => 마지막 (은퇴) => 웹 프로그래머의 핵심
 *  => 90%
 *  브라우저 ==== 자바 =========== 오라클
 *         요청        SQL전송     | SQL문장 실행
 *         전송 ===== 결과값 받기
 *    | 결과값을 받아서 화면 출력 (HTML)
 *  ** 오라클 SQL문장과 자바에서 전송하는 SQL문장이 다른것이 있다
 *                       95%는 동일 => 5%가 다르다
 *                       => LIKE가 약간 틀리다
 *                       => 오라클 / MYSQL
 *  1) 연결(송수신) => 드라이버 설정
 *                 ------- 클래스로 만들어져 있다
 *     Class.forName("oracle.jdbc.driver.OracleDriver")
 *                    ------------------------------
 *                        ojdbc8.jar
 *  2) 오라클 연결
 *     Connection conn=DriverManager.getConnection(URL,username,password)
 *                                                        hr      happy
 *     URL : jdbc:업체명:드라이버타입:@IP:PORT:데이터베이스명
 *           jdbc:oracle:thin:@localhost:1521:XE
 *   
 *  3) SQL 문장을 전송
 *     PreparedStatement ps=conn.prepareStatement(sql문장);
 *     
 *  4) 오라클에서 실행된 데이터를 받는다
 *     ResultSet rs=ps.executeQuery()
 *     =>        -- 실행된 결과를 메모리에 저장
 *     SELECT ename,job
 *     ResultSet
 *     
 *         ENAME                JOB
    -------------------- ------------------
        KING                 PRESIDENT | 커서이동 => 맨위로 커서이동 메소드 next()
        BLAKE                MANAGER
        CLARK                MANAGER
        JONES                MANAGER
        MARTIN               SALESMAN
        ALLEN                SALESMAN
        TURNER               SALESMAN
        JAMES                CLERK
        WARD                 SALESMAN
        FORD                 ANALYST
        SMITH                CLERK  | 커서이동 => 맨아래로 커서이동 메소드 previous()
         | 커서위치
         
        ==> Order by를 이용해서 데이터를 읽어온다 => next()
        while(rs.next())
        {
           => VO에 값을 채운다
        }
        => 읽을 데이터가 없는 상태
        rs.close()
        ps.close()
        conn.close ==> 종료
        ---------- 코딩하는 패턴이 1개
        ---------- sql문장을 정상수행하게 제작하는 것이 핵심
                   ------ 오라클
        ---------- DML,DQL => CRUD
        
    ==> 반복 구간 => 연결 / 닫기 => 메소드 
    
    SELECT : 데이터 읽기 (검색)
     => 형식 
        SELECT * | Column1,Column2..
        FROM table_name
        [
           WHERE 조건문 (연산자)
           GROUP BY 컬럼|함수 => 그룹
           HAVING 그룹에 대한 조건 ====>
                  반드시 GROUP BY가 있는 경우에만 사용
           ORDER BY 컬럼(정렬대상), 번호 => ASC|DESC
                                       --생략가능
            
        ]
 */
import java.util.*; //Date
import java.sql.*; // Connection, PreparedStatement,
public class EmpDAO {
	// 네트워크  => 자바응용프로그램(클라이언트) <====> 오라클(서버)
	// 요청(SQL) <==> 응답 (실제 출력 결과값을 받는다) 
   // 연결 객체 선언 => Connection
	private Connection conn; 
	// SQL문장 송수신
	private PreparedStatement ps; //read/write
	// 오라클 연결 => 오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버 등록 => 한번만 => 보통 (생성자)
	public EmpDAO()
	{
		try {
			//대소문자 구분
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//메모리 할당 => 클래스명으로 메모리 할당이 가능 => 리플렉션
		}catch(Exception e)
		{
			
		}
	}
	//연결
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
			
		}catch(Exception e)
		{
			
		}
	}
	//해제
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e)
		{
			
		}
	}
	//기능 수행 => 메소드 => 테이블 1개당 => VO,DAO
	// sql문장 전송
	// emp에서 데이터를 => 사번, 이름, 입사일, 직위, 급여
	public void empListData()
	{
		try {
			//1. 오라클 연결
			getConnection();
			//2. sql문장 제작
			String sql="SELECT empno,ename,job,hiredate,sal "
					+ "FROM emp";
			//3. sql문장을 오라클로 전송
			ps=conn.prepareStatement(sql);
			//4. 결과값을 받는다
			ResultSet rs=ps.executeQuery();
			//5. 결과값을 출력
			/*
			 *     no   name   sex    regdate
			 *     --------------------------
			 *     1    홍길동   남자     23/11/13
			 *     -    ---    ---    --------
			 *                            | rs.getDate(4)
			 *                  | rs.getString(3)
			 *           | rs.getString(2)
			 *     | rs.gerInt(1)
			 */
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+" "
						+rs.getString(2)+" "
						+rs.getString(3)+" "
						+rs.getDate(4)+" "
						+rs.getInt(5)+" ");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			//닫기
			disConnection();
		}
	}
	// 사원의 이름, 직위, 급여, 입사일, 성과급 => 성과급이 없는 사원의 목록을 출력
	public void empNotCommListData()
	{
		try {
			getConnection();
			String sql="SELECT ename,job,hiredate,sal,comm "
					+ "FROM emp "
					+ "WHERE comm IS NULL OR comm=0";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "
						+rs.getString(2)+" "
						+rs.getDate(3)+" "
						+rs.getInt(4)+" "
						+rs.getInt(5)+" ");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
	
	public void empCommListData()
	{
		try {
			getConnection();
			String sql="SELECT ename,job,sal,hiredate,comm "
					+ "FROM emp "
					+ "WHERE comm IS NOT NULL AND comm<>0";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "
						+rs.getString(2)+" "
						+rs.getInt(3)+" "
						+rs.getDate(4)+" "
						+rs.getInt(5)+" ");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
	//사용자로부터 입력값을 받음
	public void empEnameListData(String ename)
	{
		try {
			getConnection();
			String sql="SELECT ename,job,hiredate,sal "
					+ "FROM emp\r\n"
					+ "WHERE ename LIKE '%'||?||'%' "
					+ "ORDER BY ename ASC";
			ps=conn.prepareStatement(sql);
			// ?에 값을 채운 후에 실행 요청
			ps.setString(1, ename); // 1번째 ?에
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "
						+rs.getString(2)+" "
						+rs.getDate(3)+" "
						+rs.getInt(4)+" ");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
	// -- RPAD
	public void empRpadData()
	{
		try {
			getConnection();
			String sql="SELECT ename,RPAD(SUBSTR(ename,1,2),LENGTH(ename),'*') "
					+ "FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "
						+rs.getString(2));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
	public void empsalInfoData()
	{
		try {
			getConnection();
			String sql="SELECT ename,ROUND(MONTHS_BETWEEN(SYSDATE,hiredate)),"
					+ "TO_CHAR(sal,'$999,999'),TO_CHAR(sal*12,'$999,999'),"
					+ "TO_CHAR(sal+NVL(comm,0),'$999,999'),"
					+ "TO_CHAR(HIREDATE,'YYYY-MM-DD HH24:MI:SS') "
					+ "FROM emp";
			//, 다음에는 공백을 줄 필요 없음
		    ps=conn.prepareStatement(sql);
		    ResultSet rs=ps.executeQuery();
		    while(rs.next())
		    {
		    	System.out.println(rs.getString(1)+" "
		    			+rs.getInt(2)+" "
		    			+rs.getString(3)+" "
		    			+rs.getString(4)+" "
		    			+rs.getString(5)+" "
		    			+rs.getString(6)+" ");
		    	
		    }
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	public void empGroupbyData()
	{
		try {
			getConnection();
			String sql="SELECT TO_CHAR(hiredate,'YYYY'),COUNT(*),SUM(sal),AVG(sal),MAX(sal),MIN(sal) "
					+ "FROM EMP "
					+ "GROUP BY TO_CHAR(hiredate,'YYYY') "
					+ "ORDER BY TO_CHAR(hiredate,'YYYY')";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "
						+rs.getInt(2)+" "
						+rs.getInt(3)+" "
						+rs.getDouble(4)+" "
						+rs.getInt(5)+" "
						+rs.getInt(6)+" ");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
	//서브쿼리를 사용하지 않은 경우
	public void subqueryNotData()
	{
		try {
			getConnection();
			String sql="SELECT ROUND(AVG(sal)) "
					+"FROM emp";
			ps=conn.prepareStatement(sql);
			//sql문장은 1개 전송 가능
			ResultSet rs=ps.executeQuery();
			rs.next(); //커서 위치 변경
			int avg=rs.getInt(1);
			rs.close();
			
			sql="SELECT ename,job,hiredate,sal "
					+"FROM emp "
					+"WHERE sal<?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, avg);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "
						+rs.getString(2)+" "
						+rs.getDate(3)+" "
						+rs.getInt(4));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
	//서브쿼리를 사용한 경우
}

