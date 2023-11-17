/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.AutenticacionDAO;
import modelo.pojo.RespuestaLogin;

@Path("autenticacion")
public class AutenticacionWS {

    @Context
    private UriInfo context;

    public AutenticacionWS() {
    }

    @POST
    @Path("loginInseguro")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLogin loginEscritorioInseguro(@FormParam("nombreUsuario") String nombreUsuario, @FormParam("contraseña") String contraseña) {
        RespuestaLogin respuesta = null;
        if (nombreUsuario!= null && !nombreUsuario.isEmpty() && contraseña!=null && !contraseña.isEmpty()){
            respuesta = AutenticacionDAO.verificarInicioSesionNoSeguro(nombreUsuario, contraseña);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return respuesta;
    }
    
    @POST
    @Path("loginSeguro")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLogin loginEscritorioSeguro(@FormParam("nombreUsuario") String nombreUsuario, @FormParam("contraseña") String contraseña) {
        RespuestaLogin respuesta = null;
        if (nombreUsuario!= null && !nombreUsuario.isEmpty() && contraseña!=null && !contraseña.isEmpty()){
            respuesta = AutenticacionDAO.verificarInicioSesionSeguro(nombreUsuario, contraseña);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return respuesta;
    }
    
    @POST
    @Path("cambiarContraseña")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLogin cambiarContraseña(@FormParam("nombreUsuario") String nombreUsuario, @FormParam("contraseña") String contraseña) {
        RespuestaLogin respuesta = null;
        if (nombreUsuario!= null && !nombreUsuario.isEmpty() && contraseña!=null && !contraseña.isEmpty()){
            respuesta = AutenticacionDAO.cambiarContraseña(nombreUsuario, contraseña);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return respuesta;
    }

}
