
async function fetchData() {
	const response  = await fetch("http://localhost:8080/all")
  	.then((response) => response.json())
  	.catch((error) => console.error(error));
  	
  	const data = await response;
  	return data;
}
async function renderSpots() {
        const spots = await fetchData();
        let body = document.querySelector("body");
        
        for (let i = 0; i < spots.length; i++) {
			let form = document.createElement("form");
			let span = document.createElement("span");
			let deleteButton = document.createElement("input");
			let editButton = document.createElement("input");
			let id = document.createElement("input");
			
			span.innerHTML = spots[i].name + ", " + spots[i].id;
			span.style.width = "200px";
			span.style.float = "left";
			span.style.overflow = "hidden";
			span.style.whiteSpace = "nowrap";
			span.style.textOverflow = "ellipsis";
			
			id.type = "number";
			id.value = spots[i].id;
			id.name = "id";
			id.style.display = "none";
			
			deleteButton.type = "submit";
			deleteButton.value = "delete";
			deleteButton.formAction = "/deletespot";
			deleteButton.style.marginLeft = "5px";
			deleteButton.addEventListener("click", function(e) {
				if(!window.confirm('Vai izdzēst spotu?')) {
        			e.preventDefault();
    			};
			})
			
			editButton.type = "submit";
			editButton.value = "edit";
			editButton.formAction = "/editspot";
			editButton.style.marginLeft = "5px";
			
			form.style.justifyContent = "center";
			
			form.appendChild(span);
			form.appendChild(id);
			form.appendChild(deleteButton);
			form.appendChild(editButton);
			body.appendChild(form);
		}
}
renderSpots();