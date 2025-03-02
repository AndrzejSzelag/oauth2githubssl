# OAuth 2.0 GitHub SSL

### A Spring Boot web application configured to use OAuth 2.0 with GitHub API and self-signed SSL certificate..

### Tech Stack
* 🔶 Java 21.0.5 LTS
* 🔶 Spring Boot 3.4.3
* 🔶 Gradle 8.12.1
* 🔶 Apache Tomcat 10.1.36

### IDE
* 🔶 Visual Studio Code - Insiders 1.97.2


#### What is OAuth 2.0?

OAuth (Open Authorization) 2.0 is an open authorization standard. It provides a way for users to log into a web-based
application (Spring Security OAuth 2.0) by delegating the authentication process to trusted third-party services, such
as Google and GitHub. The full specification of the OAuth 2.0 protocol can be found
in [RFC 6749](https://datatracker.ietf.org/doc/html/rfc6749).

#### How use OAuth2.0?

1. Configuring an **OAuth 2.0 GitHub SSL** application in **GitHub** (https://[https://github.com/](https://github.com/)/).

   You need:

        CLIENT_ID
        CLIENT_SECRET  
        REDIRECT_URI (e.g. http://localhost:8080/login/oauth2/code/github)

3. Clone a GitHub Repository to your computer:

          git clone https://github.com/AndrzejSzelag/oauth2githubssl.git

4. Run JAR file and in your web browser paste a URL:

          http://localhost:8080
