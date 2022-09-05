## froggy-gaming-server

@author: Minh đẹp trai <br/>
@author: Việt đẹp trai

##Json web token
Lần đầu run code thì mở appication ra ![image](https://user-images.githubusercontent.com/88840717/188499912-64f5fac6-a230-4063-9eef-057722ef31b7.png)
tăt comment trong đó rồi mới run chạy xong lần đầu thì comment lại 
t k xoá nó đi vì ở đó lưu acc USER & ADMIN
từ mai login phải có username password r nha các bro

## Product

### Lấy tất cả các sản phẩm

GET: http://localhost:8386/api/v1/product

### Xóa sản phẩm bằng id

DELETE: http://localhost:8386/api/v1/product/delete/{proId}

{ <br>
"proId": "{{proId}}" <br>
} <br>

### Sửa thông tin sản phẩm bằng id

PUT: http://localhost:8386/api/v1/product/update/{proId}

{ <br>
"proId": "{{proId}}" <br>
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

### Trả về danh sách sản phẩm được sắp xếp theo tên A-Z (thứ tự tăng dần)

GET: http://localhost:8386/api/v1/product/sort=pro.name&order=asc

### Trả về danh sách sản phẩm được sắp xếp theo tên A-Z (thứ tự giảm dần)

GET: http://localhost:8386/api/v1/product/sort=pro.name&order=desc

### Trả về danh sách sản phẩm được sắp xếp theo giá tiền (thứ tự tăng dần)

GET: http://localhost:8386/api/v1/product/sort=pro.price&order=asc

### Trả về danh sách sản phẩm được sắp xếp theo giá tiền (thứ tự giảm dần)

GET: http://localhost:8386/api/v1/product/sort=pro.price&order=desc

### Trả về danh sách sản phẩm được sắp xếp theo thời gian (thứ tự tăng dần)

GET: http://localhost:8386/api/v1/product/sort=pro.date&order=asc

### Trả về danh sách sản phẩm được sắp xếp theo thời gian (thứ tự giảm dần)

GET: http://localhost:8386/api/v1/product/sort=pro.date&order=desc

### Trả về danh sách sản phẩm được lọc trong một vùng giá

GET: http://localhost:8386/api/v1/product/min-price={proPriceMin}&max-price={proPriceMax}

### Trả về danh sách sản phẩm được tìm bằng tên và sắp xếp theo giá (thứ tự tăng dần)

GET: http://localhost:8386/api/v1/product/search/query={proName}&sort=pro.price&order=asc

### Trả về danh sách sản phẩm được tìm bằng tên và sắp xếp theo giá (thứ tự giảm dần)

GET: http://localhost:8386/api/v1/product/search/query={proName}&sort=pro.price&order=desc

### Trả về danh sách sản phẩm được tìm bằng tên và sắp xếp theo thời gian (thứ tự tăng dần)

GET: http://localhost:8386/api/v1/product/search/query={proName}&sort=pro.date&order=asc

### Trả về danh sách sản phẩm được tìm bằng tên và sắp xếp theo thời gian (thứ tự giảm dần)

GET: http://localhost:8386/api/v1/product/search/query={proName}&sort=pro.date&order=desc

### Trả về danh sách sản phẩm được tìm bằng tên và sắp xếp theo tên A-Z (thứ tự tăng dần)

GET: http://localhost:8386/api/v1/product/search/query={proName}&sort=pro.name&order=asc

### Trả về danh sách sản phẩm được tìm bằng tên và sắp xếp theo tên A-Z (thứ tự giảm dần)

GET: http://localhost:8386/api/v1/product/search/query={proName}&sort=pro.name&order=desc
