// aavu chatgpt ae lakhiyu 6e.?
// J: suu ke cho
// aa user register kervano code jate lakhiyo 6e k chatgpt pase l
//chatgpt// ruk tane batavu chat
function registerUser(){
  const name = document.getElementById('name').value;
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  fetch('/api/auth/register', {
    method:'POST',
    headers:{'Content-Type':'application/json'},
    body: JSON.stringify({name,email,password})
  })
  .then(async res => {
    if(res.status === 200){
      window.location = 'login.html';
    } else if(res.status === 409){
      document.getElementById('msg').innerText = 'Email already exists';
    } else {
      document.getElementById('msg').innerText = 'Error';
    }
  })
  .catch(()=> document.getElementById('msg').innerText = 'Network error');
}