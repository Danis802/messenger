document.addEventListener("DOMContentLoaded", ()=>{
    init();
})
const chatId = window.location.pathname.split("/").pop();
function init(){
    const submitButton = document.getElementById("submit-btn");
    submitButton.addEventListener("click", sendMes);
}

async function sendMes(event){
        event.preventDefault();
        const content = document.getElementById("messageInput").value;

        const response = await fetch(`http://localhost:8080/chat/${chatId}`,{
                    method:"PUT",
                    credentials: 'include',
                    headers:{
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        content: content,
                        session: localStorage.getItem("session")
                    })
                    });

        const data = await response.json();
        console.log(data);

        if (!data.success) {
            alert(data.message);
        }
}

async function updateMes(){
//    event.preventDefault();
    const session = localStorage.getItem("session");

    const response = await fetch(`http://localhost:8080/chat/messages?session=${session}&chatId=${chatId}`,{
                    method:"GET",
                    credentials: 'include',
                    headers:{
                        "Content-Type": "application/json"
                    }
                    });
    const messages = await response.json();
    console.log(messages);

    const messagesList = document.getElementById('chat-messages');

    if (messagesList!=null){
        messagesList.innerHTML = '';
    }

        messages.data.forEach(mes => {
            const mesDiv = document.createElement('div');

            mesDiv.className = 'mes-item';

            mesDiv.innerHTML = `
                <h3>${mes.sender}</h3>
                <p>${mes.content}</p>
            `;

            messagesList.appendChild(mesDiv);
        });
}

setInterval(updateMes, 1000);