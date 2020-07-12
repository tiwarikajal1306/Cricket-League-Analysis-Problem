import com.bridgelabz.iplanalyser.exception.CricketLeagueAnalysisException;
import com.bridgelabz.iplanalyser.model.MostRunCSV;
import com.bridgelabz.iplanalyser.services.CricketLeagueAnalysis;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CricketLeagueAnalysisTest {
    private static final String IPL_BATSMAN_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    @Test
    public void givenIPL2019BatsmanData_shouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.CricketType.BATTING);
            List numOfRecords = cricketLeagueAnalysis.loadCricketLeagueData(IPL_BATSMAN_DATA);
            Assert.assertEquals(100,numOfRecords.size());
        } catch (CricketLeagueAnalysisException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLCensusCSVFile_WhenSorted_Should_ReturnTopBattingAvgPlayer() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.CricketType.BATTING);
            cricketLeagueAnalysis.loadCricketLeagueData(IPL_BATSMAN_DATA);
            String sortCensusData  = cricketLeagueAnalysis.topBattingAverage();
            MostRunCSV[] indiaCensusCSV = new Gson()
                    .fromJson(sortCensusData, MostRunCSV[].class);
            Assert.assertEquals("MS Dhoni", indiaCensusCSV[indiaCensusCSV.length-1].player);
        } catch (CricketLeagueAnalysisException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLCensusCSVFile_WhenSorted_Should_ReturnTopStrikeRatePlayer() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.CricketType.BATTING);
            cricketLeagueAnalysis.loadCricketLeagueData(IPL_BATSMAN_DATA);
            String sortCensusData  = cricketLeagueAnalysis.topStrikeRate();
            MostRunCSV[] indiaCensusCSV = new Gson()
                    .fromJson(sortCensusData, MostRunCSV[].class);
            Assert.assertEquals("Ishant Sharma", indiaCensusCSV[indiaCensusCSV.length-1].player);
        } catch (CricketLeagueAnalysisException e) {
            e.printStackTrace();
        }
    }
}
