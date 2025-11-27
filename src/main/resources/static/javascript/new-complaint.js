const user = JSON.parse(localStorage.getItem('user') || 'null');
if(!user) window.location='login.html';

function submitComplaint(){
  const category = document.getElementById('category').value;
  const description = document.getElementById('description').value;
  const payload = {
    user: { id: user.id },
    category,
    description
  };
  fetch('/api/complaints/create', {
    method:'POST',
    headers:{'Content-Type':'application/json'},
    body: JSON.stringify(payload)
  })
  .then(async res => {
    if(res.status === 200){
      document.getElementById('msg').innerText = 'Submitted';
      setTimeout(()=> window.location='user-dashboard.html', 800);
    } else {
      document.getElementById('msg').innerText = 'Error submitting';
    }
  });
}