package com.yanis.scholar.Control;

/**
 *
 * @author Yanis OULHACI
 */
public class CheckApostrophe {

    public CheckApostrophe() {
    }
    public String apostropheConfiguration(String string) {
        String[] split = string.split("'");
        string = split[0];
        for(int i=1; i < split.length; i++) {
            string = string + "’" + split[i];
        }
        return string;
    }
}
