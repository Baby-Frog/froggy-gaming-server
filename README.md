## froggy-gaming-server

@author: Minh đẹp trai <br/>
@author: Việt đẹp trai

## Product

### Lấy tất cả các sản phẩm

GET: http://localhost:8386/api/v1/product

### Xóa sản phẩm bằng id

DELETE: http://localhost:8386/api/v1/product/delete/{proId}

{ <br>
"proId": "{(proId}}" <br>
} <br>

### Sửa thông tin sản phẩm bằng id

PUT: http://localhost:8386/api/v1/product/update/{proId}

{ <br>
"proId": "{(proId}}" <br>
} <br>

### Add sản phẩm vào category

POST: http://localhost:8386/api/v1/product/add-to-category

{ <br>
"proName": "{{proName}}", <br>
"cateName: "{{cateName}}" <br>
} <br>

### Add sản phẩm vào một brand

POST: http://localhost:8386/api/v1/product/add-to-brand

{ <br>
"proName": "{{proName}}", <br>
"brandName: "{{brandName}}" <br>
} <br>

### Add sản phẩm vào order-detail

POST: http://localhost:8386/api/v1/product/add-to-order-detail

{ <br>
"proName": "{{proName}}", <br>
"orderDetailId: "{{orderDetailId}}" <br>
} <br>

### Xóa một chi tiết sản phẩm bằng id

POST: http://localhost:8386/api/v1/product/delete-product-detail/{productDetailId}

{ <br>
"productDetailId": {{productDetailId}} <br>
} <br>

### Tìm danh sách sản phẩm bằng tên

GET: http://localhost:8386/api/v1/product/search/query={proName}

### Trả về danh sách sản phẩm được sắp xếp theo tên (thứ tự tăng dần)

GET: http://localhost:8386/api/v1/product/sort-by-name/asc

### Trả về danh sách sản phẩm được sắp xếp theo tên (thứ tự giảm dần)

GET: http://localhost:8386/api/v1/product/sort-by-name/desc

### Trả về danh sách sản phẩm được sắp xếp theo giá tiền (thứ tự tăng dần)

GET: http://localhost:8386/api/v1/product/sort-by-price/asc

### Trả về danh sách sản phẩm được sắp xếp theo giá tiền (thứ tự giảm dần)

GET: http://localhost:8386/api/v1/product/sort-by-price/desc

### Trả về danh sách sản phẩm được sắp xếp theo thời gian (thứ tự tăng dần)

GET: http://localhost:8386/api/v1/product/sort-by-date/asc

### Trả về danh sách sản phẩm được sắp xếp theo thời gian (thứ tự giảm dần)

GET: http://localhost:8386/api/v1/product/sort-by-date/desc

### Trả về danh sách sản phẩm được lọc trong một vùng giá

GET: http://localhost:8386/api/v1/product/min-price={proPriceMin}&max-price={proPriceMax}
