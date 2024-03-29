In cryptography, PKCS #12 defines an archive file format for storing many cryptography objects as a single file. 
It is commonly used to bundle a *private key* with its *X.509 certificate* or to *bundle all the members of a chain of trust*.

A PKCS #12 file may be encrypted and signed. The internal storage containers, called "SafeBags", may also be encrypted and signed. 
A few SafeBags are predefined to store *certificates*, *private keys* and *CRLs*. Another SafeBag is provided to store any other data at individual implementer's choice.


It is advisible to:
* use a different logger for debug pupose
* log internal ids rather the user or emails informatins 
* Replace the Spring Whitelabel error page and replace with an custom error.html


# Debugging
Adding a service parameter to a server request is not a good protection against hacking. If an attacker fakes a request, he will be able to include the output of debugging information in logs and collect a lot of important information about the system.

**It is recommended to create separate debugging levels for test and production bench. This is an important defense measure against attack on debug enabled strategy. When deploying the application in a production environment, the settings for enabling debugging output need to be monitored*


It is recommended to display minimum information in the logs. Implementing the option to disable debugging in the config file is a safe solution. The system administrator can switch values when switching from a test bench to a working one, which will protect information about the system and user data.

```
package scw.boardgameshop.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class DebugSettings {

    @Value("${debug.enabled}")
    private Boolean isEnabled;

}
```

It is not recommended to put sc.getSessionCookieConfig().setHttpOnly(false); as this results in the cookie being accessible by client-side scripts.

# Session Cook
It is advised to put sc.getSessionCookieConfig().setHttpOnly(true); this causes the cookie to not be accessible through client-side scripts. As a result, if a user clicks a link that includes a cross-site scripting (XSS) attack, the browser will not reveal the cookie to the third party.


## Generic Error 
At the time of error, the application incorporates a custom error page to show a brief error message to the end user. Also, in case of any uncaught errors, the whitelabel error page is enabled to prevent the rendering of stack trace on the UI, debug mode is also disabled to improve the performance. These security measures won't allow an attacker to gain access to the sensitive information through poorly constructed error messages.
```
public class Error {
    public Integer status;
    public String error;
    public String message;
    public String timeStamp;

    public Error(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.error = (String) errorAttributes.get("error");
        this.message = getErrorMessage(status);
        this.timeStamp = errorAttributes.get("timestamp").toString();
    }

    private String getErrorMessage(int statusCode) {
        String errorMessage = "";
        switch (statusCode) {
            case 400: {
                errorMessage = "Bad Request";
                break;
            }
            case 401: {
                errorMessage = "Unauthorized";
                break;
            }
            case 404: {
                errorMessage = "Resource not found";
                break;
            }
            case 500: {
                errorMessage = "Internal Server Error";
                break;
            }
            default: {
                errorMessage = "Unknown error";
            }
        }
        return errorMessage;
    }

}
```
```
import com.securecodewarrior.employee.model.request.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Value("${debug}")
    private boolean debug;

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    String error(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        model.addAttribute("error",
                new Error(response.getStatus(), getErrorAttributes(request, debug)));
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(
            HttpServletRequest request,
            boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes,
                includeStackTrace);
    }
}

```
resources/templates/error/
```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    th:replace="common/header :: common-header"
    <title>Error Page</title>
</head>
<body>
<img class="img-responsive" src="/images/banner2.png" alt="banner2"/>
<div class="alert alert-danger">
    <h1>Error Occured</h1>
    <span><b>TIME: </b></span><span th:text="${error.timeStamp}">No
			Message</span><br/> <span><b>ERROR: </b></span><span
        th:text="${error.error}">No exception</span><br/> <span><b>STATUS:
		</b></span><span th:text="${error.status}">No exception</span><br/> <span><b>CAUSE:
		</b></span><span th:text="${error.message}"></span><br/> <br/>
</div>
<img class="img-responsive" src="/images/banner2.png" alt="banner2"/>
</body>
</html>
```
with propety file: 
```
# debug properties
debug=false
server.error.whitelabel.enabled=true

![image](https://user-images.githubusercontent.com/669458/135618160-f6a04153-eccb-43e3-9855-89863258891b.png)

## Avoiding Stored CSS 
Stored data must always be sanitized before displaying. Using Owasp encoder to sanitize data will help protect the application from XSS attacks, which will prevent an attacker from injecting scripts or other code into data for display on HTML pages
Use the owasp Encoder project  https://owasp.org/www-project-java-encoder/
```
import org.owasp.encoder.Encode;
....
    private String sanitize(String value) {
      return value == null ? null : Encode.forHtml(value);
    }

```
```
# Reflected XSS
User input transferred to the page is not sanitized, making it possible for an attacker to carry out an XSS attack by sending a link with the malicious script contained in it to the victim. The resulting malicious script will go to the page and execute it. A successful XSS attack will allow an adversary to take any action on behalf of the victim.

When creating new elements of the document structure, it is not recommended to insert unverified data from user forms. DOM-based XSS is characterized by the absence of server requests, which complicates the data validation for the developer.

Using a special ESAPI4J library for encoding data allows you to repel an attack with the introduction of javascript code. This rule allows you not to worry about filling the contents of new elements.
```
Base.esapi.properties.logging['ApplicationLogger'] = {
	Level: org.owasp.esapi.Logger.ALL,
	Appenders: [ new Log4js.ConsoleAppender() ],
	LogUrl: true,
	LogApplicationName: true,
	EncodingRequired: true
};

Base.esapi.properties.application.Name = "My Application v1.0";
org.owasp.esapi.ESAPI.initialize();

function search() {
	var searchNum = document.getElementById('js-filter-num').value;
	var searchBank = document.getElementById('js-filter-bank').value;
	var searchId = 'n' + searchNum + 'b' + searchBank;
	var resultSearch = document.getElementById(searchId);
	removeElement('js-filter-num');
	removeElement('js-filter-bank')
	removeElement('js-filter-button');
	removeElement('js-cardList-header');
	removeElement('js-cardList');
	removeElement('js-cardform-header');
	removeElement('js-cardform');
	removeElement('js-user-page');
	addElement('Seach num', searchNum);
	addElement('Seach bankId', searchBank);
	if (resultSearch == null) {
		addElement('Card', 'not found'); 
	} else {
		var varCardholder = resultSearch.cells.item(1).firstElementChild.value;
		var varExpired = resultSearch.cells.item(2).firstElementChild.value;
		var varCvc = resultSearch.cells.item(3).firstElementChild.value;
		var varAccountId = resultSearch.cells.item(4).firstElementChild.value;
		addElement('CardHolder', varCardholder);
		addElement('Expired', varExpired);
		addElement('AccountId', varAccountId);
		addElement('Cvc', varCvc);
	}
}

function removeElement(name) {
	var element = document.getElementById(name);
	element.parentNode.removeChild(element);
}

function addElement(name, value) {
	var newDiv = document.createElement('div');
	var elementHtml = '<p>' + $ESAPI.encoder().encodeForHTML(name) + ' : ' + $ESAPI.encoder().encodeForHTML(value) + '</p>';
	newDiv.innerHTML = elementHtml;
	document.body.append(newDiv);
}

$(document).ready(function() {
    $("#js-filter-num").val("");
    $("#js-filter-bank").val("");
    $("#num").focusout(function() {
        var value = $(this).val();
        $("#num").val(value.replace(/ /g,''));
    });
});
```


# Seled object 
A CipherInputStream instance deserializes the encrypted object. An InvalidClassException will be thrown if the serialized object is invalid, corrupt or damaged, which will prevent any deserialization attack. The serialized object must be properly encrypted in order to be accepted.

An attacker could exploit the weaknesses of the standard ObjectInputStream class. Individually, this class does not check the type of object class before deserialization. This is why we need ot use SealedObject or Apache ValidatingObjectInputStream 


```

	public  void secureSerializableObject(Serializable object, OutputStream ostream) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
	    try {
	        Cipher cipher = Cipher.getInstance( "Blowfish" );
	        cipher.init( Cipher.ENCRYPT_MODE, key64 );
	        SealedObject sealedObject = new SealedObject(object, cipher);

	        // Wrap the output stream
	        CipherOutputStream cos = new CipherOutputStream(ostream, cipher);
	        ObjectOutputStream outputStream = new ObjectOutputStream(cos);
	        outputStream.writeObject(sealedObject);
	        outputStream.close();
	    } catch (IllegalBlockSizeException e) {
	        e.printStackTrace();
	    }
	}

	public static Object secureDeserializableObject(InputStream istream) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance( "Blowfish" );
		cipher.init(Cipher.DECRYPT_MODE, key64);

		CipherInputStream cipherInputStream = new CipherInputStream(istream, cipher);
		ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
		SealedObject sealedObject;
		try {
			sealedObject = (SealedObject) inputStream.readObject();
			return sealedObject.getObject(cipher);
		} catch (ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
			throw new InvalidClassException("Class that you deserialize is corrupted or incorrect ");
		}
	}
}
```


# methods 2 by using Apache Commons:
Deserialization is allowed only for valid objects strictly set by the developer using the helper class validatingObjectInputStream.accept (UserInfoDto.class) . # This provides reliable rotection against deserialization of unexpected or malicious objects.
```

import com.scw.pentestfield.exception.SerializeException;
import com.scw.pentestfield.model.dto.UserInfoDto;
import com.scw.pentestfield.security.encryption.AES.CryptoSymmetricAES;
import com.scw.pentestfield.service.UserInfoSerializeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.serialization.ValidatingObjectInputStream;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import java.io.*;

@Service
@RequiredArgsConstructor
public class UserInfoSerializeServiceImpl implements UserInfoSerializeService {

    private final CryptoSymmetricAES cryptoSymmetricAES;

    @Override
    public void serializeUserInfo(UserInfoDto userInfoDto, File file) {
        try (
                FileOutputStream out = new FileOutputStream(file);
                CipherOutputStream cipherOutputStream = new CipherOutputStream(out,
                        cryptoSymmetricAES.getCipher(Cipher.ENCRYPT_MODE))
        ) {
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(cipherOutputStream);
            objectOutputStream.writeObject(userInfoDto);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new SerializeException("Serialize object failed");
        }
    }

    @Override
    public UserInfoDto deserializeUserInfo(File file) {
        try (FileInputStream in = new FileInputStream(file)) {
            CipherInputStream cipherInputStream = new CipherInputStream(in,
                    cryptoSymmetricAES.getCipher(Cipher.DECRYPT_MODE));
            ValidatingObjectInputStream validatingObjectInputStream =
                    new ValidatingObjectInputStream(cipherInputStream);
            validatingObjectInputStream.accept(UserInfoDto.class);
            UserInfoDto userInfoDto = (UserInfoDto)
                    validatingObjectInputStream.readObject();
            validatingObjectInputStream.close();
            return userInfoDto;
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializeException("Deserialize object failed");
        }
    }
}
```

# Solution 3
The EmployeeObjectInputStream class is extended from the ObjectInputStream as it checks if the className is identical to the required className. If not, it throws an unauthorized exception, making this the recommended solution for preventing deserialization attacks.
```
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

import com.securecodewarrior.employee.model.request.EmployeeRequestVO;

public class EmployeeObjectInputStream extends ObjectInputStream {

    public EmployeeObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException,
            ClassNotFoundException {
        if (!desc.getName().equals(EmployeeRequestVO.class.getName())) {
            throw new InvalidClassException(
                    "Unauthorized deserialization attempt",
                    desc.getName());
        }
        return super.resolveClass(desc);
    }

}


    public EmployeeRequestVO deserializedEmployeeObject(EmployeeRequestVO employeeRequestVO) {
        serializedEmployeeObject(employeeRequestVO);
        EmployeeRequestVO employee = null;

        ObjectInputStream ois;
        try {
            ois = new EmployeeObjectInputStream(new FileInputStream("emp.bin"));
            employee = (EmployeeRequestVO) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employee;
    }


    private void serializedEmployeeObject(EmployeeRequestVO employeeRequestVO) {
        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                    "emp.bin"));
            oos.writeObject(employeeRequestVO);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

```

# Using Known Vulnerable Components
o always be aware of the safety of connected libraries to the project, you must use the OWASP plugin.
By default, when building a project, this plugin checks downloaded libraries for vulnerabilities and, depending on the danger level set, in the plugin settings 7 signals the weakness of the project if it contains libraries with a level there are more than seven dangers (in this application, according to the settings).
https://github.com/dependency-check/dependency-check-gradle
https://owasp.org/www-project-dependency-check/
https://jeremylong.github.io/DependencyCheck/index.html
https://www.first.org/cvss/specification-document



# Logging 
Poor or excessive monitoring of user actions can lead to an attack on the system. An attacker can safely perform operations such as changing a password, replacing user information, etc., and remain undetected. And redundant information can hide really necessary data.
Points:
1. Do not disable debug log level: `spring.debug.level=false`

#Stored XSS

User input data should be sanitized before storing it into the database and encoded before including it into an HTML page. ESAPI is a library recommended by OWASP for sanitizing and encoding input data, which efficiently prevents the execution of stored scripts on the client.


Displaying raw user input on the page will allow an attacker to steal data, such as browser cookies.
```
<div class="container">
    FILTER SEARCH : <span th:utext="${filter}"/>
</div>
```


this is OK:
```
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      lang="en">
<head>
    <script type="text/javascript"
            th:src="@{/webjars/jquery/3.4.1/jquery.min.js/}"></script>
    <link rel="stylesheet" th:href="@{../css/main.css}"/>
</head>
<body>
<div class="container" id="js-name-form">
    <h3>SEARCH ANALYSIS BY NAME</h3>
    <p>
        <label>Enter analysis name</label>
        <input type="text" id="js-name-filter" value=""/>
    </p>
    <p>
        <input type="button" id="js-name-filter-button" onclick="generateLink();" value="Get link">
    </p>
</div>
<script>
    function generateLink() {
        var searchValue = document.getElementById('js-name-filter').value.trim();
        var newA = document.createElement('a');
        var elementHtml = '<p>Click to link</p>';
        newA.innerHTML = elementHtml;
        newA.setAttribute('href', '/search-by-analysis-name-result?analysisName=' + escape(searchValue));
        document.body.append(newA);
    }
</script>
</body>
</html>
```

# RECaptcha
It is recommended to use trusted and safe libraries and third-party scripts. Google ReCaptcha is used to prevent automated attacks. Using secure sources will prevent attackers from injecting any scripts to obtain user data or control the application.
https://developers.google.com/recaptcha/docs/display

# Mail
The application uses the JavaMail library (javax.mail-api ver. 1.5.3) without subject sanitising. This library is known to be vulnerable to Emal Header injection. An attacker, who is allowed to choose the subject of an email, can use a line feed, in order to force the setSubject() method of JavaMail to inject a new SMTP header.

The application should obtain components and libraries from official sources over secure links and prefer signed packages to reduce the chance of including modified, malicious components. Moreover, the libraries should be the latest version and have no known vulnerabilities.
Using the library "spring-boot-starter-mail" (Starter for using Java Mail and Spring Framework's email sending support) with the latest stable version improves the application mail sending security.
