import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.plot.FunctionPlot;
import com.panayotis.gnuplot.style.NamedPlotColor;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.FileTerminal;

public class EulerPhi {

    public static int ggT(int a, int b) {
	// a = q * b + r
	if(b==0)
	    return a;
	return ggT(b,a%b);
    }

    public static int phi(int m) {
	int c = 0;
	for (int i = 1; i < m; i++) {
	    if (ggT(i, m) == 1)
		c++;
	}
	return c;
    }

    public static void main(String[] args) {
	System.out.println(ggT(6,21));
	System.out.println("EulerPhi.main()");
	int[][] data = new int[10000][2];

	for (int i = 0; i < data.length; i++) {
	    data[i][0] = i;
	    data[i][1] = phi(i);
	    System.err.println(i);
	}

	JavaPlot plot = new JavaPlot();

	plot.setTitle("Growth Rate of phi(n)");
	plot.setTerminal(new FileTerminal("pdf", "growth-rate.pdf"));

	DataSetPlot dataP = new DataSetPlot(data);
	PlotStyle ps = new PlotStyle(Style.LINES);
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

	plot.getAxis("x").setLabel("n");
	plot.getAxis("x").setBoundaries(0, 500);
	plot.getAxis("y").setLabel("phi(n)");
	plot.getAxis("y").setBoundaries(0, 500);
	plot.plot();
    }
}
