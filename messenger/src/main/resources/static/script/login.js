document.addEventListener("DOMContentLoaded", ()=>{
    init();
})

function init(){
    const submitButton = document.getElementById("submit-btn");
    submitButton.addEventListener("click", regDataSend);
}

function regDataSend(event){
    event.preventDefault();
    const login = document.getElementById("login").value;
    const password = document.getElementById("password").value;

//    fetch("http://192.168.0.173:8080/login",{
    fetch("/login",{
                method:"POST",
                credentials: 'include',
                headers:{
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    login: login,
                    password: password
                })
                }).then(response => response.json())
                .then(data => {
                window.location.href = "/";
                localStorage.setItem("session",data['session']);
                });
}