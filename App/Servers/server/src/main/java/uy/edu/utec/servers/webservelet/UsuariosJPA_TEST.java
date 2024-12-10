//package uy.edu.utec.servers.webservelet;
//
//import jakarta.ejb.EJB;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import uy.edu.utec.servers.Entidades.Usuario;
//import uy.edu.utec.servers.Excepciones.ServiciosException;
//import uy.edu.utec.servers.local.UsuarioBean;
//import uy.edu.utec.servers.enums.EstadoUsuario;
//import uy.edu.utec.servers.utils.RandomGenerator;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//
////http://localhost:8080/server-1.0-SNAPSHOT/usuariosjpa
//@WebServlet(name = "usuariosjpa", value = "/usuariosjpa")
//public class UsuariosJPA_TEST extends HttpServlet {
//
//    private String message;
//    @EJB
//    private UsuarioBean usuarioBean;
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
//        Usuario usuario = new Usuario();
//        usuario.setNombre("Manolito"+numAleatrorioStr);
//        usuario.setCedula("223456"+numAleatrorioStr);
//        usuario.setEmail("manolito"+numAleatrorioStr+"@example.com");
//        usuario.setNombre2("SegundoNombre");
//        usuario.setApellido("González");
//        usuario.setApellido2("SegundoApellido");
//
//        // Crear una fecha de nacimiento (ejemplo: 1 de enero de 1990)
//        java.util.Date fechaNacimiento = java.sql.Date.valueOf(java.time.LocalDate.of(1990, 1, 1));
//        usuario.setFec_nac(fechaNacimiento);
//
//        usuario.setNom_usuario("test"+numAleatrorioStr);
//        usuario.setContrasenia("claveSegura");
//        usuario.setEstado(EstadoUsuario.ACTIVO);
//
//        //DESCOMENTAR PARA PODER CREAR USUARIOS cada vez que actualizan en
//        //http://localhost:8080/server-1.0-SNAPSHOT/ejemplojpa path a la pagina
//        try {
//            usuarioBean.agregarUsuario(usuario);
//        } catch (ServiciosException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        //Actualizamos un usaurio por medio de se ID
//        Usuario usuarioParaActualizar = new Usuario();
//        usuarioParaActualizar = usuarioBean.buscarPorId(2l);//ID a modificar tipo long con la "l" al final
//       usuarioParaActualizar.setNombre("Actualizado"+numAleatrorioStr);// comentar si falla al principio
//        //DESCOMENTAR PARA PODER ACTUALIZAR
//        try {
//            usuarioBean.actualizarUsuario(usuarioParaActualizar);
//        } catch (ServiciosException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        //Obtenemos todos los usuarios para pintarlos en pantalla
//        List<Usuario> usuarios = usuarioBean.obtenerTodosLosUsuarios();
//
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<html><head><title>Lista de Usuarios</title></head><body>");
//
//        out.println("<h1>Lista de Usuarios</h1>");
//        out.println("<table border='1'>");
//
//        // Encabezados de la tabla
//        out.println("<tr>");
//        out.println("<th>ID</th>");
//        out.println("<th>Nombre</th>");
//        out.println("<th>Apellido</th>");
//        out.println("<th>Fecha de Nacimiento</th>");
//        out.println("<th>Correo</th>");
//        out.println("<th>Teléfono</th>");
//        out.println("</tr>");
//
//        // Datos de los usuarios
//        for (Usuario u : usuarios) {
//            out.println("<tr>");
//            out.println("<td>" + u.getID_Usuario() + "</td>");
//            out.println("<td>" + u.getNombre() + "</td>");
//            out.println("<td>" + u.getApellido() + "</td>");
//            out.println("<td>" + u.getFec_nac() + "</td>");
//            out.println("<td>" + u.getEmail() + "</td>");
//            //out.println("<td>" + u.get + "</td>");
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
