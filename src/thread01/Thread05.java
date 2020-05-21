package thread01;

public class Thread05 implements Runnable{
	private  Integer ticketNums = 10;
	private  boolean flag = true;
	@Override
	public  void run() {
		while(flag){
			test();
		}
	}
	public  void test(){
	  synchronized (ticketNums) {
			if(ticketNums <=0 ){
				flag = false;
				return;
			}
			//Ä£ÄâÑÓÊ±
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ticketNums--;
			System.out.println(Thread.currentThread().getName()+"-->"+ticketNums);
		}
	}
	public static void main(String[] args) {
		Thread05 t1 = new Thread05();
		new Thread(t1,"t1").start();
		new Thread(t1,"t2").start();
		new Thread(t1,"t3").start();
	}
}
