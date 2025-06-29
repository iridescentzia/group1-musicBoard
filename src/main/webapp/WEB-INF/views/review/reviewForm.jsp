<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 작성 - 음악 후기 게시판</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .form-container {
            max-width: 800px;
            margin: 0 auto;
        }
        .music-option {
            display: flex;
            align-items: center;
            padding: 10px;
            border: 1px solid #dee2e6;
            border-radius: 8px;
            margin-bottom: 10px;
            cursor: pointer;
            transition: all 0.2s ease;
        }
        .music-option:hover {
            background-color: #f8f9fa;
            border-color: #007bff;
        }
        .music-option.selected {
            background-color: #e3f2fd;
            border-color: #007bff;
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
        }
        .music-cover {
            width: 60px;
            height: 60px;
            object-fit: cover;
            border-radius: 6px;
            margin-right: 15px;
        }
        .rating-stars {
            font-size: 1.5rem;
            color: #ddd;
            cursor: pointer;
            transition: color 0.2s ease;
        }
        .rating-stars.active,
        .rating-stars:hover {
            color: #ffc107;
        }
        .star-rating {
            display: flex;
            gap: 5px;
            margin: 10px 0;
        }
        .preview-section {
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            border-radius: 8px;
            padding: 20px;
            margin-top: 20px;
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
                    <li class="breadcrumb-item active">리뷰 작성</li>
                </ol>
            </nav>
            <h2>
                <i class="fas fa-pen-alt me-2"></i>리뷰 작성
            </h2>
            <hr>
        </div>
    </div>

    <!-- 리뷰 작성 폼 -->
    <div class="form-container">
        <form id="reviewForm" action="/api/reviews" method="post">
            <input type="hidden" name="userId" value="${userId}" />
            <input type="hidden" id="selectedMusicId" name="musicId" />
            <input type="hidden" id="selectedRating" name="rating" />

            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0">
                        <i class="fas fa-music me-2"></i>음악 선택
                    </h5>
                </div>
                <div class="card-body">
                    <div class="mb-3">
                        <input type="text" id="musicSearch" class="form-control"
                               placeholder="음악 제목이나 아티스트로 검색...">
                    </div>
                    <div id="musicList" style="max-height: 400px; overflow-y: auto;">
                        <c:forEach var="music" items="${musicList}">
                            <div class="music-option" data-music-id="${music.id}"
                                 data-title="${music.title}" data-artist="${music.artist}">
                                <c:if test="${not empty music.coverUrl}">
                                    <img src="${music.coverUrl}" class="music-cover" alt="앨범 커버"
                                         onerror="this.src='https://via.placeholder.com/60x60?text=No+Image'">
                                </c:if>
                                <div class="flex-grow-1">
                                    <h6 class="mb-1">${music.title}</h6>
                                    <p class="text-muted mb-1">${music.artist}</p>
                                    <span class="badge bg-secondary">${music.genre}</span>
                                </div>
                                <div class="ms-auto">
                                    <i class="fas fa-circle-check text-primary d-none"></i>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="card mt-4">
                <div class="card-header">
                    <h5 class="mb-0">
                        <i class="fas fa-star me-2"></i>평점
                    </h5>
                </div>
                <div class="card-body">
                    <div class="star-rating" id="starRating">
                        <i class="fas fa-star rating-stars" data-rating="1"></i>
                        <i class="fas fa-star rating-stars" data-rating="2"></i>
                        <i class="fas fa-star rating-stars" data-rating="3"></i>
                        <i class="fas fa-star rating-stars" data-rating="4"></i>
                        <i class="fas fa-star rating-stars" data-rating="5"></i>
                    </div>
                    <small class="text-muted">별을 클릭하여 평점을 선택하세요 (1~5점)</small>
                </div>
            </div>

            <div class="card mt-4">
                <div class="card-header">
                    <h5 class="mb-0">
                        <i class="fas fa-comment me-2"></i>리뷰 내용
                    </h5>
                </div>
                <div class="card-body">
                        <textarea id="reviewContent" name="content" class="form-control" rows="6"
                                  placeholder="이 음악에 대한 솔직한 후기를 작성해주세요..." required></textarea>
                    <div class="form-text">
                        <span id="charCount">0</span>/500자
                    </div>
                </div>
            </div>

            <!-- 미리보기 -->
            <div id="previewSection" class="preview-section d-none">
                <h6><i class="fas fa-eye me-2"></i>미리보기</h6>
                <div id="previewContent"></div>
            </div>

            <!-- 작성 버튼 -->
            <div class="mt-4 text-center">
                <button type="button" class="btn btn-secondary me-2" onclick="history.back()">
                    <i class="fas fa-arrow-left me-1"></i>취소
                </button>
                <button type="submit" id="submitBtn" class="btn btn-primary" disabled>
                    <i class="fas fa-paper-plane me-1"></i>리뷰 작성
                </button>
            </div>
        </form>
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
    let selectedMusicId = null;
    let selectedRating = 0;

    // 음악 선택
    document.querySelectorAll('.music-option').forEach(option => {
        option.addEventListener('click', function() {
            // 이전 선택 해제
            document.querySelectorAll('.music-option').forEach(opt => {
                opt.classList.remove('selected');
                opt.querySelector('.fas.fa-circle-check').classList.add('d-none');
            });

            // 현재 선택
            this.classList.add('selected');
            this.querySelector('.fas.fa-circle-check').classList.remove('d-none');

            selectedMusicId = this.dataset.musicId;
            document.getElementById('selectedMusicId').value = selectedMusicId;

            updateSubmitButton();
            updatePreview();
        });
    });

    // 별점 선택
    document.querySelectorAll('.rating-stars').forEach(star => {
        star.addEventListener('click', function() {
            selectedRating = parseInt(this.dataset.rating);
            document.getElementById('selectedRating').value = selectedRating;

            // 별점 표시 업데이트
            document.querySelectorAll('.rating-stars').forEach((s, index) => {
                if (index < selectedRating) {
                    s.classList.add('active');
                } else {
                    s.classList.remove('active');
                }
            });

            updateSubmitButton();
            updatePreview();
        });
    });

    // 리뷰 내용 입력
    const reviewContent = document.getElementById('reviewContent');
    const charCount = document.getElementById('charCount');

    reviewContent.addEventListener('input', function() {
        const length = this.value.length;
        charCount.textContent = length;

        if (length > 500) {
            charCount.style.color = 'red';
        } else {
            charCount.style.color = '';
        }

        updateSubmitButton();
        updatePreview();
    });

    // 음악 검색
    document.getElementById('musicSearch').addEventListener('input', function() {
        const searchTerm = this.value.toLowerCase();
        document.querySelectorAll('.music-option').forEach(option => {
            const title = option.dataset.title.toLowerCase();
            const artist = option.dataset.artist.toLowerCase();

            if (title.includes(searchTerm) || artist.includes(searchTerm)) {
                option.style.display = 'flex';
            } else {
                option.style.display = 'none';
            }
        });
    });

    // 제출 버튼 활성화 체크
    function updateSubmitButton() {
        const submitBtn = document.getElementById('submitBtn');
        const content = reviewContent.value.trim();

        if (selectedMusicId && selectedRating > 0 && content.length > 0 && content.length <= 500) {
            submitBtn.disabled = false;
        } else {
            submitBtn.disabled = true;
        }
    }

    // 미리보기 업데이트
    function updatePreview() {
        const previewSection = document.getElementById('previewSection');
        const previewContent = document.getElementById('previewContent');

        if (selectedMusicId && selectedRating > 0 && reviewContent.value.trim()) {
            const selectedMusic = document.querySelector(`[data-music-id="${selectedMusicId}"]`);
            const stars = '★'.repeat(selectedRating) + '☆'.repeat(5 - selectedRating);

            const imgElement = selectedMusic.querySelector('img');
            const imgSrc = imgElement ? imgElement.src : '';

            previewContent.innerHTML = `
                    <div class="d-flex">
                        <div class="me-3">
                            <img src="` + imgSrc + `"
                                 width="50" height="50" class="rounded">
                        </div>
                        <div>
                            <h6>` + selectedMusic.dataset.title + ` - ` + selectedMusic.dataset.artist + `</h6>
                            <div class="text-warning">` + stars + ` (` + selectedRating + `/5)</div>
                            <p class="mt-2">` + reviewContent.value + `</p>
                        </div>
                    </div>
                `;
            previewSection.classList.remove('d-none');
        } else {
            previewSection.classList.add('d-none');
        }
    }

    // 폼 제출 (테스트용 - 실제로는 AJAX로 처리해야 함)
    document.getElementById('reviewForm').addEventListener('submit', function(e) {
        e.preventDefault();
        alert('테스트용: 실제 구현에서는 AJAX로 API 호출해야 합니다.');
        console.log('Music ID:', selectedMusicId);
        console.log('Rating:', selectedRating);
        console.log('Content:', reviewContent.value);
    });
</script>
</body>
</html>
