# Online-Store-Application

## API Endpoints

### 1. Customer registration  
POST -> http://localhost:8080/api/customers/register  

```example json :
{
  "customerName": "Kathir",
  "customerEmail": "kathir@gmail.com",
  "password": "kathir123",
  "phoneNumber": "9876543210",
  "customerAddress": "Chennai, Tamil Nadu"
}
```

### 2. Create new Product  
POST -> http://localhost:8080/api/products/create-product  

```example json :
{
  "productName": "Wireless Mouse",
  "description": "Bluetooth ergonomic mouse",
  "category": "ELECTRONICS",
  "price": 899.99,
  "stackQuantity": 25
}
```

### 3. Place Order  
POST -> http://localhost:8080/api/orders/placeOrder  

```example json :
{
  "customerId": 1,
  "items": [
    { "productId": 2, "quantity": 1 },
    { "productId": 5, "quantity": 3 }
  ]
}
```

### 4. Cancel the Order Based on **"Order_id"**
PUT -> http://localhost:8080/api/orders/{order_id}/cancel

Change the Order status to **"CANCELLED"**, then re-stock the quantity to the Product Entity.

### 5. Get Order Details
GET -> http://localhost:8080/api/orders/{order_id}

Retrieves complete order information based on the given **"Order ID"** in the request path.

