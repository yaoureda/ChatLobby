const form = document.getElementById('register-form');
const errorEl = document.getElementById('error');
const successEl = document.getElementById('success');

form.addEventListener('submit', async function (event) {
    event.preventDefault();

    errorEl.style.display = 'none';
    successEl.style.display = 'none';

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    try {
        const response = await fetch('/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });

        const text = await response.text();

        if (!response.ok) {
            throw new Error(text || 'Registration failed');
        }

        successEl.textContent = 'Account created successfully. Redirecting to login...';
        successEl.style.display = 'block';

        setTimeout(() => {
            window.location.href = '/login';
        }, 1200);

    } catch (error) {
        errorEl.textContent = error.message;
        errorEl.style.display = 'block';
    }
});