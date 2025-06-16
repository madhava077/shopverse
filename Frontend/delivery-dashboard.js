document.addEventListener('DOMContentLoaded', async () => {
  const token = localStorage.getItem('token');
  const role = localStorage.getItem('role');
  if (!token || role !== 'DELIVERY') {
    alert('You must be logged in as a delivery user to access this page.');
    window.location.href = 'login.html';
    return;
  }

  const ordersBody = document.getElementById('ordersBody');

  try {
    // Fetch all orders (or only assigned to this delivery user if you have such logic)
    const res = await fetch('http://localhost:8080/api/orders/all', {
        method: 'GET',
      headers: { 'Authorization': 'Bearer ' + token }
    });
    if (!res.ok) throw new Error('Could not load orders');
    const orders = await res.json();

    if (!orders || orders.length === 0) {
      ordersBody.innerHTML = `<tr><td colspan="6" style="text-align:center;">No orders found.</td></tr>`;
      return;
    }

    // Optionally, filter orders by status if you want (e.g., show only "Processing" or "Shipped")
    ordersBody.innerHTML = orders.map(order => `
      <tr>
        <td>${order.id}</td>
        <td>${order.user_id}</td>
        <td>${order.orderDate}</td>
        <td>₹${order.totalAmount}</td>
        <td>${order.status}</td>
        <td>
          <select id="status-${order.id}">
            <option value="Processing" ${order.status === 'Processing' ? 'selected' : ''}>Processing</option>
            <option value="Shipped" ${order.status === 'Shipped' ? 'selected' : ''}>Shipped</option>
            <option value="Delivered" ${order.status === 'Delivered' ? 'selected' : ''}>Delivered</option>
          </select>
          <button onclick="updateOrderStatus(${order.id})">Update</button>
        </td>
      </tr>
    `).join('');
  } catch (err) {
    ordersBody.innerHTML = `<tr><td colspan="6" style="text-align:center;">Failed to load orders.</td></tr>`;
  }
});

window.updateOrderStatus = async function(orderId) {
  const token = localStorage.getItem('token');
  const status = document.getElementById(`status-${orderId}`).value;
  try {
    // Fetch the current order details
    const orderRes = await fetch(`http://localhost:8080/api/orders/${orderId}`, {
      headers: { 'Authorization': 'Bearer ' + token }
    });
    if (!orderRes.ok) {
      alert('Order not found!');
      return;
    }
    const order = await orderRes.json();
    order.status = status;

    // Update the order status
    const res = await fetch(`http://localhost:8080/api/orders/${orderId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      },
      body: JSON.stringify(order)
    });
    if (res.ok) {
      alert('Order status updated!');
      window.location.reload();
    } else {
      alert('Failed to update order status.');
    }
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