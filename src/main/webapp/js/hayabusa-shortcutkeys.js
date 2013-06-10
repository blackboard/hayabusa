//open command bar
Mousetrap.bindGlobal(['command+shift+o', 'ctrl+shift+o'], function(e) {
  document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'
  return false;
});

//close command bar
Mousetrap.bindGlobal(['command+shift+u', 'ctrl+shift+u'], function(e) {
  document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'
  return false;
});