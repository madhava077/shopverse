document.addEventListener("DOMContentLoaded", () => {
    fetch("http://localhost:8080/api/products")
        .then(response => response.json())
        .then(products => {
            const productList = document.getElementById("product-list");
            products.forEach(product => {
                const productDiv = document.createElement("div");
                productDiv.innerHTML = `
                    <h3>${product.productname}</h3>
                    <p>${product.description}</p>
                    <p>Price: $${product.price}</p>
                    <button onclick="addToCart(${product.id})">Add to Cart</button>
                `;
                productList.appendChild(productDiv);
            });
        });
});

function addToCart(productId) {
    console.log("Product added to cart:", productId);
    // Implement cart addition logic here
}