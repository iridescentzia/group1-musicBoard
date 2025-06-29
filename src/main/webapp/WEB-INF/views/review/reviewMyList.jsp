<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 리뷰 목록 - 음악 후기 게시판</title>
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
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis;
            min-height: 60px;
        }
        .genre-badge {
            font-size: 0.8em;
        }
        .action-buttons {
            display: flex;
            gap: 8px;
        }
        .user-stats {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border-radius: 15px;
            padding: 20px;
            margin-bottom: 30px;
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
                    <li class="breadcrumb-item active">내 리뷰 목록</li>
                </ol>
            </nav>
            <div class="d-flex justify-content-between align-items-center">
                <h2>
                    <i class="fas fa-user-edit me-2"></i>내가 작성한 리뷰
                </h2>
                <div>
                    <a href="/reviews/form" class="btn btn-primary">
                        <i class="fas fa-plus me-1"></i>새 리뷰 작성
                    </a>
                    <a href="/reviews" class="btn btn-outline-secondary ms-2">
                        <i class="fas fa-list me-1"></i>전체 리뷰 보기
                    </a>
                </div>
            </div>
            <hr>
        </div>
    </div>

    <!-- 사용자 통계 -->
    <div class="user-stats">
        <div class="row text-center">
            <div class="col-md-3">
                <div class="fs-4 fw-bold">${myReviewList.size()}</div>
                <div class="small">작성한 리뷰</div>
            </div>
            <div class="col-md-3">
                <div class="fs-4 fw-bold">
                    <c:set var="totalRating" value="0" />
                    <c:forEach items="${myReviewList}" var="review">
                        <c:set var="totalRating" value="${totalRating + review.rating}" />
                    </c:forEach>
                    <c:choose>
                        <c:when test="${myReviewList.size() > 0}">
                            <fmt:formatNumber value="${totalRating / myReviewList.size()}" maxFractionDigits="1" />
                        </c:when>
                        <c:otherwise>0</c:otherwise>
                    </c:choose>
                </div>
                <div class="small">평균 평점</div>
            </div>
            <div class="col-md-3">
                <div class="fs-4 fw-bold">User #${userId}</div>
                <div class="small">사용자 ID</div>
            </div>
            <div class="col-md-3">
                <div class="fs-4 fw-bold">
                    <i class="fas fa-trophy"></i>
                </div>
                <div class="small">활동 등급</div>
            </div>
        </div>
    </div>

    <!-- 내 리뷰 목록 -->
    <div class="row">
        <c:choose>
            <c:when test="${empty myReviewList}">
                <div class="col-12">
                    <div class="alert alert-info text-center">
                        <i class="fas fa-info-circle me-2"></i>
                        아직 작성한 리뷰가 없습니다.
                        <div class="mt-3">
                            <a href="/reviews/form" class="btn btn-primary">
                                <i class="fas fa-plus me-1"></i>첫 번째 리뷰 작성하기
                            </a>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${myReviewList}" var="review" varStatus="status">
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

                                <!-- 작성 날짜 -->
                                <div class="mb-3">
                                    <small class="text-muted">
                                        <i class="fas fa-calendar me-1"></i>
                                        <fmt:formatDate value="${review.createdAt}" pattern="yyyy-MM-dd HH:mm"/>
                                    </small>
                                </div>
                            </div>

                            <!-- 카드 푸터 - 액션 버튼들 -->
                            <div class="card-footer bg-transparent">
                                <div class="action-buttons">
                                    <a href="/reviews/${review.id}" class="btn btn-outline-info btn-sm flex-grow-1">
                                        <i class="fas fa-eye me-1"></i>상세보기
                                    </a>
                                    <button type="button" class="btn btn-outline-primary btn-sm"
                                            onclick="editReview(${review.id})">
                                        <i class="fas fa-edit me-1"></i>수정
                                    </button>
                                    <button type="button" class="btn btn-outline-danger btn-sm"
                                            onclick="deleteReview(${review.id}, '${review.music.title}')">
                                        <i class="fas fa-trash me-1"></i>삭제
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- 페이지네이션 (테스트용 임시) -->
    <c:if test="${not empty myReviewList}">
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
    </c:if>

    <!-- 하단 -->
    <div class="row mt-5">
        <div class="col">
            <hr>
            <div class="text-center">
                <p class="text-muted">음악 후기 게시판 - 테스트용 페이지</p>
                <div class="mt-2">
                    <small class="text-muted">
                        현재 로그인 사용자: User #${userId}
                        <span class="ms-3">
                                <a href="/api/reviews" class="text-decoration-none">
                                    <i class="fas fa-code me-1"></i>API JSON 보기
                                </a>
                            </span>
                    </small>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 삭제 확인 모달 -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">
                    <i class="fas fa-exclamation-triangle text-warning me-2"></i>리뷰 삭제 확인
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>정말로 <strong id="deleteMusicTitle"></strong>에 대한 리뷰를 삭제하시겠습니까?</p>
                <p class="text-muted small">이 작업은 되돌릴 수 없습니다.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-danger" id="confirmDeleteBtn">
                    <i class="fas fa-trash me-1"></i>삭제
                </button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // 테스트용 간단한 스크립트
    console.log('내 리뷰 목록 페이지 로드됨');
    console.log('내 리뷰 개수:', ${myReviewList.size()});

    let reviewToDelete = null;

    // 리뷰 수정
    function editReview(reviewId) {
        alert('테스트용: 리뷰 수정 기능 (실제로는 수정 폼으로 이동)');
        console.log('수정할 리뷰 ID:', reviewId);
        // 실제 구현에서는 수정 페이지로 이동
        // window.location.href = '/reviews/' + reviewId + '/edit';
    }

    // 리뷰 삭제
    function deleteReview(reviewId, musicTitle) {
        reviewToDelete = reviewId;
        document.getElementById('deleteMusicTitle').textContent = musicTitle;

        const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
        deleteModal.show();
    }

    // 삭제 확인
    document.getElementById('confirmDeleteBtn').addEventListener('click', function() {
        if (reviewToDelete) {
            alert('테스트용: 리뷰 삭제 (실제로는 API 호출)');
            console.log('삭제할 리뷰 ID:', reviewToDelete);

            // 실제 구현에서는 AJAX로 DELETE 요청
            /*
            fetch('/api/reviews/' + reviewToDelete, {
                method: 'DELETE'
            }).then(response => {
                if (response.ok) {
                    location.reload();
                }
            });
            */

            const deleteModal = bootstrap.Modal.getInstance(document.getElementById('deleteModal'));
            deleteModal.hide();
            reviewToDelete = null;
        }
    });

    // 이미지 로드 에러 처리
    document.querySelectorAll('.music-cover').forEach(img => {
        img.onerror = function() {
            this.src = 'https://via.placeholder.com/80x80?text=No+Image';
        };
    });
</script>
</body>
</html>
