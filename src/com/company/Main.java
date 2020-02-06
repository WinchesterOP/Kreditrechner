package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {



    private static void generateTable(float[] data){
        float credit = data[0];
        float interest = data[1];
        float repayment = data[2];
        float months = data[3] * 12;
        float monthlyRate = new RoundNumber(((credit*interest)/(float)1200 + (credit*repayment)/(float)1200));
        /* Die Formel für "monthlyRate" wurde aus folgender Rechnung umgesetzt:
         *      Formel-Zinsen = ( ( ( Kredit / 100 ) * Sollzinsen ) / 12 )
         *      Formel-Tilgung =  ( ( ( Kredit / 100 ) * Tilgungsprozent ) / 12 )
         */
        String monthlyRateString = String.format("%.2f",monthlyRate);
        float outstanding = credit;
        float sumInterest = 0;
        float sumRepayment = 0;
        LocalDate payDay = LocalDate.now();
        payDay = payDay.withDayOfMonth(payDay.lengthOfMonth());

        System.out.println("Datum \t\tRestschuld \t\tZinsen \t\tTilgung/Auszahlung \t\tRate");
        System.out.println("" + payDay + " \t\t0,00€ \t\t-" + String.format("%.2f",credit) + " \t\t-" + String.format("%.2f",credit) + "");

        for (int i=0 ; i<(int)months ; i++){
            float interestRate = round ((outstanding*interest)/1200);
            sumInterest += interestRate;
            float repaymentRate = monthlyRate - interestRate;
            sumRepayment += repaymentRate;
            outstanding -= repaymentRate;
            payDay = payDay.plusMonths(1);
            payDay = payDay.withDayOfMonth(payDay.lengthOfMonth());

            System.out.println(payDay
                    + "\t\t-" + String.format("%.2f",outstanding)
                    + "\t\t " + String.format("%.2f",interestRate)
                    + "\t\t " + String.format("%.2f",repaymentRate)
                    + "\t\t " + monthlyRateString
            );
        }

        System.out.println(payDay
                + "\t\t-" + String.format("%.2f",outstanding)
                + "\t\t " + String.format("%.2f",sumInterest)
                + "\t\t " + String.format("%.2f",sumRepayment)
                + "\t\t " + String.format("%.2f",monthlyRate*months)
        );
    }

    public static void main(String[] args){
        generateTable(getData());
    }


}
