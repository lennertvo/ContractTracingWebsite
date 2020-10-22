package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String dbUrl = servletContextEvent.getServletContext().getInitParameter("dburl");
        String schema = servletContextEvent.getServletContext().getInitParameter("schema");
        DbConnectionService.connect(dbUrl, schema);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DbConnectionService.disconnect();
    }
}
