# authserver

> Spring Security + Jwt + OAuth 2.0, and authorization from database.

- 1#, Get request:<br/>
```
localhost:8080/oauth/authorize?client_id=normal-app&response_type=code&scope=read&redirect_uri=/resources/user
```
<br/><br/>

- 2#, Post request:<br/>
```
localhost:8080/oauth/token?code=xxx&grant_type=authorization_code&client_id=normal-app&redirect_uri=/resources/user
```