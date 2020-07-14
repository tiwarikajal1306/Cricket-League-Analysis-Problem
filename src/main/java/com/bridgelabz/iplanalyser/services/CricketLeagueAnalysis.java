package com.bridgelabz.iplanalyser.services;

import com.bridgelabz.iplanalyser.adapter.IplLeagueAdapterFactory;
import com.bridgelabz.iplanalyser.dao.CricketAnalysisDAO;
import com.bridgelabz.iplanalyser.exception.CricketLeagueAnalysisException;
import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalysis {

    List<CricketAnalysisDAO> leagueList = new ArrayList<>();

    public enum CricketType {
        BATTING
    }
    private CricketType cricketType;

    public CricketLeagueAnalysis(CricketType cricketType) {
        this.cricketType = cricketType;
    }

    public  List<CricketAnalysisDAO>  loadCricketLeagueData(String... csvFilePath) throws CricketLeagueAnalysisException{
        return this.leagueList = new IplLeagueAdapterFactory().getLeagueData(cricketType, csvFilePath);
    }

    /**
     *  top batting avg of the cricketers
     */

    public String topBattingAverage() throws CricketLeagueAnalysisException {
        if (leagueList == null || leagueList.size() == 0)
            throw new CricketLeagueAnalysisException("No data", CricketLeagueAnalysisException.ExceptionType.NO_DATA);
        Comparator<CricketAnalysisDAO> IPLComparator = Comparator.comparing(census -> census.average);
        ArrayList leagueDTO = leagueList.stream()
                .sorted(IPLComparator)
                .map(censusDAO -> censusDAO.getIplLeagueDTOS(cricketType))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateCensusJson = new Gson().toJson(leagueDTO);
       return sortedStateCensusJson;
    }

    /**
     *  top StrikeRate player
     * @return
     * @throws CricketLeagueAnalysisException
     */

    public String topStrikeRate() throws CricketLeagueAnalysisException {
        if (leagueList == null || leagueList.size() == 0)
            throw new CricketLeagueAnalysisException("No data", CricketLeagueAnalysisException.ExceptionType.NO_DATA);
        Comparator<CricketAnalysisDAO> IPLComparator = Comparator.comparing(census -> census.strikeRate);
        ArrayList leagueDTO = leagueList.stream()
                .sorted(IPLComparator)
                .map(censusDAO -> censusDAO.getIplLeagueDTOS(cricketType))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateCensusJson = new Gson().toJson(leagueDTO);
        return sortedStateCensusJson;
    }

    /**
     * method to sort maximum six
     * @return
     * @throws CricketLeagueAnalysisException
     */
 public String maximumSix() throws CricketLeagueAnalysisException{
     if (leagueList == null || leagueList.size() == 0)
         throw new CricketLeagueAnalysisException("No data", CricketLeagueAnalysisException.ExceptionType.NO_DATA);
     Comparator<CricketAnalysisDAO> IPLComparator = Comparator.comparing(league -> league.six);
     ArrayList leagueDTO = leagueList.stream()
             .sorted(IPLComparator)
             .map(censusDAO -> censusDAO.getIplLeagueDTOS(cricketType))
             .collect(Collectors.toCollection(ArrayList::new));
     String sortedStateCensusJson = new Gson().toJson(leagueDTO);
     return sortedStateCensusJson;
 }

    /**
     * method to sort maximum four
     * @return
     * @throws CricketLeagueAnalysisException
     */
    public String maximumFour() throws CricketLeagueAnalysisException{
        if (leagueList == null || leagueList.size() == 0)
            throw new CricketLeagueAnalysisException("No data", CricketLeagueAnalysisException.ExceptionType.NO_DATA);
        Comparator<CricketAnalysisDAO> IPLComparator = Comparator.comparing(census -> census.four);
        ArrayList leagueDTO = leagueList.stream()
                .sorted(IPLComparator)
                .map(censusDAO -> censusDAO.getIplLeagueDTOS(cricketType))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateCensusJson = new Gson().toJson(leagueDTO);
        return sortedStateCensusJson;
    }
}