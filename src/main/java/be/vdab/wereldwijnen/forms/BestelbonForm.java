package be.vdab.wereldwijnen.forms;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class BestelbonForm {
    @NotBlank
    private String naam;
    @NotBlank
    private String straat;
    @NotBlank
    private String huisnummer;
    @NotBlank
    private String gemeente;
    @Min(1000)
    @Max(9999)
    private int postcode;
    private boolean afhalen;
    private boolean opsturen;

    public BestelbonForm(String naam, String straat, String huisnummer, String gemeente, int postcode) {
        this.naam = naam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }

    public String getNaam() {
        return naam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getGemeente() {
        return gemeente;
    }

    public int getPostcode() {
        return postcode;
    }
}
