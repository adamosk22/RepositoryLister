# Repository Lister
Application provides an API service to list GitHub repositories of a user
## Usage
To access user's repositories use
```
GET localhost:8080/github/users/{username}/repos
```
Replace {username} with the desired GitHub username.
## Provided information
- Repository Name
- Owner Login
- For each branch: its name and the last commit SHA
## Prerequisites
- Java 21
- Spring 3
- Maven
