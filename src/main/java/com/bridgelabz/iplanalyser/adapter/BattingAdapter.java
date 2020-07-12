package com.bridgelabz.iplanalyser.adapter;

import com.bridgelabz.iplanalyser.dao.CricketAnalysisDAO;
import com.bridgelabz.iplanalyser.exception.CricketLeagueAnalysisException;
import com.bridgelabz.iplanalyser.model.MostRunCSV;

import java.util.List;

public class BattingAdapter extends IplLeagueAdapter {

    @Override
    public List<CricketAnalysisDAO> loadCricketLeagueData(String... csvFilePath) throws CricketLeagueAnalysisException {
        List<CricketAnalysisDAO> leagueList = super.loadCricketLeagueData(MostRunCSV.class, csvFilePath[0]);
        return leagueList;
    }
}
