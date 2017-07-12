# springboot-oauth2-sample
Sample spring-boot application using OAuth2 for authentication

## Overview
Spring MVC application which secured by Spring Security. Instead of using simple form based security, it is secured by Spring Security OAuth2 and the OAuth provider is Google.

## Usage
Please deploy every services using docker way or maven way, then simply load http://localhost:8080 on the browser.


### Docker

Start building docker images for every services, simply run following command on root directory

```mvn clean package -Pdocker```

Launch services using docker-compose

```docker-compose up -d```

### Maven

On each service folder run following command:

```mvn spring-boot:run```

### Application properties

The application.yml (in src/main/resources) contains the details of the Google application which it uses to authenticate details. Change the values of the following attributes to the values for your application google.client.id google.client.secret

### To register a Google App perform the following steps

1. Go to https://console.developers.google.com and login with your Google account (this will be the developer account and the email of this account will be published when someone tries to authenticate with the Google application)
2. If you don't have a project create a new one and then click into the project.
3. In the menu on the left side select "APIs & auth" --> "Credentials" --> "Create a new client ID"
4. In the popup select the following
Application Type = Web Application
Authorized Javascript Origins = ,
Authorized Redirect URI = , the URI for our application is /googleLogin so for local testing you should enter http://localhost:8080/googleLogin and http://localhost:8080/googleLogin/ on different lines.
5. Copy the client ID and client Secret and update the application.yml
6. Make sure you update the mandatory values on the "APIs & auth" --> "Consent screen" page as the application will not work without it.
7. When you have a the Google App configured and the Spring boot application and you navigate to http://localhost:8080. It will redirect you to a Google login page. Upong login it will ask you to authorize your application for access to your account to get email and profile data. On successful login it will render the basic HTML page which means the authentication was sucessful.

If you are interested in fetching the Google profile data in type in the following URL http://localhost:8080/find?term=hello in the authenticated browser session and the profile data will be logged in the console of the Spring application.

## Running the application

Open http://localhost:8080 and connect with your google credentials.
