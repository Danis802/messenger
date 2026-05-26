document.addEventListener("DOMContentLoaded", ()=>{
    loadUserPage();
})

async function getLogin(){
    let session;

        try{
            session = localStorage.getItem("session");
        }catch(ReferenceError){
            alert("Сперва войдите в аккаунт!");
            return;
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

async function loadUserPage(){
    let session;
        try{
            session = localStorage.getItem("session");
        }catch(ReferenceError){
            alert("Сперва войдите в аккаунт!");
            return;
        }
            const friendsDiv = document.getElementById('friends-section');

            if (friendsDiv!=null){
                friendsDiv.innerHTML = '';
            }

        const userInfo = await getLogin();

        document.getElementById("user-name").textContent = userInfo.name;
        document.getElementById("user-login").textContent = userInfo.login;
        const response = await fetch(`/user/friends?session=${session}`,{
                        method:"GET",
                        credentials: 'include',
                        headers:{
                            "Content-Type": "application/json"
                        }
                        });
        console.log(response);
        const friends = await response.json();
        console.log(friends);

        friends.data.forEach(friend => {
        console.log(2);
            const friendDiv = document.createElement('div');

            friendDiv.addEventListener("click", () => {
                    window.location.href = `/userpage/${friend.login}?session=${session}`;
            });

            friendDiv.className = 'friend-item';

            friendDiv.innerHTML = `
                <h3>${friend.name}</h3>
                <p>${friend.login}</p>
            `;

            friendsDiv.appendChild(friendDiv);
        });
}