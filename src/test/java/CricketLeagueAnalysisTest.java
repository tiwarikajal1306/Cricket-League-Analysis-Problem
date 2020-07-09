import com.bridgelabz.iplanalyser.exception.CricketLeagueAnalysisException;
import com.bridgelabz.iplanalyser.services.CricketLeagueAnalysis;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalysisTest {
    private static final String IPL_BATSMAN_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    @Test
    public void givenIPL2019BatsmanData_shouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
            int numOfRecords = cricketLeagueAnalysis.loadCricketLeagueData(IPL_BATSMAN_DATA);
            Assert.assertEquals(100,numOfRecords);
        } catch (CricketLeagueAnalysisException e) {
        }
    }
}
