# Online-Store-Application

**API Endpoints**
1. Customer registration -> http://localhost:8080/api/customers/register
   example data :
       {
          "customerName": "Kathir",
          "customerEmail": "kathir@gmail.com",
          "password": "kathir123",
          "phoneNumber": "9876543210",
          "customerAddress": "Chennai, Tamil Nadu"
      }

2. Create new Product -> http://localhost:8080/api/products/create-product
     {
        "productName": "Wireless Mouse",
        "description": "Bluetooth ergonomic mouse",
        "category": "ELECTRONICS",
        "price": 899.99,
        "stackQuantity": 25
    }
       
3. Place Order -> http://localhost:8080/api/orders/placeOrder
   example data :
       {
          "customerId": 1,
          "items": [
             { "productId": 2, "quantity": 1 },
             { "productId": 5, "quantity": 3 }
                   ]
      }

   
