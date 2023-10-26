
async function fetchData() {
	const response  = await fetch("http://localhost:8080/get/categories")
  	.then((response) => response.json())
  	.catch((error) => console.error(error));
  	
  	const data = await response;
  	return data;
}
async function renderCategories() {
        const categories = await fetchData();
        let dropdown = document.getElementById("categories");
        
        for (let i = 0; i < categories.length; i++) {
			let option = document.createElement("option");
			option.innerHTML = categories[i].name;
			option.value = categories[i].name;
			dropdown.appendChild(option);  
		}
}
renderCategories();