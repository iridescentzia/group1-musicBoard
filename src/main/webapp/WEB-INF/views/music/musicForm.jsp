<%--
  Created by IntelliJ IDEA.
  User: zia
  Date: 6/20/25
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>음악 등록/수정</title>
</head>
<body>
<h2>${music == null ? '음악 등록' : '음악 수정'}</h2>

<form method="post" action="${music == null ? '/api/music/create' : '/api/music/update'}">
    <c:if test="${music != null}">
        <input type="hidden" name="id" value="${music.id}" />
    </c:if>

    제목: <input type="text" name="title" value="${music.title}" /><br/>
    아티스트: <input type="text" name="artist" value="${music.artist}" /><br/>
    장르: <input type="text" name="genre" value="${music.genre}" /><br/>
    발매일: <input type="date" name="releaseDate" value="${music.releaseDate}" /><br/>
    커버 URL: <input type="text" name="coverUrl" value="${music.coverUrl}" /><br/>

    <button type="submit">${music == null ? '등록' : '수정'}</button>
</form>

<a href="/api/music/list">← 목록으로</a>
</body>
</html>