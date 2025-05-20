document.addEventListener('DOMContentLoaded', () => {
  const registerForm = document.querySelector('form');

  registerForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const userData = {
      username: registerForm.username.value.trim(),
      email: registerForm.email.value.trim(),
      password: registerForm.password.value,
      role: registerForm.role.value.trim(),
      address: registerForm.address.value.trim(),
      phoneNumber: registerForm.phoneNumber.value.trim(),
    };

    try {
      const response = await fetch('http://localhost:8080/api/users/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
      });

      if (!response.ok) {
        const errorData = await response.json();
        alert('Registration failed: ' + (errorData.message || 'Unknown error'));
        return;
      }

      const createdUser = await response.json();
      alert('Registration successful! Welcome, ' + createdUser.username);
      window.location.href = 'login.html';

    } catch (error) {
      console.error('Error:', error);
      alert('An error occurred during registration. Please try again.');
    }
  });
});
