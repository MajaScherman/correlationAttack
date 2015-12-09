package correlationAttack;

public class HammingDistance {

	private String z = "00001000111110101101110110101001001" + "111001110010001100011001111100111010010101110100111"
			+ "101111101110011110000101110001110110010010110101110"
			+ "00000100001111010100100100001011110100001011001010010001";
	private LFSR l;
	private String compS;

	public HammingDistance(LFSR l) {
		this.l = l;
		this.compS = "";
	}
	public void setComperationString(String s){
		compS = s;
	}
	private void setStringFromLFSR(){
		compS = l.calcSequence();
	}

	public double calc() {
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
		//System.out.println("distance: " + distance + " p = " + p);
		return p;
	}

	public int[] calcKey() {
		double p = 0;
		double pmax = 0;
		int indexmax = 0;
		int[] r = l.getRegister(0);
		for (int i = 0; i < Math.pow(2, r.length); i++) { //r.length 2^17 max = 131 072
			l.changeInitialRegister();
			setStringFromLFSR();
			p = calc();
			if (p > pmax) {
				pmax = p;
				indexmax = i;
			}
		}
		l.changeInitialRegister();
		int[] key = l.getRegister(indexmax);
		for (int i = 0; i < key.length; i++) {
			System.out.print(key[i]);
		}
		System.out.println();
		System.out.println("pmax: " + pmax);
		return key;
	}
	
	public String getZ(){
		return z;
	}
}
