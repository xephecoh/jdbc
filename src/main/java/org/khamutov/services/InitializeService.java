package org.khamutov.services;

import java.util.Map;

public class InitializeService {
    private String url = "jdbc:postgresql://localhost:5432/test_DB";
    private String userName = "test_user";
    private String password = "12345";
    private String path = "src/main/resources/reports/";

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPath() {
        return path;
    }

    public InitializeService() {
        Map<String, String> getenv = System.getenv();
        String url = getenv.get("URL");
        String user_name = getenv.get("USER_NAME");
        String password = getenv.get("PASSWORD");
        if (    !url.isEmpty() &
                !user_name.isEmpty() &
                !password.isEmpty()) {
            this.url = url;
            this.userName = user_name;
            this.password = password;
        }
    }
}
