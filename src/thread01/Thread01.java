package thread01;

public class Thread01 extends Thread{
	
	//run方法是线程入口
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			System.out.println("pp");
		}
		
	}
}
//模拟多个人对同一份票进行抢购
class Thread02 implements Runnable{
	
	private int ticketNums = 99;
	
	@Override
	public void run() {
		while(true){
			if(ticketNums<0){
				break;
			}
			System.out.println(ticketNums);
		}
		
	}
}