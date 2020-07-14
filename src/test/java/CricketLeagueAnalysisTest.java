import com.bridgelabz.iplanalyser.exception.CricketLeagueAnalysisException;
import com.bridgelabz.iplanalyser.model.MostRunCSV;
import com.bridgelabz.iplanalyser.services.CricketLeagueAnalysis;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CricketLeagueAnalysisTest {
    private static final String IPL_BATSMAN_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BOWLING_DATA = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
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
            MostRunCSV[] mostRunCSV = new Gson()
                    .fromJson(sortCensusData, MostRunCSV[].class);
            Assert.assertEquals("MS Dhoni", mostRunCSV[mostRunCSV.length-1].player);
            Assert.assertEquals(83.2, mostRunCSV[mostRunCSV.length-1].average,0.0);
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
            Assert.assertEquals(333.33, indiaCensusCSV[indiaCensusCSV.length-1].strikeRate,0.0);
        } catch (CricketLeagueAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCensusCSVFile_WhenSorted_Should_ReturnMaximumSixAndFour() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.CricketType.BATTING);
            cricketLeagueAnalysis.loadCricketLeagueData(IPL_BATSMAN_DATA);
            String sortLeagueData6  = cricketLeagueAnalysis.maximumSix();
            String sortLeagueData4  = cricketLeagueAnalysis.maximumFour();
            MostRunCSV[] mostRunCSVS = new Gson()
                    .fromJson(sortLeagueData6, MostRunCSV[].class);
            Assert.assertEquals("Andre Russell", mostRunCSVS[mostRunCSVS.length-1].player);
            Assert.assertEquals(52, mostRunCSVS[mostRunCSVS.length-1].six);

            MostRunCSV[] mostRunCSVS1 = new Gson()
                    .fromJson(sortLeagueData4, MostRunCSV[].class);
            Assert.assertEquals("Shikhar Dhawan", mostRunCSVS1[mostRunCSVS1.length-1].player);
            Assert.assertEquals(64, mostRunCSVS1[mostRunCSVS1.length-1].four);
        } catch (CricketLeagueAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCensusCSVFile_WhenSorted_Should_ReturnGreatAverages_WithBestStrikingRate() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.CricketType.BATTING);
            cricketLeagueAnalysis.loadCricketLeagueData(IPL_BATSMAN_DATA);
            String sortCensusData  = cricketLeagueAnalysis.topBattingAverage();
            MostRunCSV[] mostRunCSV = new Gson()
                    .fromJson(sortCensusData, MostRunCSV[].class);
            Assert.assertEquals("MS Dhoni", mostRunCSV[mostRunCSV.length-1].player);
            Assert.assertEquals(134.62, mostRunCSV[mostRunCSV.length-1].strikeRate,0.0);
        } catch (CricketLeagueAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCensusCSVFile_WhenSorted_Should_ReturnMaximumRun_WithBestStrikingRate() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.CricketType.BATTING);
            cricketLeagueAnalysis.loadCricketLeagueData(IPL_BATSMAN_DATA);
            String sortCensusData = cricketLeagueAnalysis.maximumRun();
            MostRunCSV[] mostRunCSV = new Gson()
                    .fromJson(sortCensusData, MostRunCSV[].class);
            Assert.assertEquals(692.0, mostRunCSV[mostRunCSV.length - 1].runs, 0.0);
            Assert.assertEquals(143.86, mostRunCSV[mostRunCSV.length - 1].strikeRate, 0.0);
            Assert.assertEquals("David Warner", mostRunCSV[mostRunCSV.length - 1].player);
        } catch (CricketLeagueAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019BowlingData_shouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.CricketType.BOWLING);
            List numOfRecords = cricketLeagueAnalysis.loadCricketLeagueData(IPL_BOWLING_DATA);
            Assert.assertEquals(99,numOfRecords.size());
        } catch (CricketLeagueAnalysisException e) {
            e.printStackTrace();
        }
    }
}
