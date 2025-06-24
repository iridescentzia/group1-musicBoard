<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>리뷰 작성</title>
</head>
<body>
<h2>리뷰 작성</h2>

<form action="/review/create" method="post">
    <input type="hidden" name="userId" value="${userId}" />

    <label>음악 선택:</label>
    <select name="musicId">
        <c:forEach var="music" items="${musicList}">
            <option value="${music.id}">${music.title} - ${music.artist}</option>
        </c:forEach>
    </select><br><br>

    <label>평점:</label>
    <input type="number" name="rating" min="1" max="5" required /><br><br>

    <label>리뷰 내용:</label><br>
    <textarea name="content" rows="5" cols="50" required></textarea><br><br>

    <button type="submit">작성</button>
</form>

</body>
</html>
