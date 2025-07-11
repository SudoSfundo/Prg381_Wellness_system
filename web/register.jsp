<%-- 
    Document   : register
    Created on : 10 Jul 2025, 10:15:10
    Author     : janco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Student Wellness</title>
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
                <h1>Welcome!</h1>
                <p>Register to access student wellness services at Belgium Campus.</p>
                <span>Already have an account?</span>
                <a href="login.jsp"><button type="button">Sign In</button></a>
            </div>
        </div>
        <!-- Right Side (Form) -->
        <div class="split-form">
            <form id="registerForm" action="RegisterServlet" method="post">
                <h1>Create Account</h1>
                <div class="form-group">
                    <label for="register-name">Name:</label>
                    <input
                        id="register-name"
                        name="name"
                        placeholder="John Doe"
                        class="input-field"
                        required
                        pattern="^[A-Za-z]+ [A-Za-z]+$"
                        title="Please enter your name and surname separated by a space"
                    />
                </div>
                <div class="form-group">
                    <label for="register-email">Email:</label>
                    <input
                        id="register-email"
                        name="email"
                        type="email"
                        placeholder="123456@student.belgiumcampus.ac.za"
                        class="input-field"
                        required
                        pattern="^[0-9]{6}@student\.belgiumcampus\.ac\.za$"
                        title="Email must start with 6 digits and end with @student.belgiumcampus.ac.za"
                    />
                </div>
                <div class="form-group">
                    <label for="register-password">Password:</label>
                    <input
                        id="register-password"
                        name="password"
                        type="password"
                        placeholder="••••••••"
                        class="input-field"
                        required
                        minlength="8"
                        title="Password must be at least 8 characters long"
                    />
                </div>
                <button type="submit">Sign Up</button>
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