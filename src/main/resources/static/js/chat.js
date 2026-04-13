let stompClient = null;
let currentSubscription = null;

function connect() {
    loadCurrentUser();

    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        console.log("Connected");
        subscribeToRoom();
    });
}

function subscribeToRoom() {
    const room = document.getElementById('room').value;

    if (currentSubscription) {
        currentSubscription.unsubscribe();
    }

    document.getElementById("messages").innerHTML = "";

    currentSubscription = stompClient.subscribe('/topic/rooms/' + room, function (message) {
        const msg = JSON.parse(message.body);
        showMessage(msg);
    });

    loadMessagesForRoom(room);
}

function loadMessagesForRoom(room) {
    fetch('/messages/room/' + room)
        .then(res => res.json())
        .then(data => {
            data.forEach(msg => showMessage(msg));
        });
}

function sendMessage() {
    const content = document.getElementById('message').value;
    const room = document.getElementById('room').value;
    subscribeToRoom();

    stompClient.send("/app/chat.send", {}, JSON.stringify({
        content: content,
        room: room
    }));

    document.getElementById('message').value = '';
}

function showMessage(msg) {
    const li = document.createElement("li");

    const time = msg.createdAt ? new Date(msg.createdAt).toLocaleString() : "";
    li.textContent = `[${time}] ${msg.sender}: ${msg.content}`;

    document.getElementById("messages").appendChild(li);
}

function loadCurrentUser() {
    fetch('/auth/me')
        .then(res => {
            if (!res.ok) {
                throw new Error('Failed to load current user');
            }
            return res.json();
        })
        .then(data => {
            document.getElementById('current-user').textContent = data.username;
        })
        .catch(err => {
            console.error(err);
            document.getElementById('current-user').textContent = 'unknown';
        });
}

connect();