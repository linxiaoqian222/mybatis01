package com.sxt.happy;

public class DeadLock {

	public static void main(String[] args) {
		HuaZhuang h1 = new HuaZhuang(0, "С��");
		HuaZhuang h2 = new HuaZhuang(1,"С��");
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
	
	//�������
	@Override
	public void run() {
		ShiFang();
	}
	
	//���������г�����һ�����������û�������ͷ�
	private void SiSuo(){
		if(choose == 0){
			synchronized (kongHong) {
				System.out.println(name+"�ڻ��ں�");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (jingZi) {
					System.out.println(name+"���վ���");
				}
			}
		}else {
			synchronized (jingZi) {
				System.out.println(name+"���վ���");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (kongHong) {
					System.out.println(name+"�ڻ��ں�");
				}
			}
			
		}
	}
	
	//���ö������໥���У��ڻ����һ����������ʱ���Ȱ������еĶ������ͷ�
	private void ShiFang(){
		if(choose == 0){
			synchronized (kongHong) {
				System.out.println(name+"�ڻ��ں�");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			synchronized (jingZi) {
				System.out.println(name+"���վ���");
			}
		}else {
			synchronized (jingZi) {
				System.out.println(name+"���վ���");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			synchronized (kongHong) {
				System.out.println(name+"�ڻ��ں�");
			}
			
		}
		
	}
	
}
class KongHong{
	
}
class JingZi{
	
}