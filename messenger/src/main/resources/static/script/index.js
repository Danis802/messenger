document.addEventListener("DOMContentLoaded", ()=>{
    loadChats();
    init();
    });

async function getLogin(){
    let session;

        try{
            session = localStorage.getItem("session");
        }catch(ReferenceError){
            window.location.href = "/login";
        }
    const response = await fetch(`/user?session=${session}`,{
                            method:"GET",
                            credentials: 'include',
                            headers:{
                                "Content-Type": "application/json"
                            }
                            });
    const login = await response.json();
    return login.data;
}

async function init(){
    const userInfo = await getLogin();
    document.getElementById("userpage").href = `/userpage/${userInfo.login}`
    document.getElementById("userName").textContent = userInfo.name;
}

async function loadChats() {
    let session;
    try{
        session = localStorage.getItem("session");
    }catch(ReferenceError){
        window.location.href = "/login";
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
        `;

        chatList.appendChild(chatDiv);
    });
}
