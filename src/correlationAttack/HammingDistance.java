package correlationAttack;

public class HammingDistance {

	private String z = "00001000111110101101110110101001001" + "111001110010001100011001111100111010010101110100111"
			+ "101111101110011110000101110001110110010010110101110"
			+ "00000100001111010100100100001011110100001011001010010001";
	private LFSR l;
	private String compS;
	private int length;
	private int[] startState;

	public HammingDistance(LFSR l, int length, int[] startState) {
		this.l = l;
		this.compS = "";
		this.length = length;
		this.startState = startState;
	}

	public void setComperationString(String s) {
		compS = s;
	}

	public double calcDistance() {
		compS = l.calcSequence();
		double distance = 0;
		char c1;
		char c2;
		for (int i = 0; i < compS.length(); i++) {
			c1 = compS.charAt(i);
			c2 = z.charAt(i);
			if (c1 != c2) {
				distance++;
			}
		}
		// correlation
		double p = 0;
		p = 1 - (distance / z.length());
		// System.out.println("distance: " + distance + " p = " + p);
		return p;
	}

	public int[] calcKey() {
		
		l.setReg(startState);
		double p = 0;
		double pmax = 0;
		int[] key = new int[length];
		int[] tempkey = new int[length];
		for (int i = 0; i < Math.pow(2, length); i++) { // r.length 2^17 max =
														// 131 072'
			tempkey = l.incReg();
			p = calcDistance();
			if (p > pmax) {
				pmax = p;
				key = tempkey;
			}
		}
		for (int i = 0; i < key.length; i++) {
			System.out.print(key[i]);
		}
		System.out.println();
		System.out.println("pmax: " + pmax);
		return key;
	}

	public String getZ() {
		return z;
	}
}
