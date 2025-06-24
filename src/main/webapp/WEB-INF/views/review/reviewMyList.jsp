<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>내 리뷰 목록</title>
</head>
<body>
<h2>내가 작성한 리뷰</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>음악 제목</th>
        <th>내용</th>
        <th>평점</th>
        <th>작성일</th>
        <th>수정</th>
        <th>삭제</th>
    </tr>
    <c:forEach var="review" items="${myReviewList}">
        <tr>
            <td>${review.id}</td>
            <td>${review.music.title}</td>
            <td>${review.content}</td>
            <td>${review.rating}</td>
            <td>${review.createdAt}</td>
            <td>
                <a href="/review/update?id=${review.id}">수정</a>
            </td>
            <td>
                <form action="/review/delete" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${review.id}" />
                    <button type="submit">삭제</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
