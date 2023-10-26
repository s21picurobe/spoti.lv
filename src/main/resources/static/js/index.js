function clickSpot() {
	const spots = document.getElementsByClassName("spots-item");
	console.log(spots);
	
	for (let i = 0; i < spots.length; i++) {
		let e = spots[i].addEventListener("click", function(e) {
			spots[i].submit();
		})	
	}
}

clickSpot();