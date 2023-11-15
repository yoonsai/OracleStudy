package com.sist.dao;
/*
 *   VO => 오라클 데이터를 받아서 저장할 목적
 *      => 브라우저, 윈도우로 전송 목적
 *      => DTO (Data Transfor object)
 *  
 *                요청                SQL
 *   브라우저(JSP) ====== 자바응용프로그램 ======== 오라클
 *                                 ========
 *                                   결과값
 *                VO      VO                   
 *   JSP에서는 핵심 클래스 => Bean
 *   => 가급적이면 => 오라클 컬럼과 매칭        
 *   문자 : CHAR,VARCHAR2,CLOB => String
 *   숫자 : NUMBER => int,double
 *   날짜 : DATE => java.util.
 *   ------------------------------- My Batis                        
 */
public class SalGradeVO {
	private int grade,losal,hisal;

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getLosal() {
		return losal;
	}

	public void setLosal(int losal) {
		this.losal = losal;
	}

	public int getHisal() {
		return hisal;
	}

	public void setHisal(int hisal) {
		this.hisal = hisal;
	}
	
}
