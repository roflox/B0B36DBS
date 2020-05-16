package cz.pazdera.school.dbs.DBS_Hotel.config;

import org.springframework.beans.factory.annotation.Value;

public class GlobalVariables {
    public final static String REGISTER_URL = "/auth/register";
    public final static String SECRET = "myjwtsecret";
    public final static long EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
