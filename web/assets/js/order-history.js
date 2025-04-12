/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function updateAmount(input) {
    let quantity = parseInt(input.value);
    let priceElement = input.closest('.cart-item').querySelector('.price');
    let price = parseInt(priceElement.textContent.split('.')[0]); // Remove dot separator from price
    let amountElement = input.closest('.cart-item').querySelector('.amount');
    let amount = price * quantity;
    let productId = input.id;
    amountElement.textContent = formatPrice(amount) + '₫';

    console.log("Calling updateAmount for input with value:", input.value);



    $.ajax({
        url: '/OrderingSystem/cart',
        type: 'POST',
        data: {
            isUpdate: true,
            productId: productId,
            quantity: quantity
        },
        success: function (response) {
            console.log("Cập nhật thành công cho sản phẩm ID:", productId);
            updateTotal();
        },
        error: function (error) {
            console.log("Lỗi khi cập nhật sản phẩm ID:", productId, error);
        }
    });
}









function updateTotal() {
    let total = 0;
    document.querySelectorAll('.totals_price').forEach(priceElement => {
        // Lấy giá trị text, bỏ ký tự '₫', loại bỏ dấu '.' và chuyển thành số nguyên
        let price = parseInt(priceElement.textContent.replace(/₫/g, '').replace(/\./g, ''));
        total = price;
    });

    document.querySelectorAll('.totals_price').textContent = formatPrice(total) + '₫';
}

function formatPrice(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.');
}

window.onload = function () {
    updateTotal();
};