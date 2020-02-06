package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Diese Klasse wird dazu verwendet die Werte auf 2 Stellen hinter dem Komma zu runden.
 * Wird dies bei der Berechnung von den Zinsen nicht getan kann es zu Schiefständen kommen durch die Nachkommastellen.
 *
 * Die Float-Variable wird in ein BigDecimal umgewandelt damit sie auf Base10 statt Base2 berechnet wird.
 * Nachdem die Nachkommstellen entfernt wurden wird sie als Float-Wert zurückgegeben.
 */
class RoundNumber {

    private static final int DECIMAL_PLACES = 2; //Nachkommastellen

    static float round(float number){
        BigDecimal bd = new BigDecimal(Double.toString(number));
        bd = bd.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}
