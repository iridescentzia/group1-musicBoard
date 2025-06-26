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
    <title>음악 상세</title>
</head>
<body>
<h2>🎧 음악 상세 정보</h2>
<ul>
    <li><strong>제목:</strong> ${music.title}</li>
    <li><strong>아티스트:</strong> ${music.artist}</li>
    <li><strong>장르:</strong> ${music.genre}</li>
    <li><strong>발매일:</strong> ${music.releaseDate}</li>
    <li><strong>커버 이미지:</strong><br/>
        <img src="${music.coverUrl}" alt="커버 이미지" width="200px"/>
    </li>
</ul>

<!-- 수정/삭제 -->
<a href="/api/music/update?id=${music.id}">✏️ 수정</a>
<form method="post" action="/api/music/delete" style="display:inline;">
    <input type="hidden" name="id" value="${music.id}" />
    <button type="submit" onclick="return confirm('정말 삭제하시겠습니까?')">🗑 삭제</button>
</form>

<br/><br/>
<a href="/api/music/list">← 목록으로</a>
</body>
</html>