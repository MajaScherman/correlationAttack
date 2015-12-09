package correlationAttack;

import java.util.Arrays;

public class LFSR {

	private int field;
	private int[] polynomial;
	private int[] register;
	private int[] searchRegister;

	public LFSR(int field, int[] polynomial) {
		this.field = field;
		this.polynomial = polynomial;
		this.register = Arrays.copyOf(polynomial,polynomial.length); // initial state is polynomial, to be
									// shifted
		this.searchRegister = Arrays.copyOf(polynomial,polynomial.length);

	}

	private int arithmetic() {
		int i = 0;
		int s1 = 0;
		for (i = 0; i < register.length; i++) {
			if (polynomial[i] != 0) {
				s1 += (register[i] * polynomial[i]) % field;
				s1 = s1 % field;
			}
		}
		return ((s1) * polynomial[polynomial.length - 1]) % field;
	}

	public int shift() {
		int i = 0;
		int out = register[i];
		int newS1 = arithmetic();
		for (i = 0; i < register.length - 1; i++) {
			register[i] = register[i + 1];
		}
		register[i] = newS1;
		return out;
	}

	public String calcSequence() {
		String s = "";
		for (int i = 0; i < 193; i++) {// 193 is lenght of key seq
			s += "" + shift();
		}
		return s;
	}
	public int[] getRegister(int indexmax){
		register = Arrays.copyOf(polynomial,polynomial.length);
		for (int i = 0; i < indexmax; i++) {
			shift();
		}
		return register;
	}
	
	public void setRegister(int[] key){
		register = key;
		System.out.println();
		for (int i = 0; i < register.length; i++) {
			System.out.print(register[i]);
		}
	}
	
	public void changeInitialRegister(){
		shiftSearchRegister();
	}
	private int shiftSearchRegister(){
		int i = 0;
		int out = searchRegister[i];
		int newS1 = arithmetic();
		for (i = 0; i < searchRegister.length - 1; i++) {
			searchRegister[i] = searchRegister[i + 1];
		}
		searchRegister[i] = newS1;
		return out;
	}

}
