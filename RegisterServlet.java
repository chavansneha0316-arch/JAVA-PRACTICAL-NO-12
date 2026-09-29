package com.student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final String URL =
            "jdbc:mysql://localhost:3306/college?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "Root@1234";

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // Get data from JSP form
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String course = request.getParameter("course");
        int marks = Integer.parseInt(request.getParameter("marks"));

        try {

            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL database
            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);

            // Insert query
            String sql = "INSERT INTO student "
                    + "(id, name, course, marks) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            // Set values
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, course);
            ps.setInt(4, marks);

            // Execute insert
            int result = ps.executeUpdate();

            if (result > 0) {

                // Create session
                HttpSession session = request.getSession();

                // Store student name in session
                session.setAttribute("studentName", name);

                out.println("<html>");
                out.println("<body>");

                out.println("<h2>Registration Successful!</h2>");

                out.println("<p>Student ID: " + id + "</p>");
                out.println("<p>Student Name: " + name + "</p>");
                out.println("<p>Course: " + course + "</p>");
                out.println("<p>Marks: " + marks + "</p>");

                out.println("<br>");

                out.println("<a href='display'>");
                out.println("Display All Students");
                out.println("</a>");

                out.println("<br><br>");

                out.println("<a href='session.jsp'>");
                out.println("View Session Variable");
                out.println("</a>");

                out.println("</body>");
                out.println("</html>");
            }

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