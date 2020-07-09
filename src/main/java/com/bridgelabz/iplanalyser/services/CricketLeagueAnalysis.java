package com.bridgelabz.iplanalyser.services;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalysis {
    Map<String, CricketAnalysisDAO> iplAnalysisMap = null;

    public CricketLeagueAnalysis() {
        iplAnalysisMap = new HashMap<>();
    }

    public int loadCricketLeagueData(String csvFilePath) throws CricketLeagueAnalysisException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<MostRunCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, MostRunCSV.class);
            Iterable<MostRunCSV> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(),false).
                    forEach(iplDataCsv -> iplAnalysisMap.put(iplDataCsv.player,new CricketAnalysisDAO(iplDataCsv)));

            if(iplAnalysisMap.size() == 0)
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
}
