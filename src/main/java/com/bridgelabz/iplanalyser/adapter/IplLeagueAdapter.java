package com.bridgelabz.iplanalyser.adapter;

import com.bridgelabz.iplanalyser.dao.CricketAnalysisDAO;
import com.bridgelabz.iplanalyser.exception.CricketLeagueAnalysisException;
import com.bridgelabz.iplanalyser.model.MostRunCSV;
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

public abstract class IplLeagueAdapter {
    public abstract List<CricketAnalysisDAO>  loadCricketLeagueData(String... csvFilePath) throws CricketLeagueAnalysisException;

    public <E> List<CricketAnalysisDAO> loadCricketLeagueData(Class<E> classType, String csvFilePath) throws CricketLeagueAnalysisException {

        Map<String, CricketAnalysisDAO> iplAnalysisMap = new HashMap<>();
        List<CricketAnalysisDAO> leagueList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader,classType);
            Iterable<E> csvIterable = () -> csvFileIterator;
            switch (classType.getSimpleName()){
                case "MostRunCSV":
                    StreamSupport.stream(csvIterable.spliterator(), false)
                            .map(MostRunCSV.class::cast)
                            .forEach(cricketCSV -> iplAnalysisMap.put(cricketCSV.player, new CricketAnalysisDAO(cricketCSV)));
                    leagueList = iplAnalysisMap.values().stream().collect(Collectors.toList());
                    break;
            }
            if (iplAnalysisMap.size() == 0)
                throw new CricketLeagueAnalysisException("NO_DATA",
                        CricketLeagueAnalysisException.ExceptionType.NO_DATA);
            return leagueList;
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
}
