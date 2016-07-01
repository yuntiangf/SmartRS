/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package shengzhe.haiyan.smartrs.chat;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * Average temperature demo chart.
 */
public class LineChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "衢州地区的三种农产品全年销售量";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "折线图";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Intent execute(Context context) {
    String[] titles = new String[] { "橙子", "功夫鸡", "两头乌" };
    List<double[]> x = new ArrayList<double[]>();
    for (int i = 0; i < titles.length; i++) {
      x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
    }
    List<double[]> values = new ArrayList<double[]>();
    values.add(new double[] { 17.1, 17.5, 17.8, 17.8, 17.4, 18.4, 19.3, 19.5, 20.3, 20.2, 19.2,
        17.9 });
    values.add(new double[] { 11, 10.2, 9.5, 9.6, 9.2, 9.9, 10.3, 10.1, 10.2, 10.6, 10.9, 11.2 });
    values.add(new double[] { 8.5, 7.8, 7.8, 7.3, 7.5, 7.2, 7.7, 7.8, 7.3, 7.5, 8.2, 8.5 });
    int[] colors = new int[] { Color.YELLOW, Color.GREEN,  Color.RED };
    PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE,
        PointStyle.TRIANGLE, PointStyle.SQUARE };
    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
    int length = renderer.getSeriesRendererCount();
    for (int i = 0; i < length; i++) {
      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
    }
    setChartSettings(renderer, "农产品全年销售量", "月份", "销售量（斤）", 0.5, 12.5, 0, 30,
        Color.YELLOW, Color.GREEN);
    
    renderer.setXLabels(12);
    renderer.setYLabels(10);
    renderer.setShowGrid(true);
    renderer.setXLabelsAlign(Align.RIGHT);
    renderer.setYLabelsAlign(Align.RIGHT);
//    renderer.setZoomButtonsVisible(true);
    renderer.setPanLimits(new double[] { 0, 12, 0, 30 });
    renderer.setZoomLimits(new double[] { 0, 12, 0, 30 });
    
    renderer.setBackgroundColor(Color.YELLOW);
    XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
    XYSeries series = dataset.getSeriesAt(0);
//    series.addAnnotation("Vacation", 6, 25);
    Intent intent = ChartFactory.getLineChartIntent(context, dataset, renderer,
        "销售量");
    return intent;
  }

}
