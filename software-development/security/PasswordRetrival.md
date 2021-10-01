/**
The application must protect any confidential database-connection information. It is recommended to store the settings for connecting to it in environment variables, and not in the program code. Gaining access to the system, an attacker will not have access to the database.
*/

File: application.yml
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}


/** Getting passaword    
    @PostConstruct
    private void initialize() throws
            BadPaddingException,
            InvalidKeyException,
            IllegalBlockSizeException,
            InvalidAlgorithmParameterException {
        this.url = encryption.decrypt(url);
        this.username = encryption.decrypt(username);
        this.password = encryption.decrypt(password);
    }
