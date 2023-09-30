package controller;

import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread{
	Semaphore semaforo;
	
	private int numPrato;
	private int tempo;
	private int percentual;
	
	public ThreadCozinha(int id, Semaphore semaforo) {
		numPrato=id;
		this.semaforo=semaforo;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int resto=numPrato%2;
		boolean impar=impar(resto);
		if(impar) {
			sopa();
		}else {
			lasanha();
		}
	}

	private void sopa() {
		// TODO Auto-generated method stub
		System.out.println("Sopa de cebola comecou a cozinhar");
		tempo=definirTempo(350, 450);
		for(int i=1;i<=tempo/100;i++) {
			System.out.println("Sopa de cebola "+numPrato+" "+percentual(i*100)+"% pronto");
			dormir(100);
		}
		System.out.println("A sopa de cebola "+numPrato+" esta pronta");
		entrega();
	}

	private void lasanha() {
		// TODO Auto-generated method stub
		definirTempo(699,520);
		for(int i=1;i<=tempo/100;i++) {
			System.out.println("Lasanha a bolonhesa "+numPrato+" "+percentual(i*100)+"% pronto");
			dormir(100);
		}
		System.out.println("A lasanha a bolenhesa "+numPrato+" esta pronto");
		entrega();
	}
	
	private void dormir(int i) {
		// TODO Auto-generated method stub
		try {
			sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int percentual(int num) {
		// TODO Auto-generated method stub
		return num*100/tempo;
	}

	public boolean impar(int resto) {
		// TODO Auto-generated method stub
		if (resto==1) {
			return true;
		}
		return false;
	}
	
	private int definirTempo(int mult, int soma) {
		// TODO Auto-generated method stub
		return (int)(Math.random()*mult)+soma;
	}
	
	private void entrega() {
		// TODO Auto-generated method stub
		try {
			semaforo.acquire();
			dormir(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			semaforo.release();
			if(numPrato%2==1) {
				System.out.println("A sopa de cebola "+numPrato+" foi entregue");
			}else {
				System.out.println("A lasanha a bolonhesa "+numPrato+" foi entregue");
			}
		}
	}
}
