package org.khamutov.services;

import java.util.Map;

public class InitializeService {
    private static final String URL = "jdbc:postgresql://localhost:5432/test_DB";
    private static final String USER_NAME = "test_user";
    private static final String PASSWORD = "12345";
    private static final String REPORT_PATH = "src/main/resources/reports/";
    private final String url;
    private final String userName;
    private final String password;
    private final String reportPath;

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getReportPath() {
        return reportPath;
    }

    public InitializeService() {
        Map<String, String> getenv = System.getenv();
        String url = getenv.get("URL");
        String userName = getenv.get("USER_NAME");
        String password = getenv.get("PASSWORD");
        String reportPath = getenv.get("REPORT_PATH");
        this.url = url != null ? url : URL;
        this.userName = userName != null ? userName : USER_NAME;
        this.password = password != null ? password : PASSWORD;
        this.reportPath = reportPath != null ? reportPath : REPORT_PATH;
    }
}
