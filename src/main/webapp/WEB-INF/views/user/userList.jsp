<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User API Test</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h2>📄 User API Test Page</h2>


<h3>모든 사용자 조회</h3>
<button onclick="getAllUsers()">모든 사용자 조회</button>
<pre id="allUsersResult"></pre>


<h3>특정 사용자 조회</h3>
<input type="number" id="getUserId" placeholder="User ID">
<button onclick="getUserById()">조회</button>
<pre id="userByIdResult"></pre>


<h3>사용자 생성</h3>
<input type="text" id="newUsername" placeholder="Username">
<button onclick="createUser()">생성</button>
<pre id="createUserResult"></pre>


<h3>사용자 수정</h3>
<input type="number" id="updateUserId" placeholder="User ID">
<input type="text" id="updateUsername" placeholder="New Username">
<button onclick="updateUser()">수정</button>
<pre id="updateUserResult"></pre>


<h3>사용자 삭제</h3>
<input type="number" id="deleteUserId" placeholder="User ID">
<button onclick="deleteUser()">삭제</button>
<pre id="deleteUserResult"></pre>


<h3>특정 유저의 리뷰 조회</h3>
<input type="number" id="reviewUserId" placeholder="User ID">
<button onclick="getUserReviews()">리뷰 조회</button>
<pre id="userReviewsResult"></pre>

<script>
    function getAllUsers() {
        $.get("/api/users", function (data) {
            $("#allUsersResult").text(JSON.stringify(data, null, 2));
        });
    }

    function getUserById() {
        const id = $("#getUserId").val();
        $.get("/api/users/" + id, function (data) {
            $("#userByIdResult").text(JSON.stringify(data, null, 2));
        });
    }

    function createUser() {
        const username = $("#newUsername").val();
        $.ajax({
            type: "POST",
            url: "/api/users",
            contentType: "application/json",
            data: JSON.stringify({ userName: username }),
            success: function (res) {
                $("#createUserResult").text(res);
            }
        });
    }

    function updateUser() {
        const id = $("#updateUserId").val();
        const username = $("#updateUsername").val();
        $.ajax({
            type: "PUT",
            url: "/api/users/" + id,
            contentType: "application/json",
            data: JSON.stringify({ userName: username }),
            success: function (res) {
                $("#updateUserResult").text(res);
            }
        });
    }

    function deleteUser() {
        const id = $("#deleteUserId").val();
        $.ajax({
            type: "DELETE",
            url: "/api/users/" + id,
            success: function (res) {
                $("#deleteUserResult").text(res);
            }
        });
    }

    function getUserReviews() {
        const id = $("#reviewUserId").val();
        $.get("/api/users/" + id + "/reviews", function (data) {
            $("#userReviewsResult").text(JSON.stringify(data, null, 2));
        });
    }
</script>

</body>
</html>
