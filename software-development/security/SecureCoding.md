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

