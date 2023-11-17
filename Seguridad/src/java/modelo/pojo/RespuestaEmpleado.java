package modelo.pojo;

import java.util.List;

public class RespuestaEmpleado { 
    private Boolean error;
    private String mensaje;
    private List<Empleado> empleado;

    public RespuestaEmpleado() {
    }

    public RespuestaEmpleado(Boolean error, String mensaje, List<Empleado> empleado) {
        this.error = error;
        this.mensaje = mensaje;
        this.empleado = empleado;
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

    public List<Empleado> getEmpleado() {
        return empleado;
    }

    public void setEmpleado(List<Empleado> empleado) {
        this.empleado = empleado;
    }
    
    
}
