package exo1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SieveImpl {
	
	private int n;
	
	private boolean[] tab;
	
	public SieveImpl(int nb){
	
		this.n = nb;
		this.tab = new boolean[n];
		
		//tab[0]=tab[1]=false;
		
		for(int i = 2; i< n; i++){
			tab[i]=true;
		}
		
	}
	
	public ArrayList<Integer> calculatePrimeNumber(){
		
		for(int i = 2; i<(int)Math.sqrt(n);i++){
			
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
		
		SieveImpl s = new SieveImpl(100000);

		ArrayList<Integer> primeNb = s.calculatePrimeNumber();
		afficherArray(primeNb);


	}

}
