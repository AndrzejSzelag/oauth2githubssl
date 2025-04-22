# OAuth 2.0 GitHub SSL 

[<img alt="springboot" src="https://img.shields.io/badge/Spring Boot-3.4.3-COLOR.svg?logo=LOGO">](<https://spring.io/projects/spring-boot>) 
[<img alt="Gradle" src="https://img.shields.io/badge/Gradle-8.12.1-02303A.svg?logo=gradle">](https://gradle.org/)
[<img alt="OAuth 2.0" src="https://img.shields.io/badge/OAuth 2.0-Protocol-2C2255.svg?logo=oauth">](https://oauth.net/2/)

### A Spring Boot web application configured to use OAuth 2.0 with GitHub API and self-signed SSL certificate.

![OAuth2GitHub1.java](OAuth2GitHub1.png "OAuth2Github - Login")
![OAuth2GitHub2.java](OAuth2GitHub2.png "OAuth2GitHub - Home")

### Tech Stack
* 🔶 Java 21.0.5 LTS
* 🔶 Spring Boot 3.4.3
* 🔶 Gradle 8.12.1
* 🔶 Apache Tomcat 10.1.36
* 🔶 Sonarqube Gradle 4.0.0.2929

### IDE
* 🔶 Visual Studio Code 1.98.2


#### What is OAuth 2.0?

OAuth (Open Authorization) 2.0 is an open authorization standard. It provides a way for users to log into a web-based
application (Spring Security OAuth 2.0) by delegating the authentication process to trusted third-party services, such
as Google and GitHub. The full specification of the OAuth 2.0 protocol can be found
in [RFC 6749](https://datatracker.ietf.org/doc/html/rfc6749).

#### How use OAuth2.0?

1. Clone a GitHub Repository to your computer:

          git clone https://github.com/AndrzejSzelag/oauth2githubssl.git

2. Configuring an **OAuth 2.0 GitHub SSL** application in **GitHub** (https://github.com/: Settings -> Developer settings).

   You need:

        CLIENT_ID
        CLIENT_SECRET

      **Warning:**
      These values should be set in the **application.yml** file in place of **CLIENT_ID** and **CLIENT_SECRET**.

4. To run a Spring Boot project using Gradle from the command line, follow these steps:
* Open a terminal in the project's root directory.
* Execute the command:

          gradlew bootRun
          
4. Paste the URL into your web browser:

          http://localhost:8080

❤️ You have successfully run the Spring Boot project using Gradle!
