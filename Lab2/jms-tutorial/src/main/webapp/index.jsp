<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <form action="send" method="post">
        <label for="msg">Enter message:</label>
        <input type="text" id="msg" name="message" required />
        <button type="submit">Send</button>
    </form>
</body>
</html>