<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Account" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Account user = (Account) session.getAttribute("user");
%>


<div id="header">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">

            <a class="navbar-brand" href="./">FOODIE</a>

            <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup"
                aria-expanded="false"
                aria-label="Toggle navigation"
                >
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

                            <li class="nav-item">

                                <!--<li class="navbar__item--login">-->

                                <a href="<%= user != null ? "account" : "login" %>" class="nav-link">
                                    <c:choose>
                                        <c:when test="${user != null}">
                                            <div class="user-dropdown">
                                                <a class ="d-flex" style="text-decoration: none" href="/OrderingSystem/account">
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

                    <a style="text-decoration: none" href = "./cart"><div class="icon">
                            <i class="ti-shopping-cart"></i>
                        </div></a>


                    <a href = "./favourite">
                        <div class="icon">
                            <i class="ti-heart"></i>
                        </div>
                    </a>


                </div>
            </div>
        </div>
    </nav>
</div>
