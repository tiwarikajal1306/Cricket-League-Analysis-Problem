package com.bridgelabz.iplanalyser.dao;

import com.bridgelabz.iplanalyser.model.MostRunCSV;
import com.bridgelabz.iplanalyser.services.CricketLeagueAnalysis;

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
    public Object getIplLeagueDTOS(CricketLeagueAnalysis.CricketType cricketType) {
        if (cricketType.equals(CricketLeagueAnalysis.CricketType.BATTING))
            return new MostRunCSV(average, strikeRate, player, six, four, runs);
        else
            return null;
    }
}