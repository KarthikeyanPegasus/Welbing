package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;


import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Area;
import com.anychart.core.cartesian.series.Line;
import com.anychart.core.ui.Crosshair;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.homecarouselViewHolder> {
    private List<Model> models;


    public Adapter(List<Model> models) {
        this.models = models;

    }


    @NonNull
    @Override
    public homecarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new homecarouselViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.maincarouselitem,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull homecarouselViewHolder holder, int position) {
        holder.homecarouseldata(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class homecarouselViewHolder extends RecyclerView.ViewHolder {
        ImageView cardbackground;
        TextView topic,description;
        AnyChartView barChart;
        View hidewatermark;
        LinearLayout confirmation;
        CardView homecarouselcard;
        public homecarouselViewHolder(@NonNull View view) {
            super(view);
            cardbackground = view.findViewById(R.id.cardbackground);
            topic = view.findViewById(R.id.Topic);
            description = view.findViewById(R.id.description);
            confirmation = view.findViewById(R.id.confirmation);
            barChart = view.findViewById(R.id.barchart);
            hidewatermark = view.findViewById(R.id.hidethewatermark);
            homecarouselcard = view.findViewById(R.id.homecarouselcard);
        }
        void homecarouseldata(Model models){
            cardbackground.setImageResource(models.getCardbackground());
            topic.setText(models.getTopic());
            description.setText(models.getDescription());
            description.setTextColor(models.getTextcolor());
            confirmation.setVisibility(models.getConfirmationl());
            barChart.setVisibility(models.getBarchart());
            hidewatermark.setVisibility(models.getHidewatermark());
            if(models.getOnclick() == 1){
                homecarouselcard.setOnClickListener(newstiktok);
            }



            Cartesian areaChart = AnyChart.area();

            areaChart.animation(true);

            Crosshair crosshair = areaChart.crosshair();
            crosshair.enabled(true);
            // TODO yStroke xStroke in crosshair
            crosshair.yStroke((Stroke) null, null, null, (String) null, (String) null)
                    .xStroke("#fff", 1d, null, (String) null, (String) null)
                    .zIndex(39d);
            crosshair.yLabel(0).enabled(true);

            areaChart.yScale().stackMode(ScaleStackMode.VALUE);

            areaChart.title("Corona stats");

            List<DataEntry> seriesData = new ArrayList<>();
            seriesData = models.getIndiastat();

            Set set = Set.instantiate();
            set.data(seriesData);
            Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
            Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");



            Area series1 = areaChart.area(series1Data);
            series1.name("World");
            series1.stroke("3 #fff");
            series1.hovered().stroke("3 #fff");
            series1.hovered().markers().enabled(true);
            series1.hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4d)
                    .stroke("1.5 #fff");
            series1.markers().zIndex(100d);

            Area series2 = areaChart.area(series2Data);
            series2.name("India");
            series2.fill("#FF5959");
            series2.stroke("3 #fff");
            series2.hovered().stroke("3 #fff");
            series2.hovered().markers().enabled(true);
            series2.hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4d)
                    .stroke("1.5 #fff");
            series2.markers().zIndex(100d);
            areaChart.legend().enabled(true);
            areaChart.legend().fontSize(13d);
            areaChart.legend().padding(0d, 0d, 20d, 0d);

            areaChart.xAxis(0).title(false);
            areaChart.yAxis(0).title("cases in million");

            areaChart.interactivity().hoverMode(HoverMode.BY_X);
            areaChart.tooltip()
                    .valuePrefix("Cases :")
                    .valuePostfix(" M.")
                    .displayMode(TooltipDisplayMode.UNION);


            barChart.setChart(areaChart);


        }
        private View.OnClickListener newstiktok = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //forward to the newstiktok page

            }
        };






    }
}
