<!DOCTYPE html>
<html>
<head>
    <title>Student Registration</title>
</head>

<body>

<h2>Student Registration Form</h2>

<form action="register" method="post">

    ID:
    <input type="number" name="id" required>
    <br><br>

    Name:
    <input type="text" name="name" required>
    <br><br>

    Course:
    <input type="text" name="course" required>
    <br><br>

    Marks:
    <input type="number" name="marks" required>
    <br><br>

    <input type="submit" value="Register">

</form>

<br>

<a href="display">Display All Students</a>

<br><br>

<a href="session.jsp">View Session Variable</a>

</body>
</html>