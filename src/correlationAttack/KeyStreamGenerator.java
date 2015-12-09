package correlationAttack;

public class KeyStreamGenerator {

	private LFSR l1;
	private LFSR l2;
	private LFSR l3;

	public KeyStreamGenerator(LFSR l1, LFSR l2, LFSR l3) {

		this.l1 = l1;
		this.l2 = l2;
		this.l3 = l3;
	}

	public String generateZ() {
		String s = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";

		s1 = l1.calcSequence();
		System.out.println(s1.length());
		s2 = l2.calcSequence();
		System.out.println(s2.length());
		s3 = l3.calcSequence();
		System.out.println(s3.length());
		for (int i = 0; i < s1.length(); i++) {
			if ((s1.charAt(i) + (int) s2.charAt(i) + (int) s3.charAt(i)) - 48 * 3 > 1) {
				s += 1;
			} else {
				s += 0;
			}
		}
		return s;
	}

}
