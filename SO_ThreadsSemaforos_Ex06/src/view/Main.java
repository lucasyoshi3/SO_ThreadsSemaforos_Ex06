package view;

import java.util.concurrent.Semaphore;
import controller.ThreadCozinha;

public class Main {
	public static void main(String[] args) {
		Semaphore semaforo=new Semaphore(1);
		
		for(int i=1;i<=5;i++) {
			Thread cozinha=new ThreadCozinha(i,semaforo);
			cozinha.start();
		}
	}
}
