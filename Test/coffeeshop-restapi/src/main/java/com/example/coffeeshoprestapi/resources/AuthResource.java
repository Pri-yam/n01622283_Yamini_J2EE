package com.example.coffeeshoprestapi.resources;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    // Simple test endpoint - try this first!
    @GET
    @Path("/test")
    public Response test() {
        return Response.ok("{\"message\":\"AuthResource is working!\"}").build();
    }

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("mysecretkey12345678901234567890".getBytes());
    private static final long EXPIRATION_TIME = 86400000; // 24 hours

    @POST
    @Path("/login")
    public Response login(LoginRequest loginRequest) {
        // Simple hardcoded authentication - replace with real authentication
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        List<String> roles;
        if ("admin".equals(username) && "admin123".equals(password)) {
            roles = Arrays.asList("admin", "user");
        } else if ("user".equals(username) && "user123".equals(password)) {
            roles = List.of("user");
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse("Invalid credentials"))
                    .build();
        }

        String token = Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();

        return Response.ok(new LoginResponse(token, username, roles)).build();
    }

    // Inner classes for request/response
    public static class LoginRequest {
        private String username;
        private String password;

        public LoginRequest() {}

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginResponse {
        private String token;
        private String username;
        private List<String> roles;

        public LoginResponse() {}

        public LoginResponse(String token, String username, List<String> roles) {
            this.token = token;
            this.username = username;
            this.roles = roles;
        }

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public List<String> getRoles() { return roles; }
        public void setRoles(List<String> roles) { this.roles = roles; }
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse() {}
        public ErrorResponse(String message) { this.message = message; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
