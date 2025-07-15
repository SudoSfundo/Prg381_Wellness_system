<%-- 
    Document   : dashboard
    Created on : 10 Jul 2025, 10:14:51
    Author     : janco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    if (session.getAttribute("name") == null ){
        response.sendRedirect("login.jsp?error=You should not be there yet. Please Log In");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Student Wellness</title>
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
        <div class="user-status logged-in" id="loginStatus">
            Logged in as <span id="userName"><%=session.getAttribute("name")%></span>
        </div>
    </div>
</header>
<main class="dashboard-main">
    <section class="dashboard-welcome">
        <h1>Welcome back, <span id="userNameMain"><%=session.getAttribute("name")%></span>!</h1>
        <p>Manage your wellness journey with our comprehensive services.</p>
    </section>
    <section class="dashboard-grid">
        <div class="dashboard-card">
            <h3>📅 Appointment Management</h3>
            <p>Book, reschedule, or cancel your counseling appointments with ease.</p>
            <a href="#" class="dashboard-btn" onclick="navigateToAppointments()">Manage Appointments</a>
        </div>
        <div class="dashboard-card">
            <h3>👥 Counselor Directory</h3>
            <p>Browse our qualified counselors and their specializations.</p>
            <a href="#" class="dashboard-btn" onclick="navigateToCounselors()">View Counselors</a>
        </div>
        <div class="dashboard-card">
            <h3>💬 Feedback & Support</h3>
            <p>Share your experience and help us improve our services.</p>
            <a href="#" class="dashboard-btn" onclick="navigateToFeedback()">Give Feedback</a>
        </div>
        <div class="dashboard-card">
            <h3>📊 My Wellness Dashboard</h3>
            <p>Track your appointments, view history, and monitor your progress.</p>
            <a href="#" class="dashboard-btn" onclick="navigateToProfile()">View Dashboard</a>
        </div>
    </section>
    <div class="dashboard-logout">
        <a href="#" class="dashboard-btn-secondary" onclick="logout()">Logout</a>
    </div>
</main>
<script>
const themeToggle = document.getElementById('theme-toggle');
const themeIcon = document.getElementById('theme-icon');
themeToggle.onclick = function() {
    document.body.classList.toggle('dark-mode');
    themeIcon.textContent = document.body.classList.contains('dark-mode') ? '🌙' : '☀️';
};
// Navigation functions (implement as needed)
function navigateToAppointments() { alert('Navigate to Appointments Management'); }
function navigateToCounselors() { alert('Navigate to Counselors Directory'); }
function navigateToFeedback() { alert('Navigate to Feedback System'); }
function navigateToProfile() { alert('Navigate to Personal Dashboard'); }
function logout() { window.location.href = 'LogoutServlet'; } 
</script>
</body>
</html>