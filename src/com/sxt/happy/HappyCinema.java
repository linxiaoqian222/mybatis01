package com.sxt.happy;

import java.util.ArrayList;
import java.util.List;

/*
 * ����ӰԺ������ѡλ��
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
		new Thread(new Customer(c, seats),"�ϸ�").start();
		new Thread(new Customer(c, seats2),"����").start();
	}
}
//�˿�
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
				System.out.println("��Ʊ�ɹ�"+Thread.currentThread().getName()+"λ��Ϊ"+seats);
			}else{
				System.out.println("��Ʊʧ��"+Thread.currentThread().getName()+"λ��Ϊ"+seats);
			}
		}
	}
	
}
//ӰԺ
class Cinema{
	List<Integer> avaliable; //����λ��
	String name;
	public Cinema(List<Integer> avaliable,String name) {
		this.avaliable = avaliable;
		this.name = name;
	}
	
	//��Ʊ
	public boolean bookTickets(List<Integer> seats){
		System.out.println("����λ��Ϊ:"+avaliable);
		List<Integer> copy = new ArrayList<>();
		copy.addAll(avaliable);
		
		//���,�
		copy.removeAll(seats);
		//�ж���С
		if(avaliable.size() -copy.size() != seats.size()){
			return false;
		}
		avaliable = copy;
		return true;
	}
}
