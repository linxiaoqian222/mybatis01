package com.sxt.happy;

import java.util.ArrayList;
import java.util.List;

public class Happy12306 {
	
	public static void main(String[] args) {
		Web12306 c = new Web12306(10,"happy sxt");
		new Paggenger(c,"�ϸ�",2).start();
		new Paggenger(c,"����",5).start();
	}
}
class Paggenger extends Thread{
	int seats;
	
	public Paggenger(Runnable target,String name,int seats) {
		super(target,name);
		this.seats = seats;
		
	}

}
//��Ʊ��
class Web12306 implements Runnable{
	int avaliable; //����λ��
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
			System.out.println("��Ʊ�ɹ�"+Thread.currentThread().getName()+"λ��Ϊ"+p.seats);
		}else{
			System.out.println("��Ʊʧ��"+Thread.currentThread().getName()+"λ��Ϊ"+p.seats);
		}
	}
	
	//��Ʊ
	public synchronized boolean bookTickets(int seats){
		System.out.println("����λ��Ϊ:"+avaliable);
		if(avaliable - seats <0){
			return false;
		}
		avaliable = avaliable-seats;
		return true;
	}
}