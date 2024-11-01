package com.example.lab3;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDAO {
    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    // Create
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee (id, name, position, department, salary) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getPosition());
            pstmt.setString(4, employee.getDepartment());
            pstmt.setDouble(5, employee.getSalary());
            pstmt.executeUpdate();
        }
    }
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee employee = new Employee(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("position"),
                    rs.getString("department"),
                    rs.getDouble("salary")
                );
                employees.add(employee);
            }
        }
        return employees;
    }

    // Update
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET name = ?, position = ?, department = ?, salary = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getPosition());
            pstmt.setString(3, employee.getDepartment());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.setString(5, employee.getId());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public void deleteEmployee(String id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
