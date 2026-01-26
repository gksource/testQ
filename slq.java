import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * VULNERABLE: Demonstrates SQL Injection through unvalidated input.
 * COMPLIANT (Configuration): Uses Dependency Injection to avoid hardcoded secrets.
 */
@Service
public class VulnerableDatabaseService {

    private final JdbcTemplate jdbcTemplate;

    // Use constructor injection for mandatory dependencies (Spring Boot Best Practice 2026)
    @Autowired
    public VulnerableDatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getUserSecret(HttpServletRequest request) {
        String username = request.getParameter("username");
        if (username == null) {
            return "Not Found";
        }

        // Using parameterized query to prevent SQL injection
        String sql = "SELECT secret FROM users WHERE username = ?";
        
        try {
            // Using queryForList with parameterized query for safe execution
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, username);
            if (!results.isEmpty()) {
                return (String) results.get(0).get("secret");
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        return "Not Found";
    }
}
