package com.bridgelabz.iplanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class BowlingCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String bowlingPlayer;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Ov", required = true)
    public double over;

    @CsvBindByName(column = "Runs", required = true)
    public int bowlingRuns;

    @CsvBindByName(column = "Wkts", required = true)
    public int wkts;

    @CsvBindByName(column = "Avg", required = true)
    public double bowlingAverage;

    @CsvBindByName(column = "SR", required = true)
    public double bStrikeRate;

    @CsvBindByName(column = "4w", required = true)
    public int fourWkts;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWkts;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    public BowlingCSV(double bowlingAverage, String bowlingPlayer) {
        this.bowlingAverage = bowlingAverage;
        this.bowlingPlayer = bowlingPlayer;
    }

    public BowlingCSV() {
    }
}
