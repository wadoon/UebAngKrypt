import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.plot.FunctionPlot;
import com.panayotis.gnuplot.style.NamedPlotColor;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.FileTerminal;

public class DiskFun2Mod36 {
    public static void main(String[] args) {
	int[][] data = new int[100][2];

	for (int i = 0; i < data.length; i++) {
	    data[i][0] = i;
	    data[i][1] = (int) (Math.pow(2, i)%37);
	}

	JavaPlot plot = new JavaPlot();

	plot.setTitle("Growth Rate of phi(n)");
	plot.setTerminal(new FileTerminal("pdf", "expofun.pdf"));

	DataSetPlot dataP = new DataSetPlot(data);
	PlotStyle ps = new PlotStyle(Style.LINES);
	ps.setLineType(NamedPlotColor.DARK_RED);
	dataP.setPlotStyle(ps);
	dataP.setTitle("");
	plot.addPlot(dataP);

	plot.getAxis("x").setLabel("n");
	plot.getAxis("x").setBoundaries(0, 100);
	plot.getAxis("y").setLabel("");
	plot.getAxis("y").setBoundaries(0, 40);
	plot.plot();
    }
}
