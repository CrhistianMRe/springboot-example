package com.crhistianm.springboot.app.springboot_crud.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.crhistianm.springboot.app.springboot_crud.entities.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//Import TokenJwtConfig class as static to use all const final variables
import static com.crhistianm.springboot.app.springboot_crud.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

            User user = null;
            String username = null;
            String password = null;

            try {
                user = new ObjectMapper().readValue(request.getInputStream(), User.class);
                username = user.getUsername();
                password = user.getPassword();
            } catch (StreamReadException e) {
                e.printStackTrace();
            } catch (DatabindException e ) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

                
        return authenticationManager.authenticate(authenticationToken);
    }


    @Override
     protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
             Authentication authResult) throws IOException, ServletException {

         //Auth result i think is the attemptAuthentication method 
         //Get username from authentication result
         User user = (User) authResult.getPrincipal();
         String username = user.getUsername();

         //Retrieve roles from authResult
         Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();


         //Claims is a type interface who extends from Map
         Claims claims = Jwts.claims().build();
         claims.put("authorities", roles);

         //Generate token
         String token = Jwts.builder()
             .subject(username)
             .claims(claims)
             //One hour in millis
             .expiration(new Date(System.currentTimeMillis() + 3600000))
             //When the token was created 
             .issuedAt(new Date())
             //Sign the token with the secret key to after doing the successfulAuthentication verify it with the same key
             .signWith(SECRET_KEY).compact();

         //This const are from TokenJwtConfig class as static import
         response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

         //Create map to return it as json
         Map<String, String> body = new HashMap<>();
         body.put("token", token);
         body.put("username", username);
         body.put("message", String.format("Hola %s has iniciado sesion con exito!", username));

         response.getWriter().write(new ObjectMapper().writeValueAsString(body));
         response.setContentType(CONTENT_TYPE);
         response.setStatus(200);
     } 

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Error en la autenticacion username o password incorrectos!");
        body.put("error ", failed.getMessage()); 

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType(CONTENT_TYPE);
    }



    
}
