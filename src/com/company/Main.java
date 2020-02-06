package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static float checkInput(Scanner reader) throws Exception{
        try {
            return reader.nextFloat();
        } catch (InputMismatchException ime) {
            reader.next(); // leeren des Readers
            throw new Exception("Eingabetyp war fehlerhaft. Bitte nocheinmal eingeben");

        }
    }

    private static float readInput(){
        boolean wrongInput = true;
        float inputData = 0;
        Scanner reader = new Scanner(System.in);

        do {
            try {
                inputData = checkInput(reader);
                wrongInput = false;
                reader.nextLine();
            } catch (Exception e) {
                System.out.println("Eingabe war fehlerhaft. Bitte nocheinmal eingeben");
            }
        } while(wrongInput);

        return inputData;
    }

    private static float[] getData(){
        float[] data = new float[4]; // 0=credit 1=interest 2=repayment 3=
        boolean retry;

        System.out.println("Kredithöhe angeben: ");
        do{
            data[0]=readInput();
            if(data[0] < 0 ){
                System.out.println("Negativkredit nicht möglich");
                retry = true;
            } else if (data[0] > 1000000000) {
                System.out.println("Ein so hoher Kredit ist nicht möglich");
                retry = true;
            } else retry = false;
        } while (retry);

        System.out.println("Sollzins angeben: ");
        do{
            data[1]=readInput();
            if(data[1] < 0 ){
                System.out.println("Negativzinsen sind nicht möglich");
                retry = true;
            } else if (data[1] > 10) {
                System.out.println("Zu hoher Sollzinssatz");
                retry = true;
            } else retry = false;
        } while (retry);

        System.out.println("anfängliche Tilgung in Prozent angeben: ");
        do{
            data[2]=readInput();
            if(data[2] < 0 ){
                System.out.println("Negativtilgung nicht möglich");
                retry = true;
            } else if (data[2] > 100) {
                System.out.println("Mehr als 100 Prozent nicht möglich");
                retry = true;
            } else retry = false;
        } while (retry);

        System.out.println("Zinsbindung in Jahren angeben: ");
        do{
            data[3]=readInput();
            if(data[3] < 0 ){
                System.out.println("weniger als 1 Jahr nicht möglich");
                retry = true;
            } else if (data[3] > 40) {
                System.out.println("Mehr als 40 Jahre nicht möglich");
                retry = true;
            } else retry = false;
        } while (retry);

        return data;
    }

    //die Zahlen auf 2 Kommastellen runden da zu lange Nachkommastellen das Ergebnis verfälschen
    private static float round(float number) {
        BigDecimal bd = new BigDecimal(Double.toString(number));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    private static void generateTable(float[] data){
        float credit = data[0];
        float interest = data[1];
        float repayment = data[2];
        float months = data[3] * 12;
        float monthlyRate = round((credit*interest)/(float)1200 + (credit*repayment)/(float)1200);
        /* Die Formel für "monthlyRate" wurde aus folgender Rechnung umgesetzt:
            Formel-Zinsen = ( ( ( Kredit / 100 ) * Sollzinsen ) / 12 )
            Formel-Tilgung =  ( ( ( Kredit / 100 ) * Tilgungsprozent ) / 12 )
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
