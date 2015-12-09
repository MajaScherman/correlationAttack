package correlationAttack;

public class Main {

	public static void main(String[] args) {
		int[] polynomial1 = { 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1 };
		// x13+x11+x10+x7+x6+x4+x2+x+1
		LFSR l1 = new LFSR(2, polynomial1);
		//l1.calcSequence();
		int[] polynomial2 = { 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1 };
		// x15+x13+x11+x10+x7+x6+x4+x2+1
		LFSR l2 = new LFSR(2, polynomial2);
		//l2.calcSequence();
		int[] polynomial3 = { 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1 } ;
		// x17+x16+x13+x10+x8+x5+x4+x2+1
		LFSR l3 = new LFSR(2, polynomial3);
		//l3.calcSequence();

		
		HammingDistance h1 = new HammingDistance(l1);
		int[] key1 = h1.calcKey();
		HammingDistance h2 = new HammingDistance(l2);
		int[] key2 = h2.calcKey();
		HammingDistance h3 = new HammingDistance(l3);
		int[] key3 = h3.calcKey();
		
		l1.setRegister(key1);
		l2.setRegister(key2);
		l3.setRegister(key3);
		
		KeyStreamGenerator k = new KeyStreamGenerator(l1,l2,l3);
		String z = k.generateZ();
		System.out.println("Our z: " + z);
		String z1 = h1.getZ();
		System.out.println("The z: " + z1);
		h1.setComperationString(z);
		System.out.println("Correlation z1&z: " + h1.calc());
	}

}
