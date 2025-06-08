package com.example.coffeeshoprestapi.auth;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import io.jsonwebtoken.*;

@Provider
@PreMatching
public class JwtFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();

        System.out.println("Request Path: " + requestContext.getUriInfo().getPath());
        System.out.println("Request Path: " + path);
        // Skip token check for login endpoint
        if (path.contains("auth/login") || path.startsWith("auth/login")) {
            return;
        }

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            abort(requestContext, "Missing or invalid Authorization header");
            return;
        }

        try {
            String token = authHeader.substring("Bearer ".length());
            var claims = JwtUtil.parseToken(token);
            String username = claims.getBody().getSubject();
            String role = claims.getBody().get("role", String.class);

            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return () -> username;
                }

                @Override
                public boolean isUserInRole(String r) {
                    return r.equals(role);
                }

                @Override
                public boolean isSecure() {
                    return requestContext.getUriInfo().getRequestUri().getScheme().equals("https");
                }

                @Override
                public String getAuthenticationScheme() {
                    return "Bearer";
                }
            });

        } catch (Exception e) {
            abort(requestContext, "Invalid or expired token");
        }
    }

    private void abort(ContainerRequestContext ctx, String msg) {
        ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity("{\"error\": \"" + msg + "\"}")
                .build());
    }
}