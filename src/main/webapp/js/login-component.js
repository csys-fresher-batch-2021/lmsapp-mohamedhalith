/**
 * This method is used to toggle type of the input 
 */
function toggle() {
	let eyefull = document.getElementById('eyefull');
	let password = document.getElementById("password");
	/**
	* Function to show password (toggle between the type of password)
	*/
	eyefull.addEventListener('click', function() {
		let type = password.getAttribute('type') === 'password' ? 'text' : 'password';
		password.setAttribute('type', type);
		let className = eyefull.getAttribute('class') === 'fas fa-eye' ? 'fas fa-eye-slash' : 'fas fa-eye';
		eyefull.setAttribute('class', className);
	});
}
toggle();