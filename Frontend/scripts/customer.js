// Fetch and display cart items
async function fetchCartItems() {
    const user = JSON.parse(localStorage.getItem("user")); // Get user details from localStorage
    if (!user) {
        alert("You are not logged in!");
        window.location.href = "login.html"; // Redirect to login if user details are missing
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/cart-items/${user.id}`); // Use user ID in the endpoint
        if (!response.ok) {
            throw new Error("Failed to fetch cart items");
        }
        const cartItems = await response.json();
        displayCartItems(cartItems);
    } catch (error) {
        console.error("Error fetching cart items:", error);
    }
}

// Confirm the order
document.getElementById("confirm-order-button").addEventListener("click", async () => {
    const user = JSON.parse(localStorage.getItem("user")); // Get user details from localStorage
    if (!user) {
        alert("You are not logged in!");
        window.location.href = "login.html"; // Redirect to login if user details are missing
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/orders/${user.id}`, { // Use user ID in the endpoint
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({}) // Assuming the backend associates the order with the logged-in user
        });

        if (response.ok) {
            alert("Order confirmed successfully!");
            fetchCartItems(); // Clear the cart after confirming the order
        } else {
            alert("Failed to confirm order.");
        }
    } catch (error) {
        console.error("Error confirming order:", error);
    }
});

// Initial fetch of cart items
fetchCartItems();