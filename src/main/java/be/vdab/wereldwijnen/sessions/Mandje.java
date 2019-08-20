package be.vdab.wereldwijnen.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.*;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Long, Integer> wijnen = new LinkedHashMap<>(); // <id, aantal>

    public void voegToe(long id, int aantal) {
        if (this.bevat(id)) {
            wijnen.put(id, wijnen.get(id) + aantal);
        }
        else wijnen.put(id, aantal);
    }

    public boolean bevat(long id) {
        return wijnen.containsKey(id);
    }

    public boolean isGevuld() {
        return !wijnen.isEmpty();
    }

    public boolean isLeeg() {
        return wijnen.isEmpty();
    }

    public Optional<Integer> getAantal(long id) {
        return Optional.ofNullable(wijnen.get(id));
    }

    public Set<Long> getIds() {
        return wijnen.keySet();
    }

    public void maakLeeg() {
        wijnen.clear();
    }

    public Map<Long, Integer> getWijnen() {
        return Collections.unmodifiableMap(wijnen);
    }

    public void verwijder(long id) {
        wijnen.remove(id);
    }
}
