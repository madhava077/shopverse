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
        <button ${buttonDisabled ? 'disabled' : ''}>Add to Cart</button>
      `;
      grid.appendChild(card);
    });
  } catch (err) {
    loadingDiv.outerHTML = `<div class="error">Failed to load products. Please try again later.</div>`;
  }
});
