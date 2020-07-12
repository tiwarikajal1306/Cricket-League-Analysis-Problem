package com.bridgelabz.iplanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class MostRunCSV {

    public MostRunCSV() {
    }

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "4s", required = true)
    public int four;

    @CsvBindByName(column = "6s", required = true)
    public int six;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    public MostRunCSV(double average, double strikeRate, String player, int six, int four) {
        this.average = average;
        this.strikeRate = strikeRate;
        this.player = player;
        this.six = six;
        this.four = four;
    }
}