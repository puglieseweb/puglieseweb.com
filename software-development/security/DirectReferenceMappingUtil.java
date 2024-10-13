/**
* Enforcing the data layer access through random UUID values with verifying that the current user owns or is allowed to access the requested data protect against tampered IDs in URL parameters.
The application uses hash to replace the direct identifier. This hash is salted with a value defined at the application level.
Thus, the mapping table (real ID vs front end ID) is not required in the user session or in the application-level cache. The collection of enumeration values is more difficult to achieve because, even if an attacker may guess the hash algorithm from the ID size, it cannot reproduce the value due to the salt, which is not tied to the hidden value.
*/

@Log4j
public final class Utils {

    private final static String XF_HEADER = "X-Forwarded-For";
    private final static String COMMA = ",";

    public final static String PROPSFILE =
            "src/main/resources/application.properties";
    private static Properties properties;

    public static String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader(XF_HEADER);
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(COMMA)[0];
    }

    private static Properties getProperties() {
        if(properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream(PROPSFILE));
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return properties;
    }

    public static String computeFrontEndIdentifier(
            String realItemBackendIdentifier) {
        String frontEndId = null;
        if (realItemBackendIdentifier != null ) {

            realItemBackendIdentifier =
                    realItemBackendIdentifier.trim();
            if(realItemBackendIdentifier.isEmpty()) {
                return EMPTY_STRING;
            }
            String salt = getProperties()
                    .getProperty("com.scw.airplaneshop.idor.salt");
            String tmp = salt + realItemBackendIdentifier;
            MessageDigest digester = null;
            try {
                digester = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                log.error(e.getMessage());
            }
            byte[] hash = new byte[0];
            try {
                hash = digester.digest(tmp.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage());
            }
            frontEndId = DatatypeConverter.printHexBinary(hash);
        }
        return frontEndId;
    }

    public static Optional<String> getDescriptionPath() {
        return Optional.ofNullable(getProperties()
                .getProperty("com.scw.airplaneshop.discription.path"));
    }

    public static Optional<String> getDescriptionXmlFileName() {
        return Optional.ofNullable(getProperties()
                .getProperty("com.scw.airplaneshop.discription.filename.xml"));
    }
}
