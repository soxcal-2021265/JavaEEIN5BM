package controlador;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.ClienteDAO;

public class Controlador extends HttpServlet {

    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    int codCliente;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        String menuAdmin = request.getParameter("menuAdmin");
        
        // Verificar si el parámetro 'menu' es igual a "Principal"
        if (menu.equals("MenuInicio")) {
            // Hacer el forward al JSP 'MenuInicio.jsp'
            request.getRequestDispatcher("MenuInicio.jsp").forward(request, response);
        }else  if (menu.equals("ClientesAdmin")) {
            
            switch (accion) {
            case "Listar":
                List listaClientes = clienteDAO.listar();
                request.setAttribute("clientes", listaClientes);
                break;
            case "Agregar":
                String nombreCliente = request.getParameter("txtNombreCliente");
                String telefonoCliente = request.getParameter("txtTelefonoCliente");
                String correoCliente = request.getParameter("txtCorreoCliente");
                String direccion = request.getParameter("txtDireccion");
                String contrasena = request.getParameter("txtContrasena");
                String rol = request.getParameter("txtRol");
                cliente.setNombreCliente(nombreCliente);
                cliente.setTelefonoCliente(telefonoCliente);
                cliente.setCorreoCliente(correoCliente);
                cliente.setDireccion(direccion);
                cliente.setContrasena(contrasena);
                cliente.setRol(rol);
                clienteDAO.agregar(cliente);
                request.getRequestDispatcher("Controlador?menu=ClientesAdmin&accion=Listar").forward(request, response);
                break;
            case "Editar":
                break;
            case "Actualizar":
                break;
            case "Eliminar":
                break;
            default:
                throw new AssertionError();
        }
            request.getRequestDispatcher("ClientesAdmin.jsp").forward(request, response);
        }else  if (menu.equals("EmpleadosAdmin")) {
            request.getRequestDispatcher("EmpleadosAdmin.jsp").forward(request, response);
        }else  if (menu.equals("LlantasAdmin")) {
            request.getRequestDispatcher("LlantasAdmin.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
