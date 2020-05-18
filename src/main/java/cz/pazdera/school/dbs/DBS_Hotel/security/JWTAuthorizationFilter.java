package cz.pazdera.school.dbs.DBS_Hotel.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cz.pazdera.school.dbs.DBS_Hotel.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cz.pazdera.school.dbs.DBS_Hotel.config.GlobalVariables.*;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger log = LogManager.getLogger(JWTAuthorizationFilter.class);

    private final UserService userService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, UserService userService) {
        super(authManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
            var roles = this.userService.findByUsername(user).getAuthorities();

//            SimpleGrantedAuthority[] roles = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
//                    .build()
//                    .verify(token.replace(TOKEN_PREFIX, ""))
//                    .getClaims().get("roles").asArray(SimpleGrantedAuthority.class);
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, roles);
            }
            return null;
        }
        return null;
    }
}
