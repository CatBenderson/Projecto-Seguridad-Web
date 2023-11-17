/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.List;
import modelo.pojo.Administrador;
import modelo.pojo.RespuestaLogin;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author ameya
 */
public class AutenticacionDAO {

    public static RespuestaLogin verificarInicioSesionNoSeguro(String nombreUsuario, String contraseña) {
        RespuestaLogin respuesta = new RespuestaLogin();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                Administrador administrador = new Administrador();
                administrador.setNombreUsuario(nombreUsuario);
                administrador.setContraseña(contraseña);
                List<Administrador> administradorObtenido = conexionBD.selectList("autenticacion.autenticacionInsegura", administrador);
                if (administradorObtenido.size() > 0) {

                    respuesta.setError(false);
                    respuesta.setMensaje("Bienvenido(a) al sistema");
                } else {
                    respuesta.setMensaje("Número de personal y/o contraseña incorrectas, favor de intentar nuevamente");
                }
            } catch (Exception e) {
                respuesta.setMensaje("Error: " + e.getMessage());
            }
        } else {
            respuesta.setMensaje("Error de conexión con la base de datos.");
        }
        return respuesta;
    }
    
    public static RespuestaLogin verificarInicioSesionSeguro(String nombreUsuario, String contraseña) {
        RespuestaLogin respuesta = new RespuestaLogin();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                Administrador administrador = new Administrador();
                administrador.setNombreUsuario(nombreUsuario);
                administrador.setContraseña(contraseña);
                List<Administrador> administradorObtenido = conexionBD.selectList("autenticacion.autenticacionSegura", administrador);
                if (administradorObtenido.size() > 0) {

                    respuesta.setError(false);
                    respuesta.setMensaje("Bienvenido(a) al sistema");
                } else {
                    respuesta.setMensaje("Número de personal y/o contraseña incorrectas, favor de intentar nuevamente");
                }
            } catch (Exception e) {
                respuesta.setMensaje("Error: " + e.getMessage());
            }
        } else {
            respuesta.setMensaje("Error de conexión con la base de datos.");
        }
        return respuesta;
    }

    public static RespuestaLogin cambiarContraseña(String nombreUsuario, String contraseña) {
        RespuestaLogin respuesta = new RespuestaLogin();

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                Administrador administrador = new Administrador();
                administrador.setNombreUsuario(nombreUsuario);
                administrador.setContraseña(contraseña);
                int numFilasAfectadas = conexionBD.update("autenticacion.actualizarAdmin", administrador);
                conexionBD.commit();

                System.out.println(numFilasAfectadas);
                if (numFilasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Éxito");
                } else {
                    respuesta.setMensaje("Ocurrio un error al modificar los datos");
                }

            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setMensaje("Error al conectar con la base de datos");
        }

        return respuesta;
    }
}
