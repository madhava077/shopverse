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
  const response = await fetch('http://localhost:8080/api/users/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      email: email,
      password: password
    })
  });

  if (!response.ok) {
    alert('Login failed: Invalid email or password.');
    return;
  }

  const data = await response.json(); // Parse JSON response

  localStorage.setItem('token', data.token);
  localStorage.setItem('userid', data.userId);
  if (data.role === 'ADMIN') {
    localStorage.setItem('role', 'admin');
    window.location.href = 'admin-dashboard.html'; 
  }

  alert('Login successful! Welcome');
  
} catch (error) {
  console.error('Login error:', error);
  alert('An error occurred during login. Please try again.');
}
  });
});
