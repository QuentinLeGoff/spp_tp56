package exo1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SieveImpl {
	
	private int n;
	private boolean[] tab;
	
	private CyclicBarrier barrier;
	private ErathoThread[] threadTab;
	
	class ErathoThread extends Thread{
		
		public int min;
		public int max;
		
		public ErathoThread(int minimum, int maximum){
			min = minimum;
			max = maximum;
		}
		
		@Override
		public void run(){
			try {
				System.out.println("bloqué run");
				barrier.await();
				System.out.println("plus bloqué run !!!!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void calcul(int i){
			try {
				System.out.println("bloqué calcul");
				barrier.await();
				System.out.println("plus bloqué calcul !!!!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int j = (i*i); min< max; j= j+i){
				System.out.println(this.getName() + " j ="+j);
				tab[j]=false;
			}
			

		}
		
		
		
	}
	
	public SieveImpl(int nb){
	
		this.n = nb;
		this.tab = new boolean[n];
		
		//tab[0]=tab[1]=false;
		
		for(int i = 2; i< n; i++){
			tab[i]=true;
		}
		
	}
	
	public ArrayList<Integer> calculatePrimeNumber(){
		
		for(int i = 0; i<(int)Math.sqrt(n);i++){
			
			if(tab[i]){
				
				for(int j = (i*i); j< n; j= j+i){
					tab[j]=false;
				}
			}
			
		}
		
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(int i = 2; i<tab.length;i++){
			if(tab[i]){
				res.add(i);
			}
		}

		return res;
	}
	
	public ArrayList<Integer> calculatePrimeNumber2(int k){
		
		threadTab = new ErathoThread[k];
		barrier = new CyclicBarrier(k);
		
		for(int i = 0; i<k; i++){
			threadTab[i] = new ErathoThread(i*(n/k), (i+1)*(n/k) ); // je suis un dieu :)
			System.out.println("thread "+ i +"créé : " +threadTab[i].min +" ->"+threadTab[i].max + " -> "+threadTab[i].getName());
			threadTab[i].start();
		}
		
		for(int i = 0; i<(int)Math.sqrt(n);i++){
			
			if(tab[i]){
				for(int j = 0; j<threadTab.length; j++){
					threadTab[j].calcul(i);
				}
			
			}
			
		}
		
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(int i = 2; i<tab.length;i++){
			if(tab[i]){
				res.add(i);
			}
		}

		return res;
	}
	
	public void afficherTab(){
		
		for(int i = 0; i<tab.length; i++){
			System.out.println(i + " -> " +tab[i]);
		}
		
	}

	public static <T> void afficherArray(ArrayList<T> list){
		
		for(int i = 0; i<list.size();i++){
			System.out.println(list.get(i));
		}
		
	}
	
	public static void main(String[] args ) throws FileNotFoundException{
		ArrayList<Integer> listTruePrimeNb = new ArrayList<Integer>();
		
		Scanner in = new Scanner(new FileReader("primes-to-100k.txt"));
		 while(in.hasNext()){
			 listTruePrimeNb.add(in.nextInt());
		 }
		 in.close();
		 
		//afficherArray(listTruePrimeNb);
		
		SieveImpl s = new SieveImpl(27);

		ArrayList<Integer> primeNb = s.calculatePrimeNumber2(3);
		afficherArray(primeNb);


	}

}
