//package uy.edu.utec.servers.webservelet;
//
//import jakarta.ejb.EJB;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import uy.edu.utec.servers.Entidades.Institucion;
//import uy.edu.utec.servers.Excepciones.ServiciosException;
//import uy.edu.utec.servers.remote.InstitucionBeanImpl;
//import uy.edu.utec.servers.enums.EstadoInstitucion;
//import uy.edu.utec.servers.utils.RandomGenerator;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
////http://localhost:8080/server-1.0-SNAPSHOT/insitucionesjpa
//@WebServlet(name = "insitucionesjpa", value = "/insitucionesjpa")
//public class InstitucionesJPA_TEST extends HttpServlet {
//    @EJB
//    private InstitucionBeanImpl institucionBeanImpl;
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
//        Institucion institucion = new Institucion();
//        institucion.setNom_Institucion("Insti" + numAleatrorioStr);
//        institucion.setEstado(EstadoInstitucion.ACTIVO);
//        try {
//            institucionBeanImpl.agregarInstitucion(institucion);
//        } catch (ServiciosException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        //Obtenemos todas las instituciones para pintarlas en pantalla
//        List<Institucion> instituciones = institucionBeanImpl.obtenerTodosLasInstituciones();
//
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<html><head><title>Lista de Instituciones</title></head><body>");
//
//        out.println("<h1>Lista de Instituciones</h1>");
//        out.println("<table border='1'>");
//
//        // Encabezados de la tabla
//        out.println("<tr>");
//        out.println("<th>ID</th>");
//        out.println("<th>Nombre</th>");
//        out.println("<th>Estado</th>");
//        out.println("</tr>");
//
//        for (Institucion i : instituciones) {
//            out.println("<tr>");
//            out.println("<td>" + i.getID_Institucion() + "</td>");
//            out.println("<td>" + i.getNom_Institucion() + "</td>");
//            out.println("<td>" + i.getEstado() + "</td>");
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
