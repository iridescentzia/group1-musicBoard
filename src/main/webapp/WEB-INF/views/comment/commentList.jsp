<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>댓글 관리</title>
    <style>
        table { width: 80%; margin: 20px auto; border-collapse: collapse; }
        th, td { border: 1px solid #333; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
        textarea { width: 300px; height: 100px; }
        a { text-decoration: none; color: blue; }
    </style>
</head>
<body>

<h2 style="text-align: center;">댓글 관리</h2>

<%-- 리뷰 ID 기준 댓글 목록 --%>
<c:choose>
    <c:when test="${mode eq 'list'}">
        <h3 style="text-align: center;">댓글 목록 (리뷰 ID: ${reviewId})</h3>
        <c:choose>
            <c:when test="${not empty comments}">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>리뷰 ID</th>
                        <th>작성자 ID</th>
                        <th>내용</th>
                        <th>작성일</th>
                        <th>관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="comment" items="${comments}">
                        <tr>
                            <td>${comment.id}</td>
                            <td>${comment.reviewId}</td>
                            <td>${comment.userId}</td>
                            <td>${comment.content}</td>
                            <td>${comment.createdAt}</td>
                            <td>
                                    <%-- 링크 절대경로로 변경하여 URL과 맞게 --%>
                                <a href="${pageContext.request.contextPath}/comments/${comment.id}">상세</a> |
                                <a href="${pageContext.request.contextPath}/comments/${comment.id}/edit">수정</a> |
                                <a href="${pageContext.request.contextPath}/comments/${comment.id}/delete" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p style="text-align: center;">댓글이 없습니다.</p>
            </c:otherwise>
        </c:choose>
        <p style="text-align: center;">
            <a href="${pageContext.request.contextPath}/comments/review/${reviewId}/new">댓글 작성하기</a>
        </p>
    </c:when>

    <%-- 사용자 ID 기준 댓글 목록 추가 --%>
    <c:when test="${mode eq 'listUser'}">
        <h3 style="text-align: center;">댓글 목록 (사용자 ID: ${userId})</h3>
        <c:choose>
            <c:when test="${not empty comments}">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>리뷰 ID</th>
                        <th>작성자 ID</th>
                        <th>내용</th>
                        <th>작성일</th>
                        <th>관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="comment" items="${comments}">
                        <tr>
                            <td>${comment.id}</td>
                            <td>${comment.reviewId}</td>
                            <td>${comment.userId}</td>
                            <td>${comment.content}</td>
                            <td>${comment.createdAt}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/comments/${comment.id}">상세</a> |
                                <a href="${pageContext.request.contextPath}/comments/${comment.id}/edit">수정</a> |
                                <a href="${pageContext.request.contextPath}/comments/${comment.id}/delete" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p style="text-align: center;">댓글이 없습니다.</p>
            </c:otherwise>
        </c:choose>
    </c:when>

    <%-- 상세보기 --%>
    <c:when test="${mode eq 'detail'}">
        <h3 style="text-align: center;">댓글 상세보기</h3>
        <p>ID: ${comment.id}</p>
        <p>작성자 이름: ${comment.userName}</p>
        <p>리뷰 내용: ${comment.reviewContent}</p>
        <p>평점: ${comment.reviewRating}</p>
        <p>내용: ${comment.content}</p>
        <p>작성일: ${comment.createdAt}</p>
        <p>
            <a href="${pageContext.request.contextPath}/comments/${comment.id}/edit">수정</a> |
            <a href="${pageContext.request.contextPath}/comments/${comment.id}/delete" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a> |
            <a href="javascript:history.back()">목록으로</a>
        </p>
    </c:when>

    <%-- 작성폼 --%>
    <c:when test="${mode eq 'form'}">
        <h3 style="text-align: center;">댓글 작성 (리뷰 ID: ${reviewId})</h3>
        <form action="${pageContext.request.contextPath}/comments/review/${reviewId}/new" method="post">
            <label>작성자 ID: <input type="number" name="userId" required></label><br><br>
            <label>내용: <textarea name="content" required></textarea></label><br><br>
            <button type="submit">댓글 작성</button>
        </form>
        <p><a href="javascript:history.back()">목록으로</a></p>
    </c:when>

    <%-- 수정폼 --%>
    <c:when test="${mode eq 'edit'}">
        <h3 style="text-align: center;">댓글 수정 (ID: ${comment.id})</h3>
        <form action="${pageContext.request.contextPath}/comments/${comment.id}/edit" method="post">
            <p>리뷰 ID: ${comment.reviewId}</p>
            <p>작성자 ID: ${comment.userId}</p>
            <label>내용: <textarea name="content" required>${comment.content}</textarea></label><br><br>
            <button type="submit">수정 완료</button>
        </form>
        <p><a href="${pageContext.request.contextPath}/comments/${comment.id}">상세보기로 돌아가기</a></p>
    </c:when>

    <c:otherwise>
        <p style="text-align: center; color:red;">잘못된 접근입니다.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
