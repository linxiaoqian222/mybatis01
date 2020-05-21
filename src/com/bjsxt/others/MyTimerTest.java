package com.bjsxt.others;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/*
 * 任务调度：Timer和TimerTask
 */
public class MyTimerTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		//执行安排
		//5s后，执行任务依次 timer.schedule(new MyTask(), 5000);
		//1000之后在启动，之后每隔200毫秒执行一次。timer.schedule(new MyTask(),1000,200);
		Calendar cal = new GregorianCalendar(2020,5,10,15,30);
		timer.schedule(new MyTask(), cal.getTime(),200); //5秒后开始，每隔200毫秒执行一次
	}
}
/*
 * 任务类
 */
class MyTask extends TimerTask{

	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(i);
		}
		System.out.println("任务结束");
	}
	
}