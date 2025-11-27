    function login(){
    const email = document.getElementById("email").value;
     const password = document.getElementById("password").value;
  fetch('/api/auth/login', {
    method: 'POST',
    headers:{'Content-Type':'application/json'},
    body: JSON.stringify({email,password})
  })
  .then(async res => {
    if(res.status === 200){
      const user = await res.json();
      localStorage.setItem('user', JSON.stringify(user));
      if(user.role === 'ADMIN') window.location='admin-dashboard.html';
      else window.location='user-dashboard.html';
    } else {
      document.getElementById('msg').innerText = 'Invalid credentials';
    }
  })
  .catch(err => { document.getElementById('msg').innerText = 'Error connecting'; });
}