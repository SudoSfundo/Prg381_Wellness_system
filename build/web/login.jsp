<%-- 
    Document   : login
    Created on : 10 Jul 2025, 10:06:43
    Author     : janco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Student Wellness</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body class="dark-mode">
<button id="theme-toggle" class="theme-toggle-btn" aria-label="Toggle light/dark mode">
    <span id="theme-icon">🌙</span>
</button>
<div class="center-wrapper">
    <div class="split-container">
        <!-- Left Side (Overlay) -->
        <div class="split-overlay">
            <div class="split-overlay-content">
                <h1>Welcome Back!</h1>
                <p>Log in to access student wellness services at Belgium Campus.</p>
                <span>Don't have an account?</span>
                <a href="register.jsp"><button type="button">Sign Up</button></a>
            </div>
        </div>
        <!-- Right Side (Form) -->
        <div class="split-form">
            <form id="loginForm" action="LoginServlet" method="post">
                <h1>Login</h1>
                <% if (request.getParameter("error") != null) { %>
                    <div class="error-message"><%= request.getParameter("error") %></div>
                <% } else if (request.getAttribute("error") != null) { %>
                    <div class="error-message"><%= request.getAttribute("error") %></div>
                <% } %>
                <div class="form-group">
                    <label for="login-email">Email:</label>
                    <input id="login-email" name="email" type="email" placeholder="Email" class="input-field" required />
                </div>
                <div class="form-group">
                    <label for="login-password">Password:</label>
                    <input id="login-password" name="password" type="password" placeholder="Password" class="input-field" required minlength="8" title="Password must be at least 8 characters long" />
                </div>
                <button type="submit">Log In</button>
            </form>
        </div>
    </div>
</div>
<script>
const themeToggle = document.getElementById('theme-toggle');
const themeIcon = document.getElementById('theme-icon');
themeToggle.onclick = function() {
    document.body.classList.toggle('dark-mode');
    if(document.body.classList.contains('dark-mode')) {
        themeIcon.textContent = '🌙';
    } else {
        themeIcon.textContent = '☀️';
    }
};
</script>
</body>
</html>