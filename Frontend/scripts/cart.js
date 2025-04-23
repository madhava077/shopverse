document.addEventListener("DOMContentLoaded", () => {
    // Fetch cart items from backend
    fetch("http://localhost:8080/api/cart")
        .then(response => response.json())
        .then(cartItems => {
            const cartItemsDiv = document.getElementById("cart-items");
            cartItems.forEach(item => {
                const itemDiv = document.createElement("div");
                itemDiv.innerHTML = `
                    <h3>${item.product.productname}</h3>
                    <p>Quantity: ${item.quantity}</p>
                    <p>Price: $${item.product.price}</p>
                `;
                cartItemsDiv.appendChild(itemDiv);
            });
        });
});

document.getElementById("checkout-button").addEventListener("click", () => {
    console.log("Proceed to checkout");
    // Implement checkout logic here
});