let stompClient = null;
let currentSubscription = null;
let displayedMessageIds = new Set();
let currentUser = null;

function connect() {
    loadCurrentUser();

    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        console.log("Connected");

        stompClient.subscribe('/topic/online-count', function (message) {
            console.log('online-count:', message.body);
            document.getElementById('online-count').textContent = message.body;
        });

        stompClient.send("/app/online-count", {}, "");

        subscribeToRoom();
    });
}

function subscribeToRoom() {
    const room = document.getElementById('room').value;

    if (currentSubscription) {
        currentSubscription.unsubscribe();
    }

    document.getElementById("messages").innerHTML = "";
    displayedMessageIds.clear();

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

    if (!content) {
        return;
    }

    stompClient.send("/app/chat.send", {}, JSON.stringify({
        content: content,
        room: room
    }));

    document.getElementById('message').value = '';
}

function showMessage(msg) {
    if (msg.id && displayedMessageIds.has(msg.id)) {
        return;
    }
    if (msg.id) {
        displayedMessageIds.add(msg.id);
    }
    const li = document.createElement("li");
    const time = msg.createdAt ? new Date(msg.createdAt).toLocaleString() : "";
    li.textContent = `[${time}] ${msg.sender}: ${msg.content}`;

    if (msg.sender === currentUser) {
        li.classList.add("my-message");
    } else {
        li.classList.add("other-message");
    }

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
            currentUser = data.username;
            document.getElementById('current-user').textContent = data.username;
        })
        .catch(err => {
            console.error(err);
            document.getElementById('current-user').textContent = 'unknown';
        });
}

async function loadRooms() {
    try {
        const response = await fetch('/chatrooms/all');
        const rooms = await response.json();

        const roomSelect = document.getElementById('room');
        roomSelect.innerHTML = '';

        rooms.forEach(room => {
            const option = document.createElement('option');
            option.value = room;
            option.textContent = formatRoomName(room);
            roomSelect.appendChild(option);
        });

        roomSelect.addEventListener('change', () => {
            subscribeToRoom();
        });

    } catch (error) {
        console.error('Failed to load chat rooms:', error);
    }
}

function formatRoomName(room) {
    return room.charAt(0).toUpperCase() + room.slice(1).toLowerCase();
}

window.addEventListener('DOMContentLoaded', async () => {
    await loadRooms();
    connect();
});