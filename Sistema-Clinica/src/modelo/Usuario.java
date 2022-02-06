
package modelo;


public class Usuario {
   int idUsuario;
   String nickUsuario;
   String passUsuario;
   String tipoUsuario;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nickUsuario, String passUsuario, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nickUsuario = nickUsuario;
        this.passUsuario = passUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
   
}
