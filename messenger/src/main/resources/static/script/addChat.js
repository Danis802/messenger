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
    const name = document.getElementById("name").value;
    let session;
    try{
        session = localStorage.getItem("session");
    }catch(ReferenceError){
        window.location.href = "/login";
    }

//    const response = await fetch("http://192.168.0.173:8080/addChat",{
        const response = await fetch("/addChat",{
                method:"PUT",
                credentials: 'include',
                headers:{
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name: name,
                    memberLogin: login,
                    session: session
                })
                });

    const data = await response.json();

    if (!data.success) {
        alert(data.message);
    } else {
        window.location.href = "/";
    }
}