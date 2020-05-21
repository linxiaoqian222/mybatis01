package com.sxt.happy;

import java.util.ArrayList;
import java.util.List;

public class Happy12306 {
	
	public static void main(String[] args) {
		Web12306 c = new Web12306(10,"happy sxt");
		new Paggenger(c,"老高",2).start();
		new Paggenger(c,"老网",5).start();
	}
}
class Paggenger extends Thread{
	int seats;
	
	public Paggenger(Runnable target,String name,int seats) {
		super(target,name);
		this.seats = seats;
		
	}

}
//火车票房
class Web12306 implements Runnable{
	int avaliable; //可用位置
	String name;
	public Web12306(int avaliable,String name) {
		this.avaliable = avaliable;
		this.name = name;
	}
	
	@Override
	public void run() {
		Paggenger p = (Paggenger)Thread.currentThread();
		boolean flag = this.bookTickets(p.seats);
		if(flag){
			System.out.println("出票成功"+Thread.currentThread().getName()+"位置为"+p.seats);
		}else{
			System.out.println("出票失败"+Thread.currentThread().getName()+"位置为"+p.seats);
		}
	}
	
	//购票
	public synchronized boolean bookTickets(int seats){
		System.out.println("可用位置为:"+avaliable);
		if(avaliable - seats <0){
			return false;
		}
		avaliable = avaliable-seats;
		return true;
	}
}