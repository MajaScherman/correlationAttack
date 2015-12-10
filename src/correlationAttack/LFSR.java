package correlationAttack;

import java.util.Arrays;

public class LFSR {

	private int field;
	private int[] polynomial;
	private int[] register;
	private int length;
	private int[] keyState;

	public LFSR(int field, int[] polynomial) {
		this.field = field;
		this.polynomial = polynomial;
		this.register = new int[polynomial.length];
		length = polynomial.length;
		keyState = new int[polynomial.length];

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

	public void setReg(int[] reg) {
		register = Arrays.copyOf(reg, length);
		keyState = Arrays.copyOf(reg, length);
	}

	public int[] incReg() {
		register = Arrays.copyOf(keyState, length);
		register[0]++;
		for (int i = 0; i < length - 1; i++) {
			if (register[i] == 2) {
				register[i] = register[i] % 2;
				register[i + 1]++;
			} else {
				break;
			}
		}
		keyState = Arrays.copyOf(register, length);
		return keyState;
	}

}
