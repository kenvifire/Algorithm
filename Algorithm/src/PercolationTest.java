

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import junit.framework.TestCase;

import org.junit.Test;

public class PercolationTest extends TestCase{
	@Test
	public void testOpen(){
		Percolation per = new Percolation(5);
		//per.dumpData();
		
		per.open(1, 1);
		
		per.open(1, 2);
		per.open(1, 4);
		per.open(2, 4);
		per.open(3, 2);
		per.open(3, 4);
		per.open(3, 5);
		per.open(4, 1);
		per.open(4, 3);
		per.open(5, 1);
		per.open(5, 2);
		per.open(5, 4);
		per.open(5, 5);
		per.open(4, 4);
		
//		
		assertEquals(true, per.isFull(1, 1));
		assertEquals(true, per.isFull(2, 4));
		assertEquals(true, per.isFull(5, 5));
		assertEquals(true, per.percolates());
		
		per.open(3, 3);
		assertEquals(true, per.isFull(4, 3));
		
		
	}
	
	@Test
	public void testPerculate(){
		Percolation per = new Percolation(2);
		per.open(1, 1);
		assertEquals(true, per.isOpen(1, 1));
		assertEquals(false, per.percolates());
		per.open(2, 2);
		assertEquals(false, per.percolates());
		assertEquals(true, per.isOpen(2, 2));
		per.open(2, 1);
		assertEquals(true, per.percolates());
		
	}
	
	@Test
	public void testInput6() throws FileNotFoundException{
		Percolation per = generate("E:\\workspace\\Algorithm\\src\\input6");
		assertEquals(false, per.isFull(1, 1));	
	}
	
	@Test
	public void testInput8() throws FileNotFoundException{
		Percolation per = generate("E:\\workspace\\Algorithm\\src\\input8");
		assertEquals(false, per.isFull(1, 1));	
	}
	
	@Test
	public void testInput8_no() throws FileNotFoundException{
		Percolation per = generate("E:\\workspace\\Algorithm\\src\\input8-no");
		assertEquals(false, per.isFull(1, 1));	
	}
	@Test
	public void testInput10_no() throws FileNotFoundException{
		Percolation per = generate("E:\\workspace\\Algorithm\\src\\input10-no");
		assertEquals(false, per.isFull(1, 1));	
	}
	
	@Test
	public void testInputGreeting57() throws FileNotFoundException{
		Percolation per = generate("E:\\workspace\\Algorithm\\src\\greeting57");
		//per.dumpData();
		assertEquals(true, per.isFull(1, 1));	
	
	}
	
	@Test
	public void testInputHeart25() throws FileNotFoundException{
		Percolation per = generate("E:\\workspace\\Algorithm\\src\\heart25");
		assertEquals(false, per.isFull(1, 1));	
	}
	private Percolation generate(String fileName) throws FileNotFoundException{

		Scanner scanner = new Scanner(new File(fileName));
		int n = scanner.nextInt();
		Percolation per = new Percolation(n);
		
		while(scanner.hasNextInt()){
			int p = scanner.nextInt();
			int q = scanner.nextInt();
			per.open(p, q);
		}
		//per.dumpData();
		return per;
	}
	@Test
	public void testInput1() throws FileNotFoundException{
		Percolation per = generate("E:\\workspace\\Algorithm\\src\\input1");
	//	per.dumpData();
		assertEquals(true, per.isFull(1, 1));	
	}
	
	@Test
	public void testInput20() throws FileNotFoundException{
		Percolation per = generate("E:\\workspace\\Algorithm\\src\\input20");
	//	per.dumpData();
		assertEquals(false, per.isFull(18, 1));	
	}
	
	@Test
	public void testInput10() throws FileNotFoundException{
		Percolation per = generate("E:\\workspace\\Algorithm\\src\\input10");
	//	per.dumpData();
		assertEquals(false, per.isFull(10, 8));	
	}


}
