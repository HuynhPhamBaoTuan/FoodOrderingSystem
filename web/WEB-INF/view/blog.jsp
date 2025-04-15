<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Account" %>
<%@ page import="context.PostDAO" %>
<%@ page import="context.AccountDAO" %>
<%@ page import="model.Post" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Account user = (Account) session.getAttribute("loggedUser");
    PostDAO postDAO = new PostDAO();
    AccountDAO userDAO = new AccountDAO();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Foodie Blog</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="./assets/font/themify-icons/themify-icons.css" />
        <link rel="stylesheet" href="./assets/css/style.css" />
        <link rel="stylesheet" href="./assets/css/blog.css" />
        <link rel="stylesheet" href="./assets/css/modal.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="./assets/js/blog.js"></script>
    </head>
    <body>
        <!-- HEADER -->
        <div id="header">
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/OrderingSystem/">FOODIE</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-link" aria-current="page" href="/OrderingSystem/">Home</a>
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
                                                            <img 
                                                                id="user-avatar" 
                                                                class="img-responsive img-circle" 
                                                                src="${user.getAvtImg()}" 
                                                                onerror="this.onerror=null;this.src='https://i.pinimg.com/564x/09/a9/2c/09a92c1cbe440f31d1818e4fe0bcf23a.jpg';"
                                                                alt="Profile Picture" 
                                                                />
                                                            <span id="user-name">${user.getUserName()}</span>
                                                        </a>
                                                        <div class="dropdown-content">
                                                            <a href="/OrderingSystem/account">Account</a>
                                                            <a href="/OrderingSystem/order-history">View Orders</a>
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
                            <a style="text-decoration: none" href="./cart">
                                <div class="icon"><i class="ti-shopping-cart"></i></div>
                            </a>
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
            <div class="page-info-more">
                <c:choose>
                    <c:when test="${user.role != 1 && !empty user.role}">
                        <a href="./">Home</a>
                        <a style="border-left: 1px solid #e8e8ea" href="#">Post Management</a>
                        <c:choose>
                            <c:when test="${user != null}">
                                <button id="openModalBtn" class="submit-button">Create post</button>
                            </c:when>
                            <c:otherwise>
                                <button class="openModalBtn" onclick="alert('Please log in to create a post.');">Create post</button>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <!-- Other content for non-authorized users -->
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <!-- SLIDER IMG -->
        <c:choose>
            <c:when test="${newPost != null}">
                <div id="slider">
                    <img class="slider-img" src="${newPost.getImgURL()}" alt="Slider" />
                    <div class="title">
                        <h2 style="color: white; width: 90%">${newPost.getHeading()}</h2>
                        <div class="slider-author">
                            <!--<img src="${newPost.getAvtUserImg()}" alt="" class="author_avatar" />-->
                            <p class="author_name">${fullNameNewPost}</p>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <p>No posts available.</p>
            </c:otherwise>
        </c:choose>

        <!-- CONTENT -->
        <div id="content">
            <div class="post-row content-load">
                <c:forEach var="post" items="${postList}">
                    <a class="card card-post content" href="/OrderingSystem/postDetails?postID=${post.getPostID()}" style="width: 30%">
                        <img class="card-img-top card-img-post" src="${post.getImgURL()}" alt="Post image" />
                        <div class="card-body">
                            <h5 class="card-title card-title-post">${post.getHeading()}</h5>
                            <div class="card-post-author">
                                <img src="${post.getAvtUserImg()}" onerror="this.onerror=null;this.src='https://i.pinimg.com/564x/09/a9/2c/09a92c1cbe440f31d1818e4fe0bcf23a.jpg';" alt="Author Avatar" class="author-post-avatar" />
                                <p class="author_name author-post-name">${post.getUserFullName()}</p>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>

        <a href="#" id="loadMore">Load More</a>

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

        <!-- Modal Structure -->
        <div id="myModal" class="modal">
            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="postDetails" method="POST" enctype="multipart/form-data">
                    <h2>Add Blog Post</h2>
                    <label for="imgPost">Picture Cover:</label>
                    <input name="imgPost" type="file" class="input-field" required/><br>
                    <label for="title">Heading:</label>
                    <input name="title" type="text" required class="input-field"/><br>
                    <label for="description">Description:</label>
                    <input type="hidden" name="userID" value="${user.getUserID()}"/>
                    <textarea id="default" name="description"></textarea><br>
                    <input type="hidden" name="action" value="addPost">
                    <button type="submit" class="submit-button">Submit</button>
                </form>
            </div>
        </div>
        <script>

            document.addEventListener("DOMContentLoaded", function () {
                let loadMoreButton = document.getElementById("loadMore");
                let posts = document.querySelectorAll(".post-row .card-post");
                let currentPosts = 6;

                for (let i = currentPosts; i < posts.length; i++) {
                    posts[i].style.display = "none";
                }

                loadMoreButton.addEventListener("click", function (e) {
                    e.preventDefault();
                    let nextPosts = currentPosts + 6;
                    for (let i = currentPosts; i < nextPosts && i < posts.length; i++) {
                        posts[i].style.display = "flex";
                    }
                    currentPosts += 6;

                    if (currentPosts >= posts.length) {
                        loadMoreButton.style.display = "none";
                    }
                });
            });
        </script>
        <script src="./assets/js/blog.js"></script>
        <script src="./tinymce/tinymce.min.js"></script>
        <script src="./assets/js/tinymceConfig.js"></script>
    </body>
</html>
