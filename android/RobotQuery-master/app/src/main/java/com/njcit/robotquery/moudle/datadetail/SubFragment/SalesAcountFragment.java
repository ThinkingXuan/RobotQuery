package com.njcit.robotquery.moudle.datadetail.SubFragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
import com.njcit.robotquery.base.BaseChart;
import com.njcit.robotquery.bean.server.SalesAcountBean;
import com.njcit.robotquery.databinding.HeaderItemCountCustomBinding;
import com.njcit.robotquery.http.ApiManage;
import com.njcit.robotquery.moudle.detail.item.ChartItem;
import com.njcit.robotquery.moudle.detail.item.LineChartItem;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by youxuan on 2017/2/26 0026.
 * Description: CountFragment
 */

public class SalesAcountFragment extends BaseChart {

    public static final String TAG = "SalesAcountFragment";
    private ArrayList<ChartItem> list = new ArrayList<ChartItem>();
    private ListView lv;
    private HeaderItemCountCustomBinding mHeaderBinding;
    private View mHeaderView;

    public static SalesAcountFragment newInstance() {
        SalesAcountFragment fragment = new SalesAcountFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count_layout, container, false);
        lv = (ListView) view.findViewById(R.id.lv_count_fragment_item);
        initData();

        return view;
    }

    private void initData() {

        Subscription subscription = ApiManage.getInstence().getQueryService().getSalesAcount("2017-4-1 21:02:54","2017-7-30 21:03:07")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SalesAcountBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SalesAcountBean salesAcountBean) {
                        if (salesAcountBean.getCode().equals("1")) {
                            setAdapter(salesAcountBean);
                        }
                    }

                });


    }
    private void setAdapter(SalesAcountBean salesAcountBean) {
        Log.d(TAG, "setAdapter: 1");
        list.add(new LineChartItem(generateDataLine(salesAcountBean), getContext()));
        Log.d(TAG, "setAdapter: 2");
        ChartDataAdapter cda = new ChartDataAdapter(getContext(), list);
        Log.d(TAG, "setAdapter: 3");
        lv.setAdapter(cda);
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
    private LineData generateDataLine(SalesAcountBean salesAcountBean) {

        Log.d(TAG, "generateDataLine: 1");


        ArrayList<Entry> e1 = new ArrayList<Entry>();

        Log.d(TAG, "generateDataLine: "+salesAcountBean.getEntryList().size());

        for (int i = 0; i < salesAcountBean.getEntryList().size(); i++) {
            SalesAcountBean.EntryListBean point = salesAcountBean.getEntryList().get(i);
            e1.add(new Entry(i, Float.valueOf(point.getY())));
            Log.d(TAG, "generateDataLine: 3");
        }

        Log.d(TAG, "generateDataLine:2 "+e1.toString());
        LineDataSet set = new LineDataSet(e1, "今日销售量 ");

        set.setLineWidth(2.5f);
        set.setCircleRadius(4.5f);
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setDrawValues(false);

        LineData data = new LineData(set);

        return data;
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
