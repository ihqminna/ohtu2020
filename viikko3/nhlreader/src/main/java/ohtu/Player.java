
package ohtu;

public class Player {
    private String name;
    private String nationality;
    private int assists;
    private int goals;

    public void setName(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }
    
    public String getNationality(){
        return nationality;
    }
    
    public int getPoints(){
        return (assists + goals);
    }

    @Override
    public String toString() {
        return name + ", " + (assists + goals);
    }
      
}
