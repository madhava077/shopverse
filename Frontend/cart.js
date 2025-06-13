document.addEventListener('DOMContentLoaded', () => {
  const token = localStorage.getItem('token');
  const profileBtn = document.getElementById('profileBtn');
  const logoutBtn = document.getElementById('logoutBtn');
  if (token) {
    if (profileBtn) profileBtn.style.display = '';
    if (logoutBtn) logoutBtn.style.display = '';
  }
});

function logout() {
  localStorage.removeItem('token');
  localStorage.removeItem('role');
  localStorage.removeItem('userid');
  window.location.href = "login.html";
}

document.addEventListener('DOMContentLoaded', async () => {
  const token = localStorage.getItem('token');
  const userId = localStorage.getItem('userid');
  const cartItemsDiv = document.getElementById('cartItems');
  const cartTotalDiv = document.getElementById('cartTotal');

  if (!token || !userId) {
    alert('Please login to view your cart.');
    window.location.href = 'login.html';
    return;
  }

  try {
    // Fetch cart items for the user
    const res = await fetch(`http://localhost:8080/api/cart-items/get/${userId}`, {
      headers: {
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + token
      }
    });
    if (!res.ok) throw new Error('Could not load cart items');
    const cartItems = await res.json();

   if (!cartItems || cartItems.length === 0) {
  cartItemsDiv.innerHTML = `<div class="empty">Your cart is empty.</div>`;
  cartTotalDiv.textContent = '';
  document.getElementById('confirmOrderBtn').style.display = 'none';
  return;
} else {
  document.getElementById('confirmOrderBtn').style.display = '';
}

    // Fetch all products to get product details (name, image, price)
    const productsRes = await fetch('http://localhost:8080/api/products/all');
    const products = await productsRes.json();

    let total = 0;
    cartItemsDiv.innerHTML = cartItems.map(item => {
      const product = products.find(p => p.id === item.productid);
      const price = product ? product.price : 0;
      const imageUrl = product ? product.imageUrl : '';
      const name = product ? product.productname : 'Product';
      const subtotal = price * item.quantity;
      total += subtotal;
      return `
        <div class="cart-item">
          <img src="${imageUrl || 'https://via.placeholder.com/80?text=No+Image'}" alt="${name}">
          <div class="cart-item-details">
            <div><strong>${name}</strong></div>
            <div>Quantity: ${item.quantity}</div>
            <div>Price: ₹${price}</div>
            <div>Subtotal: ₹${subtotal}</div>
          </div>
          <div class="cart-item-actions">
            <button onclick="removeFromCart(${item.id})">Remove</button>
          </div>
        </div>
      `;
    }).join('');
    cartTotalDiv.textContent = `Total: ₹${total}`;
  } catch (err) {
    cartItemsDiv.innerHTML = `<div class="empty">Failed to load cart items.</div>`;
    cartTotalDiv.textContent = '';
  }
});

// Remove item from cart
window.removeFromCart = async function(cartItemId) {
  const token = localStorage.getItem('token');
  if (!token) {
    alert('Please login.');
    window.location.href = 'login.html';
    return;
  }
  if (!confirm('Remove this item from your cart?')) return;
  try {
    const res = await fetch(`http://localhost:8080/api/cart-items/delete/${cartItemId}`, {
      method: 'DELETE',
      headers: {
        'Authorization': 'Bearer ' + token
      }
    });
    if (!res.ok) throw new Error('Failed to remove item');
    alert('Item removed!');
    window.location.reload();
  } catch (err) {
    alert('Error: ' + err.message);
  }
};
document.getElementById('confirmOrderBtn').addEventListener('click', async function() {
  const token = localStorage.getItem('token');
  const userId = localStorage.getItem('userid');
  if (!token || !userId) {
    alert('Please login to confirm your order.');
    window.location.href = 'login.html';
    return;
  }
  if (!confirm('Are you sure you want to place this order?')) return;
  try {
    // Place order (your backend expects userId as a query param or body)
    const res = await fetch(`http://localhost:8080/api/orders/${userId}`, {
  method: 'POST',
  headers: {
    'Authorization': 'Bearer ' + token
  }
});
    if (!res.ok) {
      const errMsg = await res.text();
      throw new Error(errMsg || 'Order failed');
    }
    alert('Order placed successfully!');
    window.location.reload();
  } catch (err) {
    alert('Order failed: ' + err.message);
  }
});