package cn.arcy.jportal.portal.constant;

public final class JwtClaimNames {

    public final static String CLIENT_ID = "client_id";

    /**
     * {@code iss} - the Issuer claim identifies the principal that issued the JWT
     */
    public static final String ISS = "iss";

    /**
     * {@code sub} - the Subject claim identifies the principal that is the subject of the
     * JWT
     */
    public static final String SUB = "sub";

    /**
     * {@code aud} - the Audience claim identifies the recipient(s) that the JWT is
     * intended for
     */
    public static final String AUD = "aud";

    /**
     * {@code exp} - the Expiration time claim identifies the expiration time on or after
     * which the JWT MUST NOT be accepted for processing
     */
    public static final String EXP = "exp";

    /**
     * {@code nbf} - the Not Before claim identifies the time before which the JWT MUST
     * NOT be accepted for processing
     */
    public static final String NBF = "nbf";

    /**
     * {@code iat} - The Issued at claim identifies the time at which the JWT was issued
     */
    public static final String IAT = "iat";

    /**
     * {@code jti} - The JWT ID claim provides a unique identifier for the JWT
     */
    public static final String JTI = "jti";

    private JwtClaimNames() {
    }
}
