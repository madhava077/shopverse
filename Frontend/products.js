document.addEventListener('DOMContentLoaded', () => {
  const token = localStorage.getItem('token');
  const loginBtn = document.getElementById('loginBtn');
  const registerBtn = document.getElementById('registerBtn');
  const profileBtn = document.getElementById('profileBtn');

  if (token) {
    if (loginBtn) loginBtn.style.display = 'none';
    if (registerBtn) registerBtn.style.display = 'none';
    if (profileBtn) profileBtn.style.display = '';
    if (logoutBtn) logoutBtn.style.display = '';
  } else {
    if (loginBtn) loginBtn.style.display = '';
    if (registerBtn) registerBtn.style.display = '';
    if (profileBtn) profileBtn.style.display = 'none';

  }
});
function logout() {
  localStorage.removeItem('token');
  localStorage.removeItem('role');
  localStorage.removeItem('userid');
  window.location.href = "login.html";
}



document.addEventListener('DOMContentLoaded', async () => {
  const grid = document.getElementById('productsGrid');
  const loadingDiv = document.getElementById('productsLoading');

  // The API endpoint for products in your ShopVerse backend
  const API_URL = "http://localhost:8080/api/products/all";

  try {
    const res = await fetch(API_URL, { headers: { Accept: 'application/json' } });
    if (!res.ok) throw new Error('Could not load products');

    const products = await res.json();
    if (!products || !Array.isArray(products) || products.length === 0) {
      loadingDiv.outerHTML = `<div class="error">No products available.</div>`;
      return;
    }

    grid.innerHTML = '';

    products.forEach(product => {
      const {
        id,
        imageUrl,
        productname,
        description,
        price,
        stock
      } = product;

      let stockHtml = '';
      let buttonDisabled = false;
      if (typeof stock === 'number') {
        if (stock > 0) {
          stockHtml = `<span class="stock in-stock"><i class="fa fa-check-circle"></i> In Stock (${stock})</span>`;
        } else {
          stockHtml = `<span class="stock out-of-stock"><i class="fa fa-times-circle"></i> Out of Stock</span>`;
          buttonDisabled = true;
        }
      } else {
        stockHtml = `<span class="stock">Stock: N/A</span>`;
      }

      const card = document.createElement('div');
      card.className = 'product-card';

      card.innerHTML = `
        <img src="${imageUrl || 'https://via.placeholder.com/250?text=No+Image'}" alt="${productname || 'Product'}"/>
        <h3>${productname || 'Product Name'}</h3>
        <div class="desc">${description ? description.substring(0, 70) : ''}</div>
        <p class="price">₹${price != null ? price : '--'}</p>
        ${stockHtml}
        <button ${buttonDisabled ? 'disabled' : ''} onclick="addToCart(${product.id})">Add to Cart</button>
      `;
      grid.appendChild(card);
    });
  } catch (err) {
    loadingDiv.outerHTML = `<div class="error">Failed to load products. Please try again later.</div>`;
  }
});
window.addToCart =async function(productId) {
  const token = localStorage.getItem('token');
  const role = localStorage.getItem('role');
  const userId = localStorage.getItem('userid');
  if (!token || !role) {
    alert('Please login to add items to your cart.');
    window.location.href = 'login.html';
    return;
  }
  const cartItem = {
    userid: Number(userId),
    productid: Number(productId),
    quantity: 1
  };

  try {
    const response = await fetch('http://localhost:8080/api/cart-items/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        // If your backend requires JWT for this endpoint, add:
        'Authorization': 'Bearer ' + token
      },
      body: JSON.stringify(cartItem)
    });

    if (!response.ok) {
      const error = await response.text();
      alert('Failed to add to cart: ' + error);
      return;
    }

    alert('Product added to cart!');
  } catch (err) {
    alert('Error adding to cart: ' + err.message);
  }
};
