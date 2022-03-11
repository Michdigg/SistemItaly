package com.sistemitaly.Error;

/**
 * Eccezione generata al verificarsi di un KO
 */
public class KOException extends Exception{
    public KOException() { System.err.println("KO"); }
}
