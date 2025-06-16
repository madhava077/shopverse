document.addEventListener('DOMContentLoaded', async () => {
  const token = localStorage.getItem('token');
  const userId = localStorage.getItem('userid');
  const ordersList = document.getElementById('ordersList');

  if (!token || !userId) {
    alert('Please login to view your orders.');
    window.location.href = 'login.html';
    return;
  }

  // Fetch orders for this user (filtered by backend)
  let orders = [];
  try {
    const res = await fetch(`http://localhost:8080/api/orders/user/${userId}`, {
      headers: { 'Authorization': 'Bearer ' + token }
    });
    if (!res.ok) throw new Error('Could not load orders');
    orders = await res.json();
  } catch (err) {
    ordersList.innerHTML = `<div class="empty">Failed to load orders.</div>`;
    return;
  }

  if (!orders || orders.length === 0) {
    ordersList.innerHTML = `<div class="empty">You have no orders yet.</div>`;
    return;
  }

  // Helper to fetch product details by id (with cache)
  const productCache = {};
  async function getProduct(productId) {
    if (productCache[productId]) return productCache[productId];
    const res = await fetch(`http://localhost:8080/api/products/getproduct/${productId}`);
    if (!res.ok) return { productname: 'Product', price: '--' };
    const prod = await res.json();
    productCache[productId] = prod;
    return prod;
  }

  // Render each order and its items
  ordersList.innerHTML = '';
  for (const order of orders) {
    // Fetch order items
    let items = [];
    try {
      const res = await fetch(`http://localhost:8080/api/order-items/order/${order.id}`);
      if (res.ok) items = await res.json();
    } catch {}

    // Build order items table
    let itemsHtml = '';
    if (!items || items.length === 0) {
      itemsHtml = `<tr><td colspan="4" style="text-align:center;">No items</td></tr>`;
    } else {
      // Fetch all product details in parallel
      const products = await Promise.all(items.map(item => getProduct(item.productid)));
      itemsHtml = items.map((item, idx) => {
        const prod = products[idx];
        return `
          <tr>
            <td>${prod.productname || 'Product'}</td>
            <td>${item.quantity}</td>
            <td>₹${prod.price != null ? prod.price : '--'}</td>
            <td>₹${prod.price != null ? (prod.price * item.quantity) : '--'}</td>
          </tr>
        `;
      }).join('');
    }

    // Add cancel button if order is not canceled or delivered
    let cancelBtnHtml = '';
    if (order.status !== 'Canceled' && order.status !== 'Delivered') {
      cancelBtnHtml = `<button class="cancel-btn" onclick="cancelOrder(${order.id})">Cancel Order</button>`;
    }

    // Render order block
    const orderDiv = document.createElement('div');
    orderDiv.className = 'order';
    orderDiv.innerHTML = `
      <div class="order-header">
        Order #${order.id}
        <span class="order-status">${order.status || ''}</span>
      </div>
      <div>Date: ${order.orderDate || ''}</div>
      <div>Total: ₹${order.totalAmount != null ? order.totalAmount : '--'}</div>
      <table class="order-items-table">
        <thead>
          <tr>
            <th>Product</th>
            <th>Qty</th>
            <th>Price</th>
            <th>Subtotal</th>
          </tr>
        </thead>
        <tbody>
          ${itemsHtml}
        </tbody>
      </table>
      ${cancelBtnHtml}
    `;
    ordersList.appendChild(orderDiv);
  }
});

window.cancelOrder = async function(orderId) {
  const token = localStorage.getItem('token');
  if (!confirm('Are you sure you want to cancel this order?')) return;
  try {
    const res = await fetch(`http://localhost:8080/api/orders/cancel/${orderId}`, {
      method: 'PUT',
      headers: { 'Authorization': 'Bearer ' + token }
    });
    if (!res.ok) throw new Error('Failed to cancel order');
    alert('Order canceled.');
    window.location.reload();
  } catch (err) {
    alert('Error: ' + err.message);
  }
};

function logout() {
  localStorage.removeItem('token');
  localStorage.removeItem('role');
  localStorage.removeItem('userid');
  window.location.href = "login.html";
}