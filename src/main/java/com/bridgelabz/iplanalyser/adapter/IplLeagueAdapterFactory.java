package com.bridgelabz.iplanalyser.adapter;

import com.bridgelabz.iplanalyser.dao.CricketAnalysisDAO;
import com.bridgelabz.iplanalyser.exception.CricketLeagueAnalysisException;
import com.bridgelabz.iplanalyser.services.CricketLeagueAnalysis;

import java.util.List;

public class IplLeagueAdapterFactory {

    public List<CricketAnalysisDAO> getLeagueData(CricketLeagueAnalysis.CricketType cricketType, String[] csvFilePath) throws CricketLeagueAnalysisException {
        if (cricketType.equals(CricketLeagueAnalysis.CricketType.BATTING))
            return new BattingAdapter().loadCricketLeagueData(csvFilePath);
        else throw new CricketLeagueAnalysisException("Invalid type", CricketLeagueAnalysisException.ExceptionType.NO_DATA);

    }
}
