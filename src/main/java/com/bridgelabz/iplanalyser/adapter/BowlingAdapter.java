package com.bridgelabz.iplanalyser.adapter;

import com.bridgelabz.iplanalyser.dao.CricketAnalysisDAO;
import com.bridgelabz.iplanalyser.exception.CricketLeagueAnalysisException;
import com.bridgelabz.iplanalyser.model.BowlingCSV;
import com.bridgelabz.iplanalyser.model.MostRunCSV;

import java.util.List;

public class BowlingAdapter extends IplLeagueAdapter {
    @Override
    public List<CricketAnalysisDAO> loadCricketLeagueData(String... csvFilePath) throws CricketLeagueAnalysisException {
        List<CricketAnalysisDAO> leagueList = super.loadCricketLeagueData(BowlingCSV.class, csvFilePath[0]);
        return leagueList;
    }
}
