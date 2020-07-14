package com.bridgelabz.iplanalyser.dao;

import com.bridgelabz.iplanalyser.model.BowlingCSV;
import com.bridgelabz.iplanalyser.model.MostRunCSV;
import com.bridgelabz.iplanalyser.services.CricketLeagueAnalysis;

public class CricketAnalysisDAO {
    public double over;
    public int match;
    public double economy;
    public int fiveWkts;
    public int fourWkts;
    public int wkts;
    public double bowlingAverage;
    public double bStrikeRate;
    public int bowlerRun;
    public String bowlingPlayer;


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

    public CricketAnalysisDAO(BowlingCSV bowlingCSV) {
        bowlingPlayer = bowlingCSV.bowlingPlayer;
        bowlerRun = bowlingCSV.bowlingRuns;
        bStrikeRate = bowlingCSV.bStrikeRate;
        bowlingAverage = bowlingCSV.bowlingAverage;
        wkts = bowlingCSV.wkts;
        fourWkts = bowlingCSV.fourWkts;
        fiveWkts = bowlingCSV.fiveWkts;
        economy = bowlingCSV.economy;
        match = bowlingCSV.match;
        over = bowlingCSV.over;

    }

    public Object getIplLeagueDTOS(CricketLeagueAnalysis.CricketType cricketType) {
        if (cricketType.equals(CricketLeagueAnalysis.CricketType.BATTING))
            return new MostRunCSV(average, strikeRate, player, six, four, runs);

        else if (cricketType.equals(CricketLeagueAnalysis.CricketType.BOWLING))
            return new BowlingCSV(bowlingAverage,bowlingPlayer);
        else
            return null;
    }
}