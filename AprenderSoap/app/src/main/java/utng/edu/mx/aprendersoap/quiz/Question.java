package utng.edu.mx.aprendersoap.quiz;

/**
 * Created by dano on 29/03/16.
 */
public class Question {
    private int id;
    private String pregunta;
    private String opcionA;
    private String opcionB;
    private String opcionC;
    private String respuesta;


    public Question() {
        id= 0;
        pregunta="";
        opcionA="";
        opcionB="";
        opcionC="";
        respuesta="";
    }

    public Question(int id, String pregunta, String opcionA, String opcionB, String opcionC, String respuesta) {
        this.id = id;
        this.pregunta = pregunta;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.respuesta = respuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpcionA() {
        return opcionA;
    }

    public void setOpcionA(String opcionA) {
        this.opcionA = opcionA;
    }

    public String getOpcionB() {
        return opcionB;
    }

    public void setOpcionB(String opcionB) {
        this.opcionB = opcionB;
    }

    public String getOpcionC() {
        return opcionC;
    }

    public void setOpcionC(String opcionC) {
        this.opcionC = opcionC;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
