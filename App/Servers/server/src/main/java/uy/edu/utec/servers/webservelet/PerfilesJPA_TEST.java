//package uy.edu.utec.servers.webservelet;
//
//import jakarta.ejb.EJB;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import uy.edu.utec.servers.Entidades.Perfil;
//import uy.edu.utec.servers.Excepciones.ServiciosException;
//import uy.edu.utec.servers.remote.PerfilBeanImpl;
//import uy.edu.utec.servers.enums.EstadoPerfil;
//import uy.edu.utec.servers.utils.RandomGenerator;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
////http://localhost:8080/server-1.0-SNAPSHOT/perfiljpa
//@WebServlet(name = "perfiljpa", value = "/perfiljpa")
//public class PerfilesJPA_TEST  extends HttpServlet {
//    @EJB
//    private PerfilBeanImpl perfilBeanImpl;
//
//    public void init() {
//
//    }
//
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        Integer numAleatorio = RandomGenerator.generadorDeNumerosAleatorios();
//        String numAleatrorioStr = String.valueOf(numAleatorio);
//
//        Perfil perfil = new Perfil();
//        perfil.setNom_Perfil("Perfil" + numAleatrorioStr);
//        perfil.setEstado(EstadoPerfil.ACTIVO);
//
//        try {
//            perfilBeanImpl.agregarPerfil(perfil);
//        } catch (ServiciosException e) {
//            throw new RuntimeException(e);
//        }
//        Perfil perfilActualizado;
//        perfilActualizado = perfilBeanImpl.buscarPorId(1L);
//
//        if(perfilActualizado != null){
//            perfilActualizado.setNom_Perfil("Perfil" + numAleatrorioStr + 2);
//            perfilActualizado.setEstado(EstadoPerfil.ELIMINADO);
//            try {
//                perfilBeanImpl.actualizarPerfil(perfilActualizado);
//            } catch (ServiciosException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        //Obtenemos todos los usuarios para pintarlos en pantalla
//        List<Perfil> perfiles = perfilBeanImpl.obtenerTodosLosPerfiles();
//
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<html><head><title>Lista de Perfiles</title></head><body>");
//
//        out.println("<h1>Lista de Perfiles</h1>");
//        out.println("<table border='1'>");
//
//        // Encabezados de la tabla
//        out.println("<tr>");
//        out.println("<th>ID</th>");
//        out.println("<th>Nombre</th>");
//        out.println("<th>Estado</th>");
//        out.println("</tr>");
//
//        // Datos de los usuarios
//        for (Perfil p : perfiles) {
//            out.println("<tr>");
//            out.println("<td>" + p.getID_Perfil() + "</td>");
//            out.println("<td>" + p.getNom_Perfil() + "</td>");
//            out.println("<td>" + p.getEstado() + "</td>");
//            out.println("</tr>");
//        }
//
//        out.println("</table>");
//        out.println("</body></html>");
//
//    }
//
//    public void destroy() {
//    }
//}
