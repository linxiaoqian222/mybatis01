package com.bjsxt.others;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/*
 * ������ȣ�Timer��TimerTask
 */
public class MyTimerTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		//ִ�а���
		//5s��ִ���������� timer.schedule(new MyTask(), 5000);
		//1000֮����������֮��ÿ��200����ִ��һ�Ρ�timer.schedule(new MyTask(),1000,200);
		Calendar cal = new GregorianCalendar(2020,5,10,15,30);
		timer.schedule(new MyTask(), cal.getTime(),200); //5���ʼ��ÿ��200����ִ��һ��
	}
}
/*
 * ������
 */
class MyTask extends TimerTask{

	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(i);
		}
		System.out.println("�������");
	}
	
}