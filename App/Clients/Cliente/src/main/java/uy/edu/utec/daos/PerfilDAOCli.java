package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.PerfilRemote;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearPerfilDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PerfilDAOCli {
    public static PerfilRemote perfilRemote;


    public PerfilDAOCli() {
        try {
            java.util.Properties jndiProps = new java.util.Properties();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");


            Context context = new InitialContext(jndiProps);

            String jndi =
                    "ejb:/server-1.0-SNAPSHOT/IntervencionRemoteImpl!uy.edu.utec.servers.remote.interfaces.IntervencionRemote";


            if (context == null) {
                throw new NamingException("Context is null");
            }

            perfilRemote = (PerfilRemote) context.lookup(jndi);
            if (perfilRemote == null) {
                throw new NamingException("No se pudo obtener una referencia al EJB remoto");
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void agregarPerfil(CrearPerfilDTO perfil) throws ServiciosException {

        if (perfilRemote != null) {
            perfilRemote.agregarPerfil(perfil);
            System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar el perfil.");
        }
    }
}
