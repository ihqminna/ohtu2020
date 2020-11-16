
package ohtu;

public class Player {
    private String name;
    private String nationality;

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

    @Override
    public String toString() {
        return name;
    }
      
}
