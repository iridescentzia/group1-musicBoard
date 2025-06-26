<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 상세 - 음악 후기 게시판</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .music-cover {
            max-width: 200px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .rating-stars {
            color: #ffc107;
            font-size: 1.2em;
        }
        .review-content {
            background-color: #f8f9fa;
            border-left: 4px solid #007bff;
            padding: 20px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <!-- 헤더 -->
    <div class="row mb-4">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/reviews">리뷰 목록</a></li>
                    <li class="breadcrumb-item active">리뷰 상세</li>
                </ol>
            </nav>
        </div>
    </div>

    <!-- 리뷰 상세 정보 -->
    <div class="row">
        <div class="col-md-4">
            <!-- 음악 정보 -->
            <div class="card">
                <c:if test="${not empty review.music.coverUrl}">
                    <img src="${review.music.coverUrl}" class="card-img-top music-cover mx-auto mt-3" alt="앨범 커버">
                </c:if>
                <div class="card-body">
                    <h5 class="card-title">${review.music.title}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${review.music.artist}</h6>
                    <p class="card-text">
                        <span class="badge bg-secondary">${review.music.genre}</span>
                    </p>
                    <c:if test="${not empty review.music.releaseDate}">
                        <p class="card-text">
                            <small class="text-muted">
                                발매일: <fmt:formatDate value="${review.music.releaseDate}" pattern="yyyy-MM-dd"/>
                            </small>
                        </p>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <!-- 리뷰 정보 -->
            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">
                        <i class="fas fa-comment-alt me-2"></i>리뷰 #${review.id}
                    </h5>
                    <div class="rating-stars">
                        <c:forEach begin="1" end="5" var="i">
                            <c:choose>
                                <c:when test="${i <= review.rating}">
                                    <i class="fas fa-star"></i>
                                </c:when>
                                <c:otherwise>
                                    <i class="far fa-star"></i>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <span class="ms-2 text-muted">(${review.rating}/5)</span>
                    </div>
                </div>
                <div class="card-body">
                    <div class="review-content">
                        <p class="mb-3">${review.content}</p>
                    </div>

                    <div class="mt-4">
                        <p class="text-muted">
                            <i class="fas fa-user me-1"></i>작성자: User #${review.userId}
                            <span class="ms-3">
                                    <i class="fas fa-calendar me-1"></i>
                                    <fmt:formatDate value="${review.createdAt}" pattern="yyyy-MM-dd HH:mm"/>
                                </span>
                        </p>
                    </div>

                    <!-- 액션 버튼들 -->
                    <div class="mt-4">
                        <a href="/reviews" class="btn btn-secondary">
                            <i class="fas fa-list me-1"></i>목록으로
                        </a>
                        <button type="button" class="btn btn-primary ms-2">
                            <i class="fas fa-edit me-1"></i>수정
                        </button>
                        <button type="button" class="btn btn-danger ms-2">
                            <i class="fas fa-trash me-1"></i>삭제
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 하단 여백 -->
    <div class="row mt-5">
        <div class="col">
            <hr>
            <p class="text-center text-muted">음악 후기 게시판 - 테스트용 페이지</p>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // 테스트용 간단한 스크립트
    console.log('리뷰 상세 페이지 로드됨');
    console.log('리뷰 ID:', '${review.id}');
    console.log('음악 제목:', '${review.music.title}');
</script>
</body>
</html>
