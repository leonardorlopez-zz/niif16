package baseDeDatos;

import Entidades.BCNIIFALT;
import Entidades.BCNIIFLUJ;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlujoDAO {

    //Insertamos un registro en BCNIIFALT 
    public static void inserta(BCNIIFALT a) {
        int aux = 0;
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = AdministradorDeConexiones.obtenerConexion();

            String sql = "insert into niif16.bcniifalt (idAgrupador, tipoContr, descriptn, proveedor,fechAlta, fechCorte, moneda, tipCamb, tasDescDol, tasDescPes, estado) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
            st = con.prepareStatement(sql);
            st.setInt(1, a.getIdAgrupador());
            st.setString(2, a.getTipoContr());
            st.setString(3, a.getDescriptn());
            st.setString(4, a.getProveedor());
            st.setString(5, a.getFechAlta());
            st.setString(6, a.getFechCorte());
            st.setString(7, a.getMoneda());
            st.setDouble(8, a.getTipCamb());
            st.setDouble(9, a.getTasDescDol());
            st.setDouble(10, a.getTasDescPes());
            st.setString(11, a.getEstado());

            st.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            //throw new CafeStoreException();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

    }

    public static void insertaFlujo(ArrayList<BCNIIFLUJ> flujo) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = AdministradorDeConexiones.obtenerConexion();

            //Se recorre el flujo 
            for (int i = 0; i < flujo.size(); i++) {
                BCNIIFLUJ flujAux = flujo.get(i);
                //Solo se ingresara lo necesario para la aprobacion del flujo de contrato	
                String sql = "insert into bcniifluj (idAgrupador, tipoContr, descriptn, proveedor, fechAlta, "
                        + "fechDesde, fechHasta, moneda, fechCorte, tipCamb, tasDescDol, estado, usuario, "
                        + "fechModif, impMonOrig, diasFuturos, imporDesc, saldo, intereses, amortizac, "
                        + "tirCuota, tirPorcen, sumaImpMonOrig, tirSuma) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                st = con.prepareStatement(sql);
                st.setInt(1, flujAux.getIdAgrupador());
                st.setString(2, flujAux.getTipoContr());
                st.setString(3, flujAux.getTipoContr());
                st.setString(4, flujAux.getProveedor());
                st.setString(5, flujAux.getFechAlta());
                st.setString(6, flujAux.getFechDesde());
                st.setString(7, flujAux.getFechHasta());
                st.setString(8, flujAux.getMoneda());
                st.setString(9, flujAux.getFechCorte());
                st.setDouble(10, flujAux.getTipCamb());
                st.setDouble(11, flujAux.getTasDescDol());
                st.setString(12, "NV");
                st.setString(13, flujAux.getUsuario());
                st.setString(14, flujAux.getFechModif());
                st.setDouble(15, flujAux.getImpMonOrig());
                st.setInt(16, flujAux.getDiasFuturos());
                st.setDouble(17, flujAux.getImporDesc());
                st.setDouble(18, flujAux.getSaldo());
                st.setDouble(19, flujAux.getIntereses());
                st.setDouble(20, flujAux.getAmortizac());
                st.setDouble(21, flujAux.getTirCuota());
                st.setDouble(22, flujAux.getTirPorcen());
                st.setDouble(23, flujAux.getSumaImpMonOrig());
                st.setDouble(24, flujAux.getSumatoriaTIR());

                st.execute();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            //throw new CafeStoreException();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion");
            }

        }

    }

}
/*
	public static void modifica(Producto p) throws CafeStoreException, NegocioException {
		PreparedStatement st = null;
		Connection con = null;
		try {
			//para que me traiga el id correspondiente a la descripcion 
                        //que puso el usuario con ese id puede continuar el flujo y podra
                        //modificar el precio lo cual es el objetivo
//                        Producto p2 = getProducto(p.getId());
//                        if (getProducto(p2.getId())==null) {
//				throw new NegocioException("El producto no existe. No se puede modificar.");
//			}
//                        Producto p1 = getProducto(p.getDescripcion());

                        con = AdministradorDeConexiones.obtenerConexion();
			String sql = "update Producto set precio = ? where descripcion = ?";
			st = con.prepareStatement(sql);
			st.setDouble(1, p.getPrecio());
			st.setString(2, p.getDescripcion());
			//st.setInt(3, p.getId());
			st.execute();
		} catch (Exception e) {
			throw new CafeStoreException();
                
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CafeStoreException();
			}

		}
	}

	public static void elimina(int id) throws NegocioException, CafeStoreException  {
		PreparedStatement st = null;
		Connection con = null;
		if (getProducto(id)==null) {
			throw new NegocioException("El producto no existe");
		}
		try {
			con = AdministradorDeConexiones.obtenerConexion();
			String sql = "delete from Producto where id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.execute();
		} catch (Exception e) {
			throw new CafeStoreException();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				//si aca hay una excepcion la sacamos asi termina bien el main
			}
		}
	}

	public static Producto getProducto(String descripcion) throws NegocioException, CafeStoreException {
		Connection con = null;
		Producto p = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			try {
				con = AdministradorDeConexiones.obtenerConexion();
			} catch (Exception e) {
				e.printStackTrace();
				throw new CafeStoreException();
			}
			String sql = "select * from Producto where descripcion  = ?";
			st = con.prepareStatement(sql);
			st.setString(1, descripcion);
			rs = st.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				double precio = rs.getDouble("precio");
				String desc = rs.getString("descripcion");
				p = new Producto();
				p.setDescripcion(desc);
				p.setId(id);
				p.setPrecio(precio);
			} else {
				throw new NegocioException("El producto no existe");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
		return p;
	}
	
	public static boolean existe(String descripcion) throws NegocioException, CafeStoreException {
	        Connection con = null;
		Producto p = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		boolean respuesta = false;
		try {
			try {
		             con =AdministradorDeConexiones.obtenerConexion();
			} catch (Exception e) {
				e.printStackTrace();
				throw new CafeStoreException();
			}
			String sql = "select * from Producto where descripcion  = ?";
			st = con.prepareStatement(sql);
			st.setString(1, descripcion);
			rs = st.executeQuery();
			if (rs.next()) {
				respuesta = true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
		return respuesta;
	}
	
	public static Producto getProducto(int id) {
		Connection con = null;
		Producto p = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			con = AdministradorDeConexiones.obtenerConexion();
			String sql = "select * from Producto where id  = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				double precio = rs.getDouble("precio");
				String desc = rs.getString("descripcion");
				p = new Producto();
				p.setDescripcion(desc);
				p.setId(id);
				p.setPrecio(precio);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
		return p;
	}
        
       public static ArrayList<Producto> getProductos() { //CAMBIO
		Connection con = null;
		ArrayList<Producto> productos = new ArrayList<Producto>(); //AGREGO
  		ResultSet rs = null;
		PreparedStatement st = null;
		try {
                        Producto p = null; //CAMBIO
			con = AdministradorDeConexiones.obtenerConexion();
			String sql = "select * from Producto"; //CAMBIO
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id"); //CAMBIO
                                double precio = rs.getDouble("precio");
				String desc = rs.getString("descripcion");
				p = new Producto();
				p.setDescripcion(desc);
				p.setId(id);
				p.setPrecio(precio);
                                productos.add(p);//Para agregar un objeto a la lista
                                
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
		return productos;
	}
        
       public static TreeMap<String, Producto> getProductosMap() { //CAMBIO
		Connection con = null;
		TreeMap<String, Producto> treeMap = new TreeMap<>(); //AGREGO
  		
                ResultSet rs = null;
		PreparedStatement st = null;
		try {
                        Producto p = null; //CAMBIO
			con = AdministradorDeConexiones.obtenerConexion();
			String sql = "select * from Producto"; //CAMBIO
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
                        while (rs.next()) {
				int id = rs.getInt("id"); //CAMBIO
                                double precio = rs.getDouble("precio");
				String desc = rs.getString("descripcion");
				p = new Producto();
				p.setDescripcion(desc);
				p.setId(id);
				p.setPrecio(precio);
                                
                                treeMap.put("Id "+id, p);
                                
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
		return treeMap;
	}
         
 */
