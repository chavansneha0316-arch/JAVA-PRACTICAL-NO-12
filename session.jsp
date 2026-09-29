<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <title>Session Variable</title>
</head>

<body>

<h2>Session Variable</h2>

<%
    String studentName =
            (String) session.getAttribute("studentName");
%>

<%
    if (studentName != null) {
%>

    <p>Student Name stored in Session:</p>

    <h3>
        <%= studentName %>
    </h3>

<%
    } else {
%>

    <p>No student name found in session.</p>

<%
    }
%>

<br>

<a href="index.jsp">
    Back to Registration
</a>

</body>
</html>