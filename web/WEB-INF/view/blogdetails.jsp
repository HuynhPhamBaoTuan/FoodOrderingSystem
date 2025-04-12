<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Account" %>
<%@ page import="model.Comment" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Account user = (Account) session.getAttribute("loggedUser");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Foodie Blog</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/font/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/css/blog.css">
        <link rel="stylesheet" href="./assets/css/modal.css">
        <link rel="stylesheet" href="./assets/css/blogdetails.css">
        <script src="./js/blog.js"></script>
    </head>
    <body>
        <!-- HEADER -->
        <div id="header">
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/OrderingSystem/">FOODIE</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-link" href="/OrderingSystem/">Home</a>
                            <a class="nav-link" href="/OrderingSystem/food">Food</a>
                            <a class="nav-link" href="/OrderingSystem/restaurant">Restaurant</a>
                            <a class="nav-link" href="/OrderingSystem/blog">Blog</a>
                        </div>
                        <div class="navbar__item">
                            <nav id="nav-bar">
                                <ul class="nav-list">
                                    <li class="navbar__item--login">
                                        <a href="<%= user != null ? "account" : "login" %>" class="nav-link">
                                            <c:choose>
                                                <c:when test="${user != null}">
                                                    <div class="user-dropdown">
                                                        <a style="text-decoration: none" href="/OrderingSystem/account">
                                                            <img id="user-avatar" class="img-responsive img-circle" src="${user.getAvtImg()}" onerror="this.onerror=null;this.src='https://i.pinimg.com/564x/09/a9/2c/09a92c1cbe440f31d1818e4fe0bcf23a.jpg';" alt="Profile Picture">
                                                            <span id="user-name">${user.getUserName()}</span>
                                                        </a>
                                                        <div class="dropdown-content">
                                                            <a href="/OrderingSystem/account">Account</a>
                                                            <a href="/OrderingSystem/logout">Logout</a>
                                                        </div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <span id="login-text">Login</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </a>
                                    </li>
                                </ul>
                            </nav>

                            <a style="text-decoration: none" href = "./cart"><div class="icon">
                                    <i class="ti-shopping-cart"></i>
                                </div></a>
                            <a href = "./favourite">
                                <div class="icon">
                                    <i class="ti-heart" style="margin: 0 10px 0 10px; padding:10px; font-size: 25px; border-radius: 25px;
                                       background-color: #ff6b6b; display: inline-block;color: white; text-align: center;"></i>
                                </div>
                            </a>               

                        </div>
                    </div>
                </div>
            </nav>
        </div>

        <!-- PAGE INFO -->
        <div id="page-info">
            <div class="page-title">Foodie Community Blog</div>
        </div>

        <!-- BLOG DETAILS -->
        <div id="blog-details">
            <c:if test="${not empty post}">
                <div class="blog-details-title">
                    <h2>${post.heading}</h2>
                    <c:if test="${user.userID == post.userID}">
                        <div class="dropdown-container">
                            <i class="ti-more-alt" id="more-options"></i>
                            <div class="dropdown-menu" id="dropdown-menu" style="display:none;">
                                <a id="openModalBtn"><i class="ti-pencil"> Edit</i></a>
                                <div id="myModal" class="modal">
                                    <div class="modal-content">
                                        <span class="close">&times;</span>
                                        <form action="postDetails" method="POST" enctype="multipart/form-data">
                                            <h2>Edit Blog Post Id: ${post.postID}</h2>
                                            <label for="imgPost">Picture Cover:</label>
                                            <input name="imgPost" type="file" class="input-field" required><br>
                                            <label for="title">Heading:</label>
                                            <input name="title" type="text" required class="input-field" value="${post.heading}"><br>
                                            <label for="description">Description:</label>
                                            <textarea id="default" name="description">${post.content}</textarea><br>
                                            <input type="hidden" name="action" value="editPost">
                                            <input type="hidden" name="postID" value="${post.postID}">
                                            <input type="hidden" name="userID" value="${user.userID}">
                                            <button type="submit" class="submit-button">Submit</button>
                                        </form>
                                    </div>
                                </div>
                                <a href="#" id="delete" onclick="confirmDelete(${post.getPostID()})"><i class="ti-trash"> Delete</i></a>
                            </div>
                        </div>
                        <!-- Hidden form for deletion -->
                        <form id="deleteFormPost" action="postDetails" method="POST" style="display: none;">
                            <input type="hidden" name="action" value="deletePost">
                            <input type="hidden" name="postID" id="postID" value="${post.getPostID()}">
                        </form>
                    </c:if>
                </div>
                <div class="blog-details-author">
                    <img src="${avtURL}" alt="Author Avatar" onerror="this.onerror=null;this.src='https://i.pinimg.com/564x/09/a9/2c/09a92c1cbe440f31d1818e4fe0bcf23a.jpg';" class="details-avatar">
                    <p class="details-name">${fullName}</p>
                    <div class="blog-details-date">${post.createdDate}</div>
                </div>
                <div class="blog-content">
                    <img src="${post.imgURL}" alt="Post Image" class="blog-content-img">
                    <p>${post.content}</p>
                </div>
            </c:if>
            <c:if test="${empty post}">
                <p>This post is not available.</p>
            </c:if>
        </div>

        <!-- COMMENT SECTION -->
        <div class="comment-container">
            <h2 style="color: #333333; padding: 20px;">Comment Section</h2>
            <form action="postDetails" method="POST">
                <div class="comment-box">
                    <textarea name="commentInput" rows="4" placeholder="How do you feel? " required></textarea><br><br>
                    <input type="hidden" name="postID" value="${post.postID}">
                    <input type="hidden" name="userID" value="${user.userID}">
                    <input type="hidden" name="action" value="addComment">
                    <input type="submit" value="Add Comment" class="btn btn-primary">
                </div>
            </form>

            <!-- Display Comments Section -->
            <div class="comments-display" id="commentsDisplay">
                <c:if test="${not empty comments}">
                    <c:if test="${not empty comments}">
                        <c:forEach var="comment" items="${comments}">
                            <div class="comment-section" id="comment-${comment.commentID}">
                                <img src="${comment.userAvtURL}" alt="Avatar" class="comment-avt" 
                                     onerror="this.onerror=null;this.src='https://i.pinimg.com/564x/09/a9/2c/09a92c1cbe440f31d1818e4fe0bcf23a.jpg';">
                                <div class="comment-content">
                                    <div>
                                        <div class="comment-author">${comment.userFullName}</div>
                                        <div class="comment-time">${comment.createdDate}</div>
                                    </div>
                                    <div class="comment">${comment.content}</div>
                                </div>
                                <c:if test="${user.userID == post.userID || user.userID == comment.userID}">
                                    <div class="comment-options">
                                        <c:if test="${user.userID == comment.userID}">
                                            <a href="javascript:void(0);" class="edit-comment-option" 
                                               onclick="openEditCommentModal(${comment.commentID}, '${comment.content}')">
                                                <i class="ti-pencil"> Edit</i>
                                            </a>
                                        </c:if>
                                        <a href="javascript:void(0);" class="delete-comment-option" 
                                           onclick="confirmDeleteComment(${comment.commentID})">
                                            <i class="ti-trash"> Delete</i>
                                        </a>
                                    </div>
                                </c:if>
                            </div>
                        </c:forEach>
                    </c:if>
                    <div id="edit-comment-modal" class="edit-comment-modal modal" style="display:none;">
                        <div class="modal-content modal-content-comment">
                            <span class="close-modal">&times;</span>
                            <form id="edit-comment-form" action="postDetails" method="POST">
                                <h2>Edit Comment</h2>
                                <textarea id="edit-comment-content" name="commentContent" rows="4" required></textarea><br><br>
                                <input type="hidden" name="postID" value="${post.postID}">
                                <input type="hidden" name="userID" value="${user.userID}">
                                <input type="hidden" id="edit-comment-id" name="commentID">
                                <input type="hidden" name="action" value="editComment">
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty comments}">
                    <div class="no-comments">No comments yet. Be the first to comment!</div>
                </c:if>
            </div>
        </div>

        <!-- AD BLOG -->
        <img src="./assets/img/adblog.svg" alt="" class="blog-ad">

        <!-- FOOTER -->
        <footer id="footer">
            <div class="footer-content">
                <div class="footer-logo">
                    <h2>FOODIE</h2>
                    <div class="footer-social">
                        <a class="icon-footer" href="#"><i class="ti-facebook"></i></a>
                        <a class="icon-footer" href="#"><i class="ti-instagram"></i></a>
                        <a class="icon-footer" href="#"><i class="ti-location-pin"></i></a>
                    </div>
                </div>
                <div class="footer-menu">
                    <h4 style="display: flex; justify-content: center">MENU</h4>
                    <div class="menu-item">
                        <ul>
                            <li><a href="#">About</a></li>
                            <li><a href="#">Restaurants</a></li>
                        </ul>
                        <ul>
                            <li><a href="#">Map</a></li>
                            <li><a href="#">Submit</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>

        <script>
            const modal = document.getElementById("myModal");
            const btn = document.getElementById("openModalBtn");
            const span = document.getElementsByClassName("close")[0];

            btn.onclick = function () {
                modal.style.display = "block";
            }

            span.onclick = function () {
                modal.style.display = "none";
            }

            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

            function confirmDelete(postID) {
                const confirmAction = confirm("Are you sure you want to delete this post?");
                if (confirmAction) {
                    document.getElementById('postID').value = postID;
                    document.getElementById('deleteFormPost').submit();
                }
            }

            document.getElementById("more-options").addEventListener("click", function (event) {
                event.stopPropagation(); // Prevent window click from firing
                const dropdown = document.getElementById("dropdown-menu");
                dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
            });

            window.addEventListener("click", function (event) {
                const dropdown = document.getElementById("dropdown-menu");
                if (dropdown.style.display === "block" && !event.target.closest('.dropdown-container')) {
                    dropdown.style.display = "none";
                }
            });

            //Comment section
            function openEditCommentModal(commentID, commentContent) {
                document.getElementById("edit-comment-content").value = commentContent; 
                document.getElementById("edit-comment-id").value = commentID; 
                document.getElementById("edit-comment-modal").style.display = "block"; 
            }

            document.querySelector(".close-modal").onclick = function () {
                document.getElementById("edit-comment-modal").style.display = "none"; 
            }

            function confirmDeleteComment(commentID) {
                console.log(`Attempting to delete comment with ID: ${commentID}`); 
                if (confirm("Are you sure you want to delete this comment?")) {
                    const deleteForm = document.createElement("form");
                    deleteForm.method = "POST";
                    deleteForm.action = "postDetails";

                    const postIdField = document.createElement("input");
                    postIdField.type = "hidden";
                    postIdField.name = "postID";
                    postIdField.value = document.getElementsByName("postID")[0].value; 

                    const commentIdField = document.createElement("input");
                    commentIdField.type = "hidden";
                    commentIdField.name = "commentID";
                    commentIdField.value = commentID;

                    const actionField = document.createElement("input");
                    actionField.type = "hidden";
                    actionField.name = "action";
                    actionField.value = "deleteComment";

                    deleteForm.appendChild(postIdField);
                    deleteForm.appendChild(commentIdField);
                    deleteForm.appendChild(actionField);

                    document.body.appendChild(deleteForm); 
                    deleteForm.submit(); 
                }
            }
        </script>

        <script src="./tinymce/tinymce.min.js"></script>
        <script src="./assets/js/tinymceConfig.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </body>
</html>
