package com.bridgelabz.iplanalyser.services;

import com.bridgelabz.iplanalyser.dao.CricketAnalysisDAO;
import com.bridgelabz.iplanalyser.exception.CricketLeagueAnalysisException;
import com.bridgelabz.iplanalyser.model.MostRunCSV;
import com.google.gson.Gson;
import com.opencsv.CSVBuilderException;
import com.opencsv.CSVBuilderFactory;
import com.opencsv.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalysis {
    Map<String, CricketAnalysisDAO> iplAnalysisMap;
    List<CricketAnalysisDAO> leagueList = new ArrayList<>();

    public CricketLeagueAnalysis() {

        iplAnalysisMap = new HashMap<>();
    }

    enum Cricket {
        BATTING
    }

    public int loadCricketLeagueData(String csvFilePath) throws CricketLeagueAnalysisException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<MostRunCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, MostRunCSV.class);
            Iterable<MostRunCSV> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false).
                    forEach(iplDataCsv -> iplAnalysisMap.put(iplDataCsv.player, new CricketAnalysisDAO(iplDataCsv)));
            leagueList = iplAnalysisMap.values().stream().collect(Collectors.toList());
            if (iplAnalysisMap.size() == 0)
                throw new CricketLeagueAnalysisException("NO_DATA",
                        CricketLeagueAnalysisException.ExceptionType.NO_DATA);
            return this.iplAnalysisMap.size();
        } catch (IOException e) {
            throw new CricketLeagueAnalysisException(e.getMessage(),
                    CricketLeagueAnalysisException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CricketLeagueAnalysisException(e.getMessage(),
                    CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE);
        } catch (CSVBuilderException e) {
            throw new CricketLeagueAnalysisException(e.getMessage(),
                    CricketLeagueAnalysisException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }

    //top batting avg of the cricketers
    public String topBattingAverage() throws CricketLeagueAnalysisException {
        if (leagueList == null || leagueList.size() == 0)
            throw new CricketLeagueAnalysisException("No data", CricketLeagueAnalysisException.ExceptionType.NO_DATA);
        Comparator<CricketAnalysisDAO> IPLComparator = Comparator.comparing(census -> census.average);
        leagueList.sort(IPLComparator);
        String sortedStateCensusJson = new Gson().toJson(leagueList);
        return sortedStateCensusJson;
    }
    //top StrikeRate player
    public String topStrikeRate() throws CricketLeagueAnalysisException {
        if (leagueList == null || leagueList.size() == 0)
            throw new CricketLeagueAnalysisException("No data", CricketLeagueAnalysisException.ExceptionType.NO_DATA);
        Comparator<CricketAnalysisDAO> IPLComparator = Comparator.comparing(census -> census.strikeRate);
        leagueList.sort(IPLComparator);
        String sortedStateCensusJson = new Gson().toJson(leagueList);
        return sortedStateCensusJson;
    }
}