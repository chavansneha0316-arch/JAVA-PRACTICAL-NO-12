package com.student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/display")
public class DisplayServlet extends HttpServlet {

    private static final String URL =
            "jdbc:mysql://localhost:3306/college?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "Root@1234";

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {

            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL database
            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);

            // Select all records
            String sql = "SELECT * FROM student";

            PreparedStatement ps = con.prepareStatement(sql);

            // Execute query
            ResultSet rs = ps.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student Records</title>");
            out.println("</head>");

            out.println("<body>");

            out.println("<h2>All Student Records</h2>");

            out.println("<table border='1' cellpadding='10'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Course</th>");
            out.println("<th>Marks</th>");
            out.println("</tr>");

            // Display all records
            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>"
                        + rs.getInt("id")
                        + "</td>");

                out.println("<td>"
                        + rs.getString("name")
                        + "</td>");

                out.println("<td>"
                        + rs.getString("course")
                        + "</td>");

                out.println("<td>"
                        + rs.getInt("marks")
                        + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<br>");

            out.println("<a href='index.jsp'>");
            out.println("Back to Registration");
            out.println("</a>");

            out.println("</body>");
            out.println("</html>");

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            out.println("<html>");
            out.println("<body>");

            out.println("<h2>Error:</h2>");
            out.println("<p>" + e.getMessage() + "</p>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}