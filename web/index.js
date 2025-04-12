// 
//console.clear();

const loginBtn = document.getElementById('login');
const signupBtn = document.getElementById('signup');

loginBtn.addEventListener('click', (e) => {
	let parent = e.target.parentNode.parentNode;
	Array.from(e.target.parentNode.parentNode.classList).find((element) => {
		if(element !== "slide-up") {
			parent.classList.add('slide-up')
		}else{
			signupBtn.parentNode.classList.add('slide-up')
			parent.classList.remove('slide-up')
		}
	});
});

signupBtn.addEventListener('click', (e) => {
	let parent = e.target.parentNode;
	Array.from(e.target.parentNode.classList).find((element) => {
		if(element !== "slide-up") {
			parent.classList.add('slide-up')
		}else{
			loginBtn.parentNode.parentNode.classList.add('slide-up')
			parent.classList.remove('slide-up')
		}
	});
});





const userIcon = document.getElementById('user-icon');
const overlay = document.getElementById('update-avatar-overlay');

// Add a click event listener to the icon
userIcon.addEventListener('click', () => {
    console.log("click");
    // Toggle the overlay's visibility
    if (overlay.style.display === 'none' || overlay.style.display === '') {
        overlay.style.display = 'block'; // Show overlay
    } else {
        overlay.style.display = 'none'; // Hide overlay
    }
});




function showUpdateAvatarOverlay() {
    document.getElementById("update-avatar-overlay").style.display = "block";
}

function hideUpdateAvatarOverlay() {
    document.getElementById("update-avatar-overlay").style.display = "none";
}


function showAddProductOverlay() {
    document.getElementById("add-product-overlay").style.display = "block";
}

function hideAddProductOverlay() {
    document.getElementById("add-product-overlay").style.display = "none";
}




function increment(button) {
    let input = button.previousElementSibling;
    if (parseInt(input.value) < 10) {
        input.value = parseInt(input.value) + 1;
        updateAmount(input);
    }
}

function decrement(button) {
    let input = button.nextElementSibling;
    if (parseInt(input.value) > 1) {
        input.value = parseInt(input.value) - 1;
        updateAmount(input);
    }
}



function submitForm(method) {
    var form = document.getElementById('myForm');
    var action = document.getElementById('action');
    if (method === 'method1') {
        form.action = './cart';
        form.method = 'post';
    } else if (method === 'method2') {
        action.name = 'addWishList';
        form.action = './favourite';
        form.method = 'post';
    } else if (method === 'method3') {
        action.name = 'removeWishList';
        form.action = './favourite';
        form.method = 'post';
    }
    form.submit();
}



