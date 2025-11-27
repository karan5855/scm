const user = JSON.parse(localStorage.getItem('user') || 'null');
if(!user){ window.location = 'login.html'; }

function loadComplaints(){
  fetch('/api/complaints/user/' + user.id)
    .then(res => res.json())
    .then(data => {
      const list = document.getElementById('complaints');
      list.innerHTML = '';
      data.forEach(c => {
        const el = document.createElement('div');
        el.className = 'card';
        el.innerHTML = `<b>${c.category}</b><br>${c.description}<br>Status: ${c.status}`;
        list.appendChild(el);
      });
    });
}

function logout(){ localStorage.removeItem('user'); window.location='login.html'; }

loadComplaints();