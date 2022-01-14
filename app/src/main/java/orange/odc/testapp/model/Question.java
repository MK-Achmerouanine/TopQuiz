package orange.odc.testapp.model;

import java.util.List;

public class Question {
    private String intitule;
    private List<String> choix;
    private int idBonneReponse;

    public Question(String intitule, List<String> choix, int idBonneReponse) {
        this.intitule = intitule;
        this.choix = choix;
        this.idBonneReponse = idBonneReponse;
    }

    public Question() {
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public List<String> getChoix() {
        return choix;
    }

    public void setChoix(List<String> choix) {
        this.choix = choix;
    }

    public int getIdBonneReponse() {
        return idBonneReponse;
    }

    public void setIdBonneReponse(int idBonneReponse) {
        this.idBonneReponse = idBonneReponse;
    }

    @Override
    public String toString() {
        return "Question{" +
                "intitule='" + intitule + '\'' +
                ", choix=" + choix +
                ", idBonneReponse=" + idBonneReponse +
                '}';
    }
}
