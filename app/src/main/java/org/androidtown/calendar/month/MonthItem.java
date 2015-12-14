package org.androidtown.calendar.month;

/**
 * 일자 정보를 담기 위한 클래스 정의
 * 
 * @author Mike
 *
 */
public class MonthItem {

	private int dayValue;
	private int monthValue;

	public MonthItem() {
	}
	
	public MonthItem(int day) {
		dayValue = day;
	}
	
	public int getDay() {
		return dayValue;
	}
	public void setDay(int day) {
		this.dayValue = day;
	}
	
//	public int getMonth() {
//		return monthValue;
//	}
	
}
