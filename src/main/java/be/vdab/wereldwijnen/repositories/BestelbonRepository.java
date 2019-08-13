package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.forms.BestelbonForm;
import be.vdab.wereldwijnen.sessions.Mandje;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface BestelbonRepository {
    long create(BestelbonForm bestelbonFrom, Mandje mandje);
}