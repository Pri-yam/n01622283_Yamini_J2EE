package com.example.coffeeshoprestapi.config;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import org.h2.tools.Server;

import java.sql.SQLException;

@Singleton
@Startup
public class H2ConsoleLauncher {

    private Server webServer;

    @PostConstruct
    public void start() {
        try {
            // Start H2’s HTTP console on port 8082 (accessible at http://localhost:8082)
            webServer = Server.createWebServer(
                    "-web",                // start web server
                    "-webAllowOthers",     // allow remote connections (optional)
                    "-webPort", "8082"     // port
            ).start();
            System.out.println("H2 console started at http://localhost:8082");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 console", e);
        }
    }
}
