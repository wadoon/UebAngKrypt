package ueb4;

import java.util.Arrays;

import com.panayotis.gnuplot.*;
import com.panayotis.gnuplot.plot.*;
import com.panayotis.gnuplot.style.*;
import com.panayotis.gnuplot.terminal.*;

public class RC4 {
    int[] k, s = new int[256];
    byte L;

    public RC4(int[] k2) {
	this.k = k2;
	L = (byte) k2.length;
	init();
    }

    private void init() {
	for (int i = 0; i < s.length; i++) {
	    s[i] = (i % 256);
	    assert s[i] >= 0;
	}
	int j = 0;
	for (int i = 0; i < s.length; i++) {
	    j = (j + s[i] + k[i % L]) % 256;
	    swap(s, j, i);
	}
    }

    private void swap(int[] b, int j, int i) {
	int t = b[i] % 256;
	b[i] = b[j];
	b[j] = t;
    }

    public void next(int[] ciph) {
	int i = 0, j = 0;
	for (int n = 0; n < ciph.length; n++) {
	    i = (i + 1) % 256;
	    j = (j + s[i]) % 256;
	    swap(s, i, j);
	    int rand = s[(s[i] + s[j]) % 256];
	    ciph[n] = rand;
	}
    }

    @Override
    public String toString() {
	return Arrays.toString(s);
    }

    private static void plot(String file, int k[]) {
	JavaPlot plot = new JavaPlot();
	double[][] data = new double[k.length][2];
	for (int i = 0; i < k.length; i++) {
	    data[i][0] = i;
	    data[i][1] = k[i];
	}

	plot.setTitle("Chaos of Perumation");
	plot.setTerminal(new FileTerminal("pdf", file));

	DataSetPlot dataP = new DataSetPlot(data);
	PlotStyle ps = new PlotStyle(Style.POINTS);
	ps.setLineType(NamedPlotColor.DARK_RED);
	dataP.setPlotStyle(ps);
	dataP.setTitle("");
	plot.addPlot(dataP);

	FunctionPlot dataL = new FunctionPlot("x");
	PlotStyle rp = new PlotStyle(Style.LINES);
	rp.setLineType(NamedPlotColor.GRAY);
	rp.setLineWidth(3);
	dataL.setPlotStyle(rp);
	dataL.setTitle("");
	plot.addPlot(dataL);

	plot.getAxis("x").setLabel("array position");
	plot.getAxis("x").setBoundaries(0, k.length);
	plot.getAxis("y").setLabel("array value");
	plot.getAxis("y").setBoundaries(0, k.length);

	// GNUPlot.getDebugger().setLevel(Debug.VERBOSE);
	plot.plot();
    }

    public static void main(String[] args) {
	int k[] = { 0x1A, 0x2B, 0x3C, 0x4D, 0x5E, 0x6F, 0x77 };
	RC4 rc4 = new RC4(k);
	 System.out.println("S:"+rc4);
//	plot("beforerc4.pdf", rc4.s);
	int b[] = new int[100];
	rc4.next(b);
	plot("rc4-key-seq.pdf", b);
	 System.out.println("S:" +rc4);
//	plot("afterrc4.pdf", rc4.s);
	System.out.println(Arrays.toString(b));
    }
}
