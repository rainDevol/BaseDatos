
package DAO;
import datos.rTutor;
import conexion.Conexion;
import java.util.ArrayList;
import java.sql.*;

public class TutorDAO {
    Connection cn = null;
    PreparedStatement pt = null;
    CallableStatement cs = null;
    ResultSet rs = null;
    ArrayList<rTutor> listaTutor = null;
    rTutor objTutor =  null;
    
    public ArrayList<rTutor> listarTutor(){
        try {
            cn = Conexion.conexionBD();
            pt = cn.prepareStatement("{call obtener_tutor()}");
            rs = pt.executeQuery();
            listaTutor = new ArrayList<rTutor>();
            while(rs.next()){
                objTutor = new rTutor();
                objTutor.setNombre(rs.getString("nombre"));
                objTutor.setApellido(rs.getString("apellido"));
                objTutor.setGenero(rs.getString("genero"));
                objTutor.setNombre(rs.getString("nombre"));
                objTutor.setIdTutor(rs.getInt("idTutor"));
                objTutor.setDNI(rs.getInt("DNI"));
                objTutor.setDomicilio(rs.getString("domicilio"));
                objTutor.setTrabajo(rs.getString("trabajo"));
                listaTutor.add(objTutor);
            }
                pt.close();
                rs.close();
                cn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaTutor;
    }
    public void insertarTutor(){
        try {
            cn = Conexion.conexionBD();
            String consulta = ("call insertar_tutor(?,?,?,?,?,?,?,?)");
            cs = cn.prepareCall(consulta);
            cs.setString("t_nombre", objTutor.getNombre());
            cs.setString("t_apellido", objTutor.getApellido());
            cs.setString("t_fnacimiento", objTutor.getfNacimiento());
            cs.setString("t_genero", objTutor.getGenero());
            cs.setInt("t_DNI", objTutor.getDNI());
            cs.setString("t_domicilio", objTutor.getDomicilio());
            cs.setString("t_trabajo", objTutor.getTrabajo());
            cs.setInt("t_celular", objTutor.getCelular());
            cs.execute();
            cs.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public static void main(String[] args) {
        TutorDAO tdao = new TutorDAO();
        ArrayList<rTutor> lista = tdao.listarTutor();
        for (int i = 0; i < lista.size(); i++) {
            rTutor tutor = lista.get(i);
        System.out.println("ID: " + tutor.getIdTutor());
        System.out.println("Nombre: " + tutor.getNombre());
        System.out.println("Apellido: " + tutor.getApellido());
        System.out.println("Género: " + tutor.getGenero());
        System.out.println("DNI: " + tutor.getDNI());
        System.out.println("Domicilio: " + tutor.getDomicilio());
        System.out.println("Trabajo: " + tutor.getTrabajo());
            System.out.println("..........");
        }
    }
}
