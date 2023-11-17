package modelo.pojo;

public class RespuestaLogin {
    private Boolean error;
    private String mensaje;

    public RespuestaLogin() {
    }

    public RespuestaLogin(Boolean error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
