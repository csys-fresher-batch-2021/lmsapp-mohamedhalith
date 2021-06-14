/**
 * This method is used to set minimum (boundary conditions) for the input fields
 */
function setMinValue() {
	let date = new Date().toJSON().substr(0, 10);
	let fromDate = document.getElementById("fromDate");
	let toDate = document.getElementById("toDate");
	fromDate.setAttribute("min", date);
	fromDate.setAttribute("value", date);
	toDate.setAttribute("min", date);
	toDate.setAttribute("value", date);
	let duration = document.getElementById("duration");
	duration.setAttribute("value", 1);
}
//Function call
setMinValue();

/**
* This function is called on giving input to fromDate field.
* It prevents from giving to date value before from date
*/
fromDate.addEventListener('input', function() {
	let day = fromDate.value;
	toDate.setAttribute("min", day);
	toDate.setAttribute("value", day);
	findDuration();
});
/**
* This functions find the no of days between the from and to dates.
*/
function findDuration() {
	let endDate = new Date(toDate.value);
	let startDate = new Date(fromDate.value);
	let difference = endDate.getTime() - startDate.getTime();
	difference = difference / (24 * 3600 * 1000);
	duration.setAttribute("value", difference + 1);
};