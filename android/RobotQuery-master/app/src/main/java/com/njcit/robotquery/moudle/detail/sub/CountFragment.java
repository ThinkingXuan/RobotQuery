package com.njcit.robotquery.moudle.detail.sub;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.njcit.robotquery.R;
import com.njcit.robotquery.bean.ObjType;
import com.njcit.robotquery.bean.server.Employee;
import com.njcit.robotquery.bean.server.Orders;
import com.njcit.robotquery.bean.server.Products;
import com.njcit.robotquery.bean.server.chartbean.LineBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.moudle.detail.DemoBase;
import com.njcit.robotquery.moudle.detail.item.BarChartItem;
import com.njcit.robotquery.moudle.detail.item.ChartItem;
import com.njcit.robotquery.moudle.detail.item.LineChartItem;
import com.njcit.robotquery.moudle.detail.item.PieChartItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by youxuan on 2017/2/26 0026.
 * Description: CountFragment
 */

public class CountFragment extends DemoBase {

    private LineBean lineBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count_layout, container, false);
        ListView lv = (ListView) view.findViewById(R.id.lv_count_fragment_item);
        initData();
        ArrayList<ChartItem> list = new ArrayList<ChartItem>();
        // 30 items
        for (int i = 0; i < 30; i++) {

            if (i % 3 == 0) {
                list.add(new LineChartItem(generateDataLine(i + 1), getContext()));
            } else if (i % 3 == 1) {
                list.add(new BarChartItem(generateDataBar(i + 1), getContext()));
            } else if (i % 3 == 2) {
                list.add(new PieChartItem(generateDataPie(i + 1), getContext()));
            }
        }

        ChartDataAdapter cda = new ChartDataAdapter(getContext(), list);
        lv.setAdapter(cda);
        return view;
    }

    private void initData() {

        Bundle bundle = getActivity().getIntent().getExtras();
        int type = (int) bundle.get(Constants.ACTIVIIY_BUNDLE_KEY_TYPE);

        if (type == ObjType.SALE.ordinal()) {
            lineBean = (LineBean) bundle.get(Constants.ACTIVIIY_BUNDLE_KEY_OBJ);
        }
    }

    /**
     * adapter that supports 3 different item types
     */
    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        public ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            return getItem(position).getItemType();
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private LineData generateDataLine(int cnt) {

        ArrayList<Entry> e1 = new ArrayList<Entry>();

        for (int i = 0; i < lineBean.getEntryList().size(); i++) {
            LineBean.LinePoint point = lineBean.getEntryList().get(i);

            e1.add(new Entry(Float.valueOf(point.getX()), Float.valueOf(point.getY())));

            //e1.add(new Entry(i, (int) (Math.random() * 65) + 40));
        }

        LineDataSet d1 = new LineDataSet(e1, "今日销售量 ");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);

//        ArrayList<Entry> e2 = new ArrayList<Entry>();
//
//        for (int i = 0; i < 12; i++) {
//            e2.add(new Entry(i, e1.get(i).getY() - 30));
//        }

//        LineDataSet d2 = new LineDataSet(e2, "设备损坏率 " + "(2)");
//        d2.setLineWidth(2.5f);
//        d2.setCircleRadius(4.5f);
//        d2.setHighLightColor(Color.rgb(244, 117, 117));
//        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
//        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
//        d2.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
//        sets.add(d2);

        LineData cd = new LineData(sets);
        return cd;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private BarData generateDataBar(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
        }

        BarDataSet d = new BarDataSet(entries, "设备修理率 ");
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.9f);
        return cd;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie(int cnt) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Quarter " + (i + 1)));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData cd = new PieData(d);
        return cd;
    }

}
