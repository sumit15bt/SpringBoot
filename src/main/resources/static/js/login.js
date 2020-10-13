const labels=document.querySelectorAll("label");
console.log(labels);
labels.forEach((label)=>{
	console.log(label);
	label.innerHTML =label.innerText
	.split("")
	.map((letter,index)=>{
		console.log(letter+" -> "+index);
		return '<span>'+letter+'</span>'
	}).join("");
})