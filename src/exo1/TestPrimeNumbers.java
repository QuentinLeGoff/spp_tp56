package exo1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestPrimeNumbers {
	 
	private static ArrayList<Integer> listTruePrimeNb;
 
	@BeforeClass // lancer ces méthodes une fois avant les testes
	public static void initTrueList() throws FileNotFoundException{
		listTruePrimeNb = new ArrayList<Integer>();
		Scanner in = new Scanner(new FileReader("primes-to-100k.txt"));
		while(in.hasNext()){
			listTruePrimeNb.add(in.nextInt());
		}
		in.close();
	}
	 
	public void compare(ArrayList<Integer> list){
		
		for(int i=0; i<list.size();i++){
			assertTrue( list.get(i).equals(listTruePrimeNb.get(i)) );
		} 	
		
	}
	  
	@Test
	public void test1() {
		
		SieveImpl sieve = new SieveImpl(65);
		ArrayList<Integer> res = sieve.calculatePrimeNumber();
		
		compare(res);

	}
	
	@Test
	public void test2() {
		
		SieveImpl sieve = new SieveImpl(100000);
		ArrayList<Integer> res = sieve.calculatePrimeNumber();
		
		compare(res);

	}
	
	@Test
	public void test3() {
		
		SieveImpl sieve = new SieveImpl(2);
		ArrayList<Integer> res = sieve.calculatePrimeNumber();
		
		compare(res);

	}
	
	@Test
	public void test4() {
		
		SieveImpl sieve = new SieveImpl(1);
		ArrayList<Integer> res = sieve.calculatePrimeNumber();
		
		compare(res);

	}
}
