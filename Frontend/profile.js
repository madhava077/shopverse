document.addEventListener('DOMContentLoaded', async () => {
  const token = localStorage.getItem('token');
  const userId = localStorage.getItem('userid');
  if (!token || !userId) {
    alert('Please login to view your profile.');
    window.location.href = 'login.html';
    return;
  }

  const profileForm = document.getElementById('profileForm');
  // Fetch user details
  try {
    const res = await fetch(`http://localhost:8080/api/users/details/${userId}`, {
      headers: {
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + token
      }
    });
    if (!res.ok) throw new Error('Could not load user details');
    const user = await res.json();
    profileForm.username.value = user.username || '';
    profileForm.email.value = user.email || '';
    profileForm.role.value = user.role || '';
    profileForm.address.value = user.address || '';
    profileForm.phoneNumber.value = user.phoneNumber || '';
  } catch (err) {
    alert('Failed to load profile: ' + err.message);
  }

  // Update user details
  profileForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const updatedUser = {
      username: profileForm.username.value.trim(),
      email: profileForm.email.value.trim(),
      address: profileForm.address.value.trim(),
      phoneNumber: profileForm.phoneNumber.value.trim(),
      role: profileForm.role.value.trim()
    };
    try {
      const res = await fetch(`http://localhost:8080/api/users/${userId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + token
        },
        body: JSON.stringify(updatedUser)
      });
      if (!res.ok) {
        const errMsg = await res.text();
        throw new Error(errMsg || 'Update failed');
      }
      alert('Profile updated successfully!');
      window.location.reload();
    } catch (err) {
      alert('Update failed: ' + err.message);
    }
  });
});

function logout() {
  localStorage.removeItem('token');
  localStorage.removeItem('role');
  localStorage.removeItem('userid');
  window.location.href = "login.html";
}