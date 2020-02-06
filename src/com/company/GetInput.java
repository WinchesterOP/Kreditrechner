package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * In dieser Klasse wird die komplette Eingabe der Parameter abgearbeitet.
 * Eingaben werden sooft wiederholt bis sie erfolgreich eingegeben wurden.
 * Werte die keine Zahlen sind werden nicht akzeptiert.
 */
class GetInput {
    /*
     * Für jeden Parameter wird ein kleiner Check ob sie die Zahlen in logischen Bereichen befinden durchgeführt.
     * Ansonsten ist es nur ein klassisches einlesen mit dem Scanner
     */
    static void getInput(){
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

        Calculate.calculate(data);
    }


    /*
     * liest den nächsten Input von Zahlen ein
     */
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

    /*
     * Überprüft ob der nächste Input eine Zahl ist. Wenn es keine Zahl ist wird eine Exception geschmissen
     */
    private static float checkInput(Scanner reader) throws Exception{
        try {
            return reader.nextFloat();
        } catch (InputMismatchException ime) {
            reader.next(); // leeren des Readers
            throw new Exception("Eingabetyp war fehlerhaft. Bitte nocheinmal eingeben");

        }
    }
}
