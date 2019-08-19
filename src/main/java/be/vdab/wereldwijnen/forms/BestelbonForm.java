package be.vdab.wereldwijnen.forms;

import be.vdab.wereldwijnen.domain.BestelWijze;

import javax.validation.constraints.*;

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
    @NotNull
    @PositiveOrZero
    private byte bestelwijze;

    public BestelbonForm(String naam, String straat, String huisnummer, String gemeente, int postcode, byte bestelwijze) {
        this.naam = naam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.gemeente = gemeente;
        this.postcode = postcode;
        this.bestelwijze = bestelwijze;
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

    public BestelWijze getBestelwijze() {
        //return bestelwijze;
        if (bestelwijze == 0) return BestelWijze.AFHALEN;
        else return BestelWijze.OPSTUREN;
    }
}
