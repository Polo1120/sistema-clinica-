
package modelo;


public class Paciente {
    int id;
    String doc;
    String nom;
    String dir;
    String estado;

    public Paciente() {
    }

    public Paciente(int id, String doc, String nom, String dir, String estado) {
        this.id = id;
        this.doc = doc;
        this.nom = nom;
        this.dir = dir;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    
    
}
