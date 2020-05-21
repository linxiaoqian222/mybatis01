package com.sxt.happy;

public class DeadLock {

	public static void main(String[] args) {
		HuaZhuang h1 = new HuaZhuang(0, "小妹");
		HuaZhuang h2 = new HuaZhuang(1,"小丽");
		new Thread(h1).start();
		new Thread(h2).start();
	}
	
}
class HuaZhuang implements Runnable{
	static KongHong kongHong = new KongHong();
	static JingZi jingZi = new JingZi();
	int choose;
	String name;
	public HuaZhuang(int choose, String name) {
		this.choose = choose;
		this.name = name;
	}
	
	//死锁情况
	@Override
	public void run() {
		ShiFang();
	}
	
	//对象锁中有持有另一个对象的锁，没有锁被释放
	private void SiSuo(){
		if(choose == 0){
			synchronized (kongHong) {
				System.out.println(name+"在画口红");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (jingZi) {
					System.out.println(name+"在照镜子");
				}
			}
		}else {
			synchronized (jingZi) {
				System.out.println(name+"在照镜子");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (kongHong) {
					System.out.println(name+"在画口红");
				}
			}
			
		}
	}
	
	//不让对象锁相互持有，在获得另一个对象锁的时候，先把所持有的对象锁释放
	private void ShiFang(){
		if(choose == 0){
			synchronized (kongHong) {
				System.out.println(name+"在画口红");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			synchronized (jingZi) {
				System.out.println(name+"在照镜子");
			}
		}else {
			synchronized (jingZi) {
				System.out.println(name+"在照镜子");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			synchronized (kongHong) {
				System.out.println(name+"在画口红");
			}
			
		}
		
	}
	
}
class KongHong{
	
}
class JingZi{
	
}