document.getElementById("registration-form").addEventListener("submit", async (event) => {
  event.preventDefault();
  
  // Collect all form data
  const username = document.getElementById("username").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  const role = document.getElementById("role").value;
  const address = document.getElementById("address").value;
  const phoneNumber = document.getElementById("phone").value;

  // Prepare request payload
  const requestBody = {
      username,
      email,
      password,
      role,
      address,
      phoneNumber
  };

  try {
      const response = await fetch("http://localhost:8080/api/users", {
          method: "POST",
          headers: {
              "Content-Type": "application/json"
          },
          body: JSON.stringify(requestBody)
      });

      if (response.ok) {
          alert("Registration successful!");
          window.location.href = "login.html";
      } else {
          const errorData = await response.json();
          alert(`Registration failed: ${errorData.message || "Unknown error"}`);
      }
  } catch (error) {
      console.error("Error during registration:", error);
      alert("An error occurred. Please try again.");
  }
});