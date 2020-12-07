
package statistics.matcher;

import statistics.Player;

public class Or implements Matcher{
    private Matcher[] matcherTable;

    public Or (Matcher... matchers) {
        this.matcherTable = matchers;
    }

    @Override
    public boolean matches(Player p) {
        for(Matcher matcher : matcherTable) {
            if(matcher.matches(p)) {
                return true;
            }
        }
        return false;
    }
}
