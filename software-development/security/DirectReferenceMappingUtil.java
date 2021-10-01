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
