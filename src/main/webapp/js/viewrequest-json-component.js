/**
 * This method is used to all leave requests
 */
function getAllRequests() {
	console.log("Fetching");
	let url = "RequestStatusServlet2";
	fetch(url).then(res => res.json()).then(res => {
		let requests = res;
		console.log(requests);
		let content = "";
		let serial = 1;
		for (let request of requests) {
			console.log(request.fromDate);
			content +=
				"<tr><td>" + serial + "</td><td>"
				+ request.employeeName + "</td><td>"
				+ request.employeeId + "</td><td>"
				+ request.fromDate + "</td><td>"
				+ request.toDate + "</td><td>"
				+ request.duration + "</td><td>"
				+ request.status + "</td><td>"
				+ request.appliedTime + "</td></tr>";
			console.log(content);
			serial++;
		}

		document.querySelector("#table-body").innerHTML = content;
	})
}
getAllRequests();