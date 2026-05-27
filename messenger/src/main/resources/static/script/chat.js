document.addEventListener("DOMContentLoaded", ()=>{
    init();
})

const chatId = window.location.pathname.split("/").pop();

function init(){
    const submitButton = document.getElementById("submit-btn");
    submitButton.addEventListener("click", sendMes);
    getKey();
}
let chatKey;

function mixKey(key){
    let digitMatch;
    if (key.match(/\d/)) {
        digitMatch = key.match(/\d/);
    }else{
        digitMatch = '3';
    }

    const blockSize = parseInt(digitMatch[0], 10);

    const blocks = [];

    for (let i = 0; i < key.length; i += blockSize) {
        blocks.push(key.slice(i, i + blockSize));
    }

    const generatedKey = blocks.reverse().join('');

    return generatedKey;
}

function encodeMessage(text, key){
    let result = '';

    for (let i = 0; i < text.length; i++) {
        const textCharCode = text.charCodeAt(i);
        const keyCharCode = key.charCodeAt(i % key.length);

        const encryptedCharCode = textCharCode ^ keyCharCode;

        result += String.fromCharCode(encryptedCharCode);
    }

    return result;
}

async function getKey(){
    let session;
    try{
        session = localStorage.getItem("session");
    }catch(ReferenceError){
        window.location.href = "/login";
    }
    const keyResponse = await fetch(`/chat/${chatId}/key?session=${session}`,{
        method:"GET",
        credentials: 'include',
        headers:{
        "Content-Type": "application/json"
        }
    });
    const keyData = await keyResponse.json();
    chatKey = keyData.data;
}

async function sendMes(event){
        event.preventDefault();
        let content = document.getElementById("messageInput").value;
        let session;
        document.getElementById("messageInput").value = '';

        try{
            session = localStorage.getItem("session");
        }catch(ReferenceError){
            window.location.href = "/login";
        }

        realKey = mixKey(chatKey);
        content = encodeMessage(content, realKey);

        const response = await fetch(`/chat/${chatId}`,{
                    method:"PUT",
                    credentials: 'include',
                    headers:{
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        content: content,
                        session: session
                    })
                    });

        const data = await response.json();

        if (!data.success) {
            alert(data.message);
        }
}

async function updateMes(){
    const session = localStorage.getItem("session");

    const response = await fetch(`/chat/messages?session=${session}&chatId=${chatId}`,{
                    method:"GET",
                    credentials: 'include',
                    headers:{
                        "Content-Type": "application/json"
                    }
                    });

    const messages = await response.json();
    realKey = mixKey(chatKey);

    const messagesList = document.getElementById('chat-messages');

    if (messagesList!=null){
        messagesList.innerHTML = '';
    }

    messages.data.forEach(mes => {
        const mesDiv = document.createElement('div');

        mesDiv.className = 'mes-item';

        mesDiv.innerHTML = `
            <h3>${mes.sender}</h3>
            <p>${encodeMessage(mes.content,realKey)}</p>
        `;

            messagesList.appendChild(mesDiv);
        });
}

setInterval(updateMes, 1000);