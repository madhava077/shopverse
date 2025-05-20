document.addEventListener('DOMContentLoaded', () => {
  const loginForm = document.querySelector('form');

  loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const email = loginForm.email.value.trim();
    const password = loginForm.password.value;

    if (!email || !password) {
      alert('Please enter both email and password.');
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/api/users/login/${encodeURIComponent(email)}/${encodeURIComponent(password)}`, {
        method: 'GET',
        headers: {
          'Accept': 'application/json'
        }
      });

      if (!response.ok) {
        alert('Login failed: Invalid email or password.');
        return;
      }

      const user = await response.json();

      alert(`Login successful! Welcome, ${user.username} (${user.role})`);
      
      // Redirect based on role
      if (user.role === 'ADMIN') {
        window.location.href = 'admin-dashboard.html';  // replace with your admin page
      } else if (user.role === 'DELIVERY') {
        window.location.href = 'delivery-dashboard.html'; // replace with your delivery page
      } else {
        window.location.href = 'index.html'; // customer or default homepage
      }

    } catch (error) {
      console.error('Login error:', error);
      alert('An error occurred during login. Please try again.');
    }
  });
});
