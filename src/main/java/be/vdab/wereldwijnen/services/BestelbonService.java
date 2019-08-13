package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.forms.BestelbonForm;
import be.vdab.wereldwijnen.sessions.Mandje;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface BestelbonService {
    long create(BestelbonForm bestelbonForm, Mandje mandje);
}