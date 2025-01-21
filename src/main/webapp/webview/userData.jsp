<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Data</title>
</head>
<body>
    <h1>User Data</h1>
    <table border="1">
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Registration Date</th>
        </tr>
        <tr>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.registrationDate}</td>
        </tr>
    </table>
</body>
</html>
