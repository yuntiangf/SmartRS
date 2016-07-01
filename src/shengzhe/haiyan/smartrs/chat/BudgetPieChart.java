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

import org.achartengine.ChartFactory;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

/**
 * Budget demo pie chart.
 */
public class BudgetPieChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "每类产品的比例";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "饼图";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Intent execute(Context context) {
    double[] values = new double[] { 13, 17, 14, 10, 8 };
    String[] title = new String[]{"瓜果","蔬菜","肉类","海鲜","其他"};
    int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN };
    DefaultRenderer renderer = buildCategoryRenderer(colors);
//    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(50);
    renderer.setLabelsTextSize(30);
    renderer.setLegendTextSize(50);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(false);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, Color.BLUE);
    r.setGradientStop(0, Color.GREEN);
    r.setHighlighted(true);
//    Intent intent = ChartFactory.getPieChartIntent(context,
//        buildCategoryDataset("Project budget", values), renderer, "Budget");
    Intent intent = ChartFactory.getPieChartIntent(context,
          buildCategoryDataset(title, values), renderer, "Budget");
    return intent;
  }

}
