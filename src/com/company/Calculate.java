package com.company;

import java.util.ArrayList;

/**
 * Hier wird die Berechnung durchgeführt.
 * die Ergebnisse werden in eine 2-Dimensionale ArrayList hinzugefügt damit sie später tabellarisch ausgegeben werden können.
 */
class Calculate {

    static void calculate(float[] data){
        ArrayList<ArrayList<Float>> tableOfResults = new ArrayList<>();
        float credit = data[0];
        float interest = data[1];
        float repayment = data[2];
        float months = data[3] * 12;
        float monthlyRate = RoundNumber.round((credit*interest)/(float)1200 + (credit*repayment)/(float)1200);
        /* Die Formel für "monthlyRate" wurde aus folgender Rechnung umgesetzt:
         *      Formel-Zinsen = ( ( ( Kredit / 100 ) * Sollzinsen ) / 12 )
         *      Formel-Tilgung =  ( ( ( Kredit / 100 ) * Tilgungsprozent ) / 12 )
         */
        float outstanding = credit;

        for (int i=0 ; i<(int)months ; i++){
            tableOfResults.add(new ArrayList<>());

            float interestRate = RoundNumber.round((outstanding*interest)/1200);
            float repaymentRate = monthlyRate - interestRate;
            outstanding -= repaymentRate;

            tableOfResults.get(i).add(outstanding);
            tableOfResults.get(i).add(interestRate);
            tableOfResults.get(i).add(repaymentRate);
            tableOfResults.get(i).add(monthlyRate);
        }
        GenerateOutputTable.generateTable(tableOfResults,months,credit);
    }
}
