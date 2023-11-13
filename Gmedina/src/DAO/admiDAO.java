
package DAO;
import java.sql.*;
import datos.rAdmi;
import conexion.Conexion;
import java.util.ArrayList;

public class admiDAO {
    Connection cn = null;
    CallableStatement cs = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    ArrayList<rAdmi> listaAdmi = null;
    rAdmi objAdmi = null;
    
    public ArrayList<rAdmi> Listar(){
        try {
            cn = Conexion.conexionBD();
            pt = cn.prepareStatement("{call obtener_admi()}");
            rs = pt.executeQuery();
            listaAdmi = new ArrayList<rAdmi>();
            while(rs.next()){
                objAdmi = new rAdmi();
                objAdmi.setNombre(rs.getString("nombre"));
                objAdmi.setApellido(rs.getString("apellido"));
                objAdmi.setfContrato(rs.getString("fContrato"));
                objAdmi.setDNI(rs.getInt("DNI"));
                objAdmi.setCelular(rs.getInt("celular"));
                objAdmi.setGenero(rs.getString("genero"));
                objAdmi.setDomicilio(rs.getString("domicilio"));
                listaAdmi.add(objAdmi);
            }
                pt.close();
                rs.close();
                cn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return listaAdmi;
    }
    public int insertarAdmi(rAdmi objI){
        int val = 0;
        try {
            cn = Conexion.conexionBD();
            cs = cn.prepareCall("{call insertar_admision(?,?,?,?,?,?,?)}");
            cs.setString(1, objI.getNombre());
            cs.setString(2, objI.getApellido());
            cs.setString(3, objI.getfContrato());
            cs.setInt(4, objI.getDNI());
            cs.setInt(5, objI.getCelular());
            cs.setString(6, objI.getGenero());
            cs.setString(7, objI.getDomicilio());
            val = cs.executeUpdate();
            cn.close();
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return val;
    }
    public int modificarAdmi(int num, rAdmi objI){
        int val = 0;
        try {
            cn = Conexion.conexionBD();
            cs = cn.prepareCall("{call editar_Admisiones(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, num);
            cs.setString(2, objI.getNombre());
            cs.setString(3, objI.getApellido());
            cs.setString(4, objI.getfContrato());
            cs.setInt(5, objI.getDNI());
            cs.setInt(6, objI.getCelular());
            cs.setString(7, objI.getGenero());
            cs.setString(8, objI.getDomicilio());
            val = cs.executeUpdate();
            cn.close();
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return val;
    }
    public int eliminarAdmi(int num){
        int val = 0;
        try {
            cn = Conexion.conexionBD();
            cs = cn.prepareCall("{call eliminar_Admi(?)}");
            cs.setInt(1, num);
            val = cs.executeUpdate();
            cn.close();
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return val;
    }
    public static void main(String[] args) {
        admiDAO ad = new admiDAO();
        ad.eliminarAdmi(1);
    }
}
