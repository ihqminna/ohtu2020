
package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {
    private Matcher matcher;

    public Not(HasAtLeast hasAtLeast) {
        this.matcher = hasAtLeast;
    }

    @Override
    public boolean matches(Player p) {
        if (matcher.matches(p)) {
            return false;
        }
        return true;
    }
    
    
}
