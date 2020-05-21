package com.bjsxt.cooperation;

/*
 * 协作模型：生产者消费者--信号灯
 * 借助标志位
 * Thread方法:
 * wait() 导致当前线程等待，直到另一个线程调用对象的notify()方法或notifyAll()方法
 * notify() 唤醒正在等待对象监视器的单个线程
 * notifyAll() 唤醒正在等待对象监视器的所有线程
 *
 */
public class CoTest02 {
	
	public static void main(String[] args) {
		Tv tv = new Tv();
		new Player(tv).start();
		new Watcher(tv).start();
	}
}
//生产者 演员
class Player extends Thread{
	Tv tv;
	
	public Player(Tv tv) {
		this.tv = tv;
	}

	@Override
	public void run() {
		for(int i=0;i<20;i++){
			if(i%2==0){
				this.tv.play("奇葩");
			}else{
				this.tv.play("广告");
			}
		}
	}
}
//消费者 观众
class Watcher extends Thread{
	Tv tv;
	
	public Watcher(Tv tv) {
		this.tv = tv;
	}
	
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			this.tv.watch();
		}
	}
	
}
//同一个资源 电视
class Tv{
	String voice;
	
	//信号灯
	// T表示信号灯表演  观众等待
	// F表示观众观看 演员等待
	boolean flag = true;
	
	//表演
	public synchronized void play(String voice){
		//演员等待
		if(!flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//表演
		System.out.println("表演了"+voice);
		this.voice = voice;
		//唤醒
		this.notifyAll();
		//切换标志
		this.flag = !this.flag;
	}
	
	//观看
	public synchronized void watch(){
		//观众等待
		if(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("听到了"+voice);
		//唤醒
		this.notifyAll();
		//切换标志
		this.flag = !this.flag;
	}
}
