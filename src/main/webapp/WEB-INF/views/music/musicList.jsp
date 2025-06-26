<%--
  Created by IntelliJ IDEA.
  User: zia
  Date: 6/20/25
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>🎵 음악 목록</title>
</head>
<body>
<h2>🎵 음악 리스트</h2>
<a href="/api/music/create">➕ 새 음악 등록</a>
<table border="1">
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>아티스트</th>
        <th>장르</th>
        <th>발매일</th>
        <th>상세</th>
    </tr>
    <c:forEach var="music" items="${list}">
        <tr>
            <td>${music.id}</td>
            <td>${music.title}</td>
            <td>${music.artist}</td>
            <td>${music.genre}</td>
            <td>${music.releaseDate}</td>
            <td>
                <a href="/api/music/get?id=${music.id}">상세보기</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
