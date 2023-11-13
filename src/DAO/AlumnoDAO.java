
package DAO;
import datos.rAlumno;
import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;

public class AlumnoDAO {
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    ArrayList<AlumnoDAO>ListaAlumnoDAO = null;
    rAlumno alumDato = null;
    
    public ArrayList<AlumnoDAO>ListarAlumnos(){
        try {
            cn = Conexion.conexionBD();
            pt = cn.prepareStatement("Select * from alumno");
            rs = pt.executeQuery();
            ListaAlumnoDAO = new ArrayList<AlumnoDAO>();
            while (rs.next()){
                alumDato = new rAlumno();
                alumDato.setNombre(rs.getString("nombre"));
                alumDato.setApellido(rs.getString("apellido"));
                alumDato.setGenero(rs.getString("genero"));
                alumDato.setNombre(rs.getString("nombre"));
                alumDato.setIdAlumno(rs.getInt("idAlumno"));
                alumDato.setDNI(rs.getInt("DNI"));
                alumDato.setIdTutor(rs.getInt("idTutor"));
                alumDato.setIdNivel(rs.getInt("idNivel"));
            }
            pt.close();
            rs.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ListaAlumnoDAO;
    }
}
