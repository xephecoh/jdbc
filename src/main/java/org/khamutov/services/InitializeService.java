package org.khamutov.services;

public class InitializeService {

    public static boolean processEnvironmentVariables() {
        return (!System.getenv().get("URL").isEmpty() &
                !System.getenv().get("USER_NAME").isEmpty() &
                !System.getenv().get("PASSWORD").isEmpty());
    }
}
