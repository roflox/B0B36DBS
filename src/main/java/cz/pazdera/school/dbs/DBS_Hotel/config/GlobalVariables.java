package cz.pazdera.school.dbs.DBS_Hotel.config;

public class GlobalVariables {
    public static final String REGISTER_URL = "/auth/register";
    public static String SECRET = "r*8L~<t<suShG46M";
    public static long EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String NOT_SPECIFIED = "%s must have specified '%s'";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String NOT_FOUND = "%s with was not found";
}
