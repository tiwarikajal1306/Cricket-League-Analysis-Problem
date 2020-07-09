package com.bridgelabz.iplanalyser.exception;

public class CricketLeagueAnalysisException extends Exception {

    public enum ExceptionType {
        IPL_FILE_PROBLEM,UNABLE_TO_PARSE, NO_DATA
    }

   public ExceptionType type;

    public CricketLeagueAnalysisException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}