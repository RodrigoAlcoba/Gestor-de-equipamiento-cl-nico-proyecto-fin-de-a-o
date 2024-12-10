package uy.edu.utec.daos;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBClientUtility {
    private static Context context;

    public static void init() {
        try {
            java.util.Properties jndiProps = new java.util.Properties();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080"); // Cambia la URL según tu configuración

            // Crea el contexto de JNDI (Java Naming and Directory Interface)
            context = new InitialContext(jndiProps);
        } catch (NamingException e) {
            e.printStackTrace(); // Maneja la excepción según tus necesidades
        }
    }

    public static <T> T lookupEJB(String jndiName, Class<T> ejbInterface) throws NamingException {
        if (context == null) {
            throw new NamingException("Context is null");
        }

        return (T) context.lookup(jndiName);
    }
}
