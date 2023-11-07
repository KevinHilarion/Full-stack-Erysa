//Spanish
let previousTitle = document.title

window.addEventListener('blur',() =>{
  previousTitle = document.title
  document.title = 'Te esperamos 🤩'
})

window.addEventListener('focus',() =>{
	document.title = previousTitle
})

