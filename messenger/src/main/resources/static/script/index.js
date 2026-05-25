document.addEventListener("DOMContentLoaded", ()=>{
    loadChats();
})

async function loadChats() {
    const session = localStorage.getItem("session");

//    const response = await fetch(`http://192.168.0.173:8080/chat?session=${session}`,{
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
