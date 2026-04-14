const params = new URLSearchParams(window.location.search);
if (params.has('error')) {
    document.getElementById('error').style.display = 'block';
}