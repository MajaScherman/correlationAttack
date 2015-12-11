package correlationAttack;

public class HammingDistance {

	private String z = "00001000111110101101110110101001001" + "111001110010001100011001111100111010010101110100111"
			+ "101111101110011110000101110001110110010010110101110"
			+ "00000100001111010100100100001011110100001011001010010001";
	private LFSR l;
	private int length;
	private int[] startState;

	public HammingDistance(LFSR l, int length, int[] startState) {
		this.l = l;
		this.length = length;
		this.startState = startState;
	}

	public double calcCorrelation(String comp1, String comp2) {
		double distance = 0;
		char c1;
		char c2;
		for (int i = 0; i < comp2.length(); i++) {
			c1 = comp1.charAt(i);
			c2 = comp2.charAt(i);
			if (c1 != c2) {
				distance++;
			}
		}
		double p = 0;
		p = 1 - (distance / z.length());
		return p;
	}

	public int[] calcKey() {
		l.setReg(startState);
		double bestCorr = 0;
		int[] key = new int[length];

		for (int i = 0; i < Math.pow(2, length); i++) {
			String s = l.calcSequence();
			double corr = calcCorrelation(s, z);
			if (corr > bestCorr) {
				bestCorr = corr;
				key = l.getKeyState();
			}
			l.incReg();// test next keystate
		}
		for (int i = 0; i < key.length; i++) {
			System.out.print(key[i]);
		}
		System.out.println();
		System.out.println("pmax: " + bestCorr);

		return key;
	}

	public String getZ() {
		return z;
	}
}
