function deletionConfirmation() {
	const deleteButtons = document.getElementsByClassName("deletebutton");
	console.log(deleteButtons);

	for (let i = 0; i < deleteButtons.length; i++) {
		deleteButtons[i].addEventListener("click", function(e) {
			if (!window.confirm('Vai izdzēst spotu?')) {
				e.preventDefault();
			};
		})
	}
}

deletionConfirmation();