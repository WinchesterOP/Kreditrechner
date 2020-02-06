package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Tabellarische Ausgabe der Ergebnisse
 * Die ArrayList wird ausgelesen und die Ergebnisse Zeile für Zeile ausgegeben
 */
class GenerateOutputTable {

    static void generateTable(ArrayList<ArrayList<Float>> tabledata, float months, float credit){

        //Bei payDay wird immer der letzte Tag des Monats berechnet
        LocalDate payDay = LocalDate.now();
        payDay = payDay.withDayOfMonth(payDay.lengthOfMonth());

        System.out.println("Datum \t\tRestschuld \t\tZinsen \t\tTilgung/Auszahlung \t\tRate");
        System.out.println("" + payDay + " \t\t0,00€ \t\t-" + String.format("%.2f",credit) + " \t\t-" + String.format("%.2f",credit) + "");

        for (int i=0 ; i<(int)months ; i++){
            payDay = payDay.plusMonths(1);
            payDay = payDay.withDayOfMonth(payDay.lengthOfMonth());

            System.out.println(payDay
                    + "\t\t-" + String.format("%.2f",tabledata.get(i).get(0))
                    + "\t\t " + String.format("%.2f",tabledata.get(i).get(1))
                    + "\t\t " + String.format("%.2f",tabledata.get(i).get(2))
                    + "\t\t " + String.format("%.2f",tabledata.get(i).get(3))
            );
        }
    }
}
