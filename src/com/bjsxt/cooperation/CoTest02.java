package com.bjsxt.cooperation;

/*
 * Э��ģ�ͣ�������������--�źŵ�
 * ������־λ
 * Thread����:
 * wait() ���µ�ǰ�̵߳ȴ���ֱ����һ���̵߳��ö����notify()������notifyAll()����
 * notify() �������ڵȴ�����������ĵ����߳�
 * notifyAll() �������ڵȴ�����������������߳�
 *
 */
public class CoTest02 {
	
	public static void main(String[] args) {
		Tv tv = new Tv();
		new Player(tv).start();
		new Watcher(tv).start();
	}
}
//������ ��Ա
class Player extends Thread{
	Tv tv;
	
	public Player(Tv tv) {
		this.tv = tv;
	}

	@Override
	public void run() {
		for(int i=0;i<20;i++){
			if(i%2==0){
				this.tv.play("����");
			}else{
				this.tv.play("���");
			}
		}
	}
}
//������ ����
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
//ͬһ����Դ ����
class Tv{
	String voice;
	
	//�źŵ�
	// T��ʾ�źŵƱ���  ���ڵȴ�
	// F��ʾ���ڹۿ� ��Ա�ȴ�
	boolean flag = true;
	
	//����
	public synchronized void play(String voice){
		//��Ա�ȴ�
		if(!flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//����
		System.out.println("������"+voice);
		this.voice = voice;
		//����
		this.notifyAll();
		//�л���־
		this.flag = !this.flag;
	}
	
	//�ۿ�
	public synchronized void watch(){
		//���ڵȴ�
		if(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("������"+voice);
		//����
		this.notifyAll();
		//�л���־
		this.flag = !this.flag;
	}
}
