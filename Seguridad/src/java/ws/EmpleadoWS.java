/*//
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.EmpleadoDAO;
import modelo.pojo.Empleado;
import modelo.pojo.RespuestaEmpleado;

@Path("empleado")
public class EmpleadoWS {

    @Context
    private UriInfo contex;

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RespuestaEmpleado registrarEmpleado(String jsonParam) {
        RespuestaEmpleado msj = null;
        try {
            Gson gson = new Gson();
            Empleado empleado = gson.fromJson(jsonParam, Empleado.class);
            if (empleado != null && empleado.getNumeroEmpleado() != null && empleado.getNumeroEmpleado() > 0) {
                msj = EmpleadoDAO.registrarEmpleado(empleado);
            } else {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        } catch (Exception e) {
            msj.setError(true);
            msj.setMensaje("Error: " + e.getMessage());
        }
        return msj;
    }

    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RespuestaEmpleado editarEmpleado(String jsonParam) {
        RespuestaEmpleado msj = null;
        try {
            Gson gson = new Gson();
            Empleado empleado = gson.fromJson(jsonParam, Empleado.class);
            if (empleado != null && empleado.getNumeroEmpleado() != null && empleado.getNumeroEmpleado() > 0) {
                msj = EmpleadoDAO.modificarEmpleado(empleado);
            } else {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        } catch (Exception e) {
            msj.setError(true);
            msj.setMensaje("Error: " + e.getMessage());
        }
        return msj;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("obtenerEmpleados")
    public RespuestaEmpleado obtenerEmpleadoPaciente() {
        return EmpleadoDAO.obtenerEmpleados();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("obtenerEmpleado/{numeroEmpleado}")
    public RespuestaEmpleado obtenerEmpleadoPorNumeroPersonal(@PathParam("numeroEmpleado") Integer numeroEmpleado) {
        if (numeroEmpleado != null && numeroEmpleado > 0) {
            return EmpleadoDAO.obtenerEmpleadoPorNumeroEmpleado(numeroEmpleado);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("eliminarEmpleado/{numeroEmpleado}")
    public RespuestaEmpleado eliminarEmpleadoPorNumeroPersonal(@PathParam("numeroEmpleado") Integer numeroEmpleado) {
        if (numeroEmpleado != null && numeroEmpleado > 0) {
            return EmpleadoDAO.eliminarEmpleado(numeroEmpleado);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
