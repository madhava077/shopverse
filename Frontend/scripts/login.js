document.getElementById("login-form").addEventListener("submit", async (event) => {
  event.preventDefault();
  
  // Collect email and password
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  try {
      // Send the login request to the backend
      const response = await fetch(`http://localhost:8080/api/users/login/${email}/${password}`, {
          method: "GET",
      });

      if (response.ok) {
          const userData = await response.json(); // Assuming the backend sends user details as a response
          
          // Store user details in localStorage
          localStorage.setItem("user", JSON.stringify(userData));

          // Check the user role and redirect accordingly
          switch (userData.role) {
              case "Customer":
                  window.location.href = "customer.html"; // Redirect to the customer page
                  break;
              case "User":
                  window.location.href = "management.html"; // Redirect to the management page
                  break;
              case "Delivery":
                  window.location.href = "delivery.html"; // Redirect to the delivery page
                  break;
              default:
                  alert("Invalid role. Please contact support.");
          }
      } else {
          alert("Invalid email or password.");
      }
  } catch (error) {
      console.error("Error during login:", error);
      alert("An error occurred. Please try again.");
  }
});