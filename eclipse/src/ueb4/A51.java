package ueb4;
import java.util.BitSet;

public class A51 {
    ShiftRegister x, y, z;

    public A51() {
	x = new ShiftRegister(19,349525);
	y = new ShiftRegister(22,3355443);
	z = new ShiftRegister(23,7401712);
    }

    public int maj(int i, int j, int k) {
	int a = i << 2 | j << 1 | k;
	switch (a) {
	case 0:// 000
	case 1:// 001
	case 4:// 100
	case 2:// 010
	    return 0;
	}
	return 1;
    }

    public int next() {
	int m = maj(x.get(8), y.get(10), z.get(10));

	if (m == x.get(8)) {
	    int t = xor(x.get(13), x.get(16), x.get(17), x.get(18));
	    x.push(t);
	}
	if (m == y.get(10)) {
	    int t = xor(y.get(20), y.get(21));
	    y.push(t);
	}

	if (m == z.get(10)) {
	    int t = xor(z.get(7), z.get(20), x.get(21), x.get(22));
	    z.push(t);
	}
	return xor(x.get(18), y.get(21), z.get(22));
    }

    private int xor(int i, int j, int k, int l) {
	return i ^ j ^ k ^ l;
    }

    private int xor(int i, int j, int k) {
	return i ^ j ^ k;
    }

    private int xor(int i, int j) {
	return i ^ j;
    }
    
    @Override
    public String toString() {
	return "x: " + x + "\ny: " + y + "\nz; " + z;
    }

    public static void main(String[] args) {
	A51 c = new A51();
	
	System.out.println(c);
	
	for (int i = 0; i < 1000; i++)
	    System.out.print(c.next());
	System.out.println();
    }

    private static void checkBin(int i) {
	if (i != 0 && i != 1)
	    throw new IllegalArgumentException();
    }

    class ShiftRegister {
	int reg;
	int size;

	public ShiftRegister(int size, int content) {
	    if (size >= 64)
		throw new IllegalArgumentException();
	    this.size = size;
	    reg=content;
	}

	public void push(int t) {
	    checkBin(t);
	    reg = (reg << 1) | t;
	}

	public int get(int i) {
	    return (int) ((reg >> i) & 1);
	}

	@Override
	public String toString() {
	    StringBuilder b = new StringBuilder(Integer.toString(reg, 2));
	    return String.format("%"+size+"s", b.reverse().toString());
	}
    }
}