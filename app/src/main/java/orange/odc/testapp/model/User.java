package orange.odc.testapp.model;

public class User extends Object{
    private String nomComplet;
    // [Constructeur] new User('Admin')
    public User(String nom){
        this.nomComplet = nom;
    }
    // [Getter]
    public String getNomComplet() {
        return nomComplet;
    }
    // [Setter]
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    @Override
    public String toString() {
        return "User{" +
                "nomComplet='" + nomComplet + '\'' +
                '}';
    }
}
