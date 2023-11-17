package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.pojo.Empleado;
import modelo.pojo.RespuestaEmpleado;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class EmpleadoDAO {

    public static RespuestaEmpleado obtenerEmpleados() {
        RespuestaEmpleado respuesta = new RespuestaEmpleado();

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                List<Empleado> empleados = conexionBD.selectList("empleados.obtenerEmpleados");

                if (empleados.size() > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Éxito");
                    respuesta.setEmpleado(empleados);
                } else {
                    respuesta.setMensaje("Ocurrio un error al obtener los datos");
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

    public static RespuestaEmpleado obtenerEmpleadoPorId(Integer idEmpleado) {
        RespuestaEmpleado respuesta = new RespuestaEmpleado();

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                List<Empleado> empleados = new ArrayList<>();
                empleados.add(conexionBD.selectOne("empleados.obtenerEmpleadoPorId", idEmpleado));

                if (empleados.size() > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Éxito");
                    respuesta.setEmpleado(empleados);
                } else {
                    respuesta.setMensaje("Ocurrio un error al obtener los datos");
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

    public static RespuestaEmpleado obtenerEmpleadoPorNumeroEmpleado(Integer numeroEmpleado) {
        RespuestaEmpleado respuesta = new RespuestaEmpleado();

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                List<Empleado> empleados = new ArrayList<>();
                empleados.add(conexionBD.selectOne("empleados.obtenerEmpleadoPorNumeroEmpleado", numeroEmpleado));

                if (empleados.size() > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Éxito");
                    respuesta.setEmpleado(empleados);
                } else {
                    respuesta.setMensaje("Ocurrio un error al obtener los datos");
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

    public static RespuestaEmpleado registrarEmpleado(Empleado empleado) {
        RespuestaEmpleado respuesta = new RespuestaEmpleado();

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.insert("empleados.registrarEmpleado", empleado);
                conexionBD.commit();

                if (numFilasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Éxito");
                } else {
                    respuesta.setMensaje("Ocurrio un error al registrar los datos");
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

    public static RespuestaEmpleado modificarEmpleado(Empleado empleado) {
        RespuestaEmpleado respuesta = new RespuestaEmpleado();

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                Empleado empleadoExiste = conexionBD.selectOne("empleados.obtenerEmpleadoPorNumeroEmpleado", empleado.getNumeroEmpleado());
                if (empleadoExiste != null) {
                    int numFilasAfectadas = conexionBD.update("empleados.modificarEmpleado", empleado);
                    conexionBD.commit();

                    System.out.println(numFilasAfectadas);
                    if (numFilasAfectadas > 0) {
                        respuesta.setError(false);
                        respuesta.setMensaje("Éxito");
                    } else {
                        respuesta.setMensaje("Ocurrio un error al modificar los datos");
                    }
                } else {
                    respuesta.setMensaje("Ocurrio un error al obtener los datos");
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

    public static RespuestaEmpleado eliminarEmpleado(Integer numeroEmpleado) {
        RespuestaEmpleado respuesta = new RespuestaEmpleado();

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.delete("empleados.eliminarEmpleadoPorNumeroEmpleado", numeroEmpleado);
                conexionBD.commit();

                if (numFilasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Éxito");
                } else {
                    respuesta.setMensaje("Ocurrio un error al eliminar los datos");
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
