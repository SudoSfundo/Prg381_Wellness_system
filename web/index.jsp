<%-- 
    Document   : index
    Created on : 10 Jul 2025, 10:06:43
    Author     : janco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BC Student Wellness - Home</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body class="dark-mode">
<button id="theme-toggle" class="theme-toggle-btn" aria-label="Toggle light/dark mode">
    <span id="theme-icon">🌙</span>
</button>
<header class="main-header">
    <div class="main-header-left">
        <h1 class="campus-title">Belgium Campus</h1>
        <h2 class="wellness-subtitle">Student Wellness</h2>
    </div>
    <div class="main-header-right">
        <div class="user-status" id="loginStatus">
            Not Logged In
        </div>
    </div>
</header>
<main class="home-main">
    <section class="welcome-section">
        <h1>Welcome to Student Wellness</h1>
        <p>Your mental health and well-being matter. Access counseling services, book appointments, and take control of your wellness journey at Belgium Campus.</p>
        <div class="home-links">
            <a href="login.jsp"><button type="button" class="home-btn">Sign In</button></a>
            <a href="register.jsp"><button type="button" class="home-btn-secondary">Sign Up</button></a>
        </div>
    </section>
</main>
<script>
const themeToggle = document.getElementById('theme-toggle');
const themeIcon = document.getElementById('theme-icon');
themeToggle.onclick = function() {
    document.body.classList.toggle('dark-mode');
    themeIcon.textContent = document.body.classList.contains('dark-mode') ? '🌙' : '☀️';
};
</script>
</body>
</html>
