document.addEventListener("DOMContentLoaded", ()=>{
    loadChats();
    const button = document.getElementById("quit");
    button.addEventListener("click", quitAccount);
})

function quitAccount(){
    localStorage.removeItem("session");
}

async function loadChats() {
    let session;
    try{
        session = localStorage.getItem("session");
    }catch(ReferenceError){
        alert("Сперва войдите в аккаунт!");
        return;
    }
        const messageDiv = document.getElementById('chats-grid');

        if (messageDiv!=null){
            messageDiv.innerHTML = '';
        }

    const response = await fetch(`/chat?session=${session}`,{
                    method:"GET",
                    credentials: 'include',
                    headers:{
                        "Content-Type": "application/json"
                    }
                    });
    const chats = await response.json();
    console.log(chats);

    const chatList = document.getElementById('chats-grid');

    if (chatList!=null){
        chatList.innerHTML = '';
    }

    chats.data.forEach(chat => {
        const chatDiv = document.createElement('div');

        chatDiv.addEventListener("click", () => {
                window.location.href = `/chat/${chat.id}`;
        });

        chatDiv.className = 'chat-item';

        chatDiv.innerHTML = `
            <h3>${chat.name}</h3>
            <p>${chat.lastMessage}</p>
        `;

        chatList.appendChild(chatDiv);
    });
}
