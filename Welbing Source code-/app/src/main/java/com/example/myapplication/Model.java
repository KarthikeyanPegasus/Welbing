package com.example.myapplication;


import android.graphics.Color;

import com.anychart.chart.common.dataentry.DataEntry;
import com.github.mikephil.charting.data.Entry;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private int cardbackground;
    private String Topic;
    private String title;
    private String desc;
    private int textcolor;
    private int onclick;

    public int getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(int textcolor) {
        this.textcolor = textcolor;
    }

    public int getOnclick() {
        return onclick;
    }

    public void setOnclick(int onclick) {
        this.onclick = onclick;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    private String description;
    private int confirmationl;
    private int barchart;
    private List<DataEntry> indiastat;

    private int hidewatermark;

    public int getHidewatermark() {
        return hidewatermark;
    }

    public void setHidewatermark(int hidewatermark) {
        this.hidewatermark = hidewatermark;
    }

    public List<DataEntry> getIndiastat() {
        return indiastat;
    }

    public void setIndiastat(List<DataEntry> indiastat) {
        this.indiastat = indiastat;
    }


    public int getBarchart() {
        return barchart;
    }

    public void setBarchart(int barchart) {
        this.barchart = barchart;
    }

    public int getConfirmationl() {
        return confirmationl;
    }

    public void setConfirmationl(int confirmationl) {
        this.confirmationl = confirmationl;
    }

    public int getCardbackground() {
        return cardbackground;
    }

    public void setCardbackground(int cardbackground) {
        this.cardbackground = cardbackground;
    }


    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
