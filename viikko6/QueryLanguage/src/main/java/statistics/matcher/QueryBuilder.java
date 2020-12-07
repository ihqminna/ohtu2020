package statistics.matcher;

public class QueryBuilder{
    Matcher matcher;
    
    public QueryBuilder(){
        matcher = new All();
    }
    
    public Matcher build (){
        return matcher;
    }
    
    public QueryBuilder playsIn(String playsIn){
        matcher = new And(matcher, new PlaysIn(playsIn));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category){
        matcher = new And(matcher, new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category){
        matcher = new And(matcher, new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher matcher1, Matcher matcher2){
        matcher = new And(new Or(matcher1, matcher2));
        return this;
    }
}
