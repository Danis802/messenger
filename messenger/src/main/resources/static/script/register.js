document.addEventListener("DOMContentLoaded", ()=>{
    init();
})

function init(){
    const submitButton = document.getElementById("submit-btn");
    submitButton.addEventListener("click", regDataSend);
}

async function regDataSend(event){
    event.preventDefault();
    const login = document.getElementById("login").value;
    const password = document.getElementById("password").value;
    const name = document.getElementById("name").value;

//    const response = await fetch("http://192.168.0.173:8080/register",{
    const response = await fetch("/register",{
                method:"PUT",
                credentials: 'include',
                headers:{
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name: name,
                    login: login,
                    password: password
                })
                });
    const data = await response.json();

    if (!data.success) {
        alert(data.message);
    } else {
        localStorage.setItem("session",data.data.session);
        window.location.href = "/";
    }
}