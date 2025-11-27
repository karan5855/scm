const user = JSON.parse(localStorage.getItem('user') || 'null');
if(!user || user.role !== 'ADMIN') window.location='login.html';

function loadAll(){
  fetch('/api/admin/complaints')
    .then(res => res.json())
    .then(data => {
      const list = document.getElementById('complaints');
      list.innerHTML = '';
      data.forEach(c => {
        const el = document.createElement('div');
        el.className = 'card';
        el.innerHTML = `<b>${c.category}</b> by ${c.user?.name || 'N/A'}<br>${c.description}<br>Status: ${c.status}
        <div style="margin-top:8px;">
          <button onclick="openUpdate(${c.id})">Update Status</button>
        </div>`;
        list.appendChild(el);
      });
    });
}

function openUpdate(id){
  const newStatus = prompt('Enter status (Pending/In-Progress/Solved):');
  if(!newStatus) return;
  const dept = prompt('Assign department (optional):');
  fetch('/api/admin/complaints/' + id + '/status', {
    method:'POST',
    headers:{'Content-Type':'application/json'},
    body: JSON.stringify({status:newStatus, assignedDepartment:dept})
  }).then(()=> loadAll());
}

function logout(){ localStorage.removeItem('user'); window.location='login.html'; }

loadAll();