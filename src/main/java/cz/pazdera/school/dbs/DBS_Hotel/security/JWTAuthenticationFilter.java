package cz.pazdera.school.dbs.DBS_Hotel.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.pazdera.school.dbs.DBS_Hotel.dto.user.LoginDto;
import cz.pazdera.school.dbs.DBS_Hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static cz.pazdera.school.dbs.DBS_Hotel.config.GlobalVariables.*;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger console = LogManager.getLogger(JWTAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;


    private UserService userService;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            LoginDto dto = new ObjectMapper()
                    .readValue(req.getInputStream(), LoginDto.class);
            var details = userService.findByUsername(dto.username);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.username,
                            dto.password,
                            details.getAuthorities()
                    )
            );
        } catch (IOException | InternalAuthenticationServiceException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        User u = (User) (auth.getPrincipal());
//        String[] roles = new String[u.getAuthorities().size()];
//        for (int i = 0; i < u.getAuthorities().size(); i++) {
//            roles[i] = u.getAuthorities().toArray()[i].toString();
//        }
        String token = JWT.create()
                .withSubject(u.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
