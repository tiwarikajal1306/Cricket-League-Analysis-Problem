package com.bridgelabz.iplanalyser.dao;

import com.bridgelabz.iplanalyser.model.MostRunCSV;

public class CricketAnalysisDAO {
    public String player;
    public int four;
    public int six;
    public int runs;
    public double average;
    public double strikeRate;

    public CricketAnalysisDAO(MostRunCSV mostRunCSV) {
        this.player = mostRunCSV.player;
        this.four = mostRunCSV.four;
        this.six = mostRunCSV.six;
        this.runs = mostRunCSV.runs;
        this.average = mostRunCSV.average;
        this.strikeRate = mostRunCSV.strikeRate;
    }

}
