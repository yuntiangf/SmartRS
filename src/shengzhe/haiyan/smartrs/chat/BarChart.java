/**
 * @author
 * 2016-6-15
 * @version
 * @parameter
 */
package shengzhe.haiyan.smartrs.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * @author Administrator
 *
 */
public class BarChart extends AbstractDemoChart{

	/**
	 * 
	 */
	public BarChart() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.example.test.IDemoChart#getName()
	 */
	@Override
	public String getName() {
		
		return "两类产品的销售对比图";
	}

	/* (non-Javadoc)
	 * @see com.example.test.IDemoChart#getDesc()
	 */
	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.example.test.IDemoChart#execute(android.content.Context)
	 */
	@Override
	public Intent execute(Context context) {
//		 double[] values = new double[] { 13, 17, 14, 10, 8 };
		List<double []> values = new ArrayList<double[]>();
		values.add(new double[]{12,15,17,16,13,17,19,22,18,16,12,9});
		values.add(new double[]{15,12,13,19,17,15,18,20,17,15,16,13});
		String[] titles = new String[] { "瓜果","蔬菜"};
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		// renderer.setZoomButtonsVisible(true);
		renderer.setAxisTitleTextSize(32);
		renderer.setChartTitleTextSize(50);
		renderer.setLabelsTextSize(30);
		renderer.setLegendTextSize(30);
		renderer.setXAxisMin(1);
	    renderer.setXAxisMax(12);
	    renderer.setYAxisMin(0);
	    renderer.setYAxisMax(25);
		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setXLabelsAlign(Align.LEFT);
		renderer.setYLabelsAlign(Align.LEFT);
		renderer.setFitLegend(true);
		renderer.setBarSpacing(0.5f);
		renderer.setXTitle("月份");
//		renderer.setYLabelsPadding(10);
		renderer.setYTitle("销售量（万斤）");
		renderer.setYLabelsAngle(270);
		renderer.setMargins(new int[] { 80, 70, 20, 20 });
		renderer.setPanLimits(new double[] { 0, 8, 0, 20 });
	    renderer.setZoomLimits(new double[] { 0, 15, -10, 30 });
		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(Color.BLUE);
		renderer.addSeriesRenderer(r);
		r = new SimpleSeriesRenderer();
		r.setColor(Color.GREEN);
		renderer.addSeriesRenderer(r);
		// Intent intent = ChartFactory.getPieChartIntent(context,
		// buildCategoryDataset("Project budget", values), renderer, "Budget");
		Intent intent = ChartFactory.getBarChartIntent(context,
				buildBarDataset(titles, values), renderer, Type.DEFAULT);
		return intent;
	}
	
	private XYMultipleSeriesDataset getBarDemoDataset() {
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	    final int nr = 10;
	    Random r = new Random();
	    for (int i = 0; i < 2; i++) {
	      CategorySeries series = new CategorySeries("Demo series " + (i + 1));
	      for (int k = 0; k < nr; k++) {
	        series.add(100 + r.nextInt() % 100);
	      }
	      dataset.addSeries(series.toXYSeries());
	    }
	    return dataset;
	  }

}
