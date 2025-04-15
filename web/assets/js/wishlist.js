
function submitForm(event, method) {
    event.preventDefault(); // Ngăn chặn hành vi mặc định của form
    
    var form = event.target.closest('form');
    
    var action = form.querySelector('input[id^="action"]');
    if (method === 'method1') {
        action.name = 'isAdd';
        form.action = './cart';
        form.method = 'post';
    
    } else if (method === 'method2') {
        action.name = 'removeWishList';
        form.action = './favourite';
        form.method = 'post';
    }
    form.submit();
}