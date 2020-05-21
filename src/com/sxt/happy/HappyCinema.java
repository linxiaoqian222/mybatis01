package com.sxt.happy;

import java.util.ArrayList;
import java.util.List;

/*
 * 快乐影院，在线选位置
 */
public class HappyCinema {
	
	public static void main(String[] args) {
		List<Integer> avaliable = new ArrayList<>();
		for(int i=1;i<10;i++){
			avaliable.add(i);
		}
		List<Integer> seats =  new ArrayList<>();
		seats.add(1);
		seats.add(2);
		
		List<Integer> seats2 =  new ArrayList<>();
		seats2.add(4);
		seats2.add(8);
		seats2.add(9);
		Cinema c = new Cinema(avaliable, "happy stx");
		new Thread(new Customer(c, seats),"老高").start();
		new Thread(new Customer(c, seats2),"老裴").start();
	}
}
//顾客
class Customer implements Runnable{
	Cinema cinema;
	List<Integer> seats;
	
	public Customer(Cinema cinema, List<Integer> seats) {
		super();
		this.cinema = cinema;
		this.seats = seats;
	}

	@Override
	public void run() {
		synchronized (cinema) {
			boolean flag = cinema.bookTickets(seats);
			if(flag){
				System.out.println("出票成功"+Thread.currentThread().getName()+"位置为"+seats);
			}else{
				System.out.println("出票失败"+Thread.currentThread().getName()+"位置为"+seats);
			}
		}
	}
	
}
//影院
class Cinema{
	List<Integer> avaliable; //可用位置
	String name;
	public Cinema(List<Integer> avaliable,String name) {
		this.avaliable = avaliable;
		this.name = name;
	}
	
	//购票
	public boolean bookTickets(List<Integer> seats){
		System.out.println("可用位置为:"+avaliable);
		List<Integer> copy = new ArrayList<>();
		copy.addAll(avaliable);
		
		//相减,差集
		copy.removeAll(seats);
		//判读大小
		if(avaliable.size() -copy.size() != seats.size()){
			return false;
		}
		avaliable = copy;
		return true;
	}
}
