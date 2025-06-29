<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 목록 - 음악 후기 게시판</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .review-card {
            transition: transform 0.2s ease-in-out;
            border: none;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .review-card:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        }
        .music-cover {
            width: 80px;
            height: 80px;
            object-fit: cover;
            border-radius: 8px;
        }
        .rating-stars {
            color: #ffc107;
        }
        .review-content {
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .genre-badge {
            font-size: 0.8em;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <!-- 헤더 -->
    <div class="row mb-4">
        <div class="col">
            <div class="d-flex justify-content-between align-items-center">
                <h2>
                    <i class="fas fa-music me-2"></i>음악 후기 게시판
                </h2>
                <div>
                    <a href="/reviews/form" class="btn btn-primary">
                        <i class="fas fa-plus me-1"></i>리뷰 작성
                    </a>
                    <a href="/reviews/my" class="btn btn-outline-secondary ms-2">
                        <i class="fas fa-user me-1"></i>내 리뷰
                    </a>
                </div>
            </div>
            <hr>
        </div>
    </div>

    <!-- 리뷰 목록 -->
    <div class="row">
        <c:choose>
            <c:when test="${empty reviews}">
                <div class="col-12">
                    <div class="alert alert-info text-center">
                        <i class="fas fa-info-circle me-2"></i>
                        등록된 리뷰가 없습니다.
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${reviews}" var="review" varStatus="status">
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="card review-card h-100">
                            <div class="card-body">
                                <!-- 음악 정보 -->
                                <div class="d-flex mb-3">
                                    <c:if test="${not empty review.music.coverUrl}">
                                        <img src="${review.music.coverUrl}"
                                             class="music-cover me-3"
                                             alt="앨범 커버"
                                             onerror="this.src='https://via.placeholder.com/80x80?text=No+Image'">
                                    </c:if>
                                    <div class="flex-grow-1">
                                        <h6 class="card-title mb-1">${review.music.title}</h6>
                                        <p class="text-muted mb-1">${review.music.artist}</p>
                                        <span class="badge bg-secondary genre-badge">${review.music.genre}</span>
                                    </div>
                                </div>

                                <!-- 평점 -->
                                <div class="mb-2">
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
                                        <span class="ms-2 text-muted small">(${review.rating}/5)</span>
                                    </div>
                                </div>

                                <!-- 리뷰 내용 -->
                                <p class="card-text review-content">${review.content}</p>

                                <!-- 작성자 및 날짜 -->
                                <div class="d-flex justify-content-between align-items-center mt-3">
                                    <small class="text-muted">
                                        <i class="fas fa-user me-1"></i>User #${review.userId}
                                    </small>
                                    <small class="text-muted">
                                        <fmt:formatDate value="${review.createdAt}" pattern="MM-dd"/>
                                    </small>
                                </div>
                            </div>

                            <!-- 카드 푸터 -->
                            <div class="card-footer bg-transparent">
                                <a href="/reviews/${review.id}" class="btn btn-outline-primary btn-sm w-100">
                                    <i class="fas fa-eye me-1"></i>자세히 보기
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- 페이지네이션 (테스트용 임시) -->
    <div class="row mt-4">
        <div class="col">
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                        <span class="page-link">이전</span>
                    </li>
                    <li class="page-item active">
                        <span class="page-link">1</span>
                    </li>
                    <li class="page-item disabled">
                        <span class="page-link">다음</span>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <!-- 하단 -->
    <div class="row mt-5">
        <div class="col">
            <hr>
            <div class="text-center">
                <p class="text-muted">음악 후기 게시판 - 테스트용 페이지</p>
                <div class="mt-2">
                    <a href="/api/revi
