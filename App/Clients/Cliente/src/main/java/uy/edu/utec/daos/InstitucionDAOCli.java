/*package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.InstitucionRemote;
import uy.edu.utec.servers.dtos.InstitucionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearInstitucionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

public class InstitucionDAOCli {
    public static InstitucionRemote institucionRemote;


    public InstitucionDAOCli() {
        try {
            java.util.Properties jndiProps = new java.util.Properties();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");


            Context context = new InitialContext(jndiProps);

            String jndi =
                    "ejb:/server-1.0-SNAPSHOT/InstitucionBeanImpl!uy.edu.utec.servers.beans.remote.interfaces.InstitucionRemote";


            if (context == null) {
                throw new NamingException("Context is null");
            }

            institucionRemote = (InstitucionRemote) context.lookup(jndi);
            if (institucionRemote == null) {
                throw new NamingException("No se pudo obtener una referencia al EJB remoto");
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void agregarInstitucion(CrearInstitucionDTO institucion) throws ServiciosException {

        if (institucionRemote != null) {
            institucionRemote.agregarInstitucion(institucion);
            System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar la institucion.");
        }
    }


    public static List<InstitucionDTO> obtenerInstituciones(){
        if (institucionRemote != null) {
            return institucionRemote.obtenerTodasLasInstituciones();
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar la institucion.");
        }
        return null;
    }
}
*/