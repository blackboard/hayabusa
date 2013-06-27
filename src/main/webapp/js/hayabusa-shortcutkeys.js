//open command bar
Mousetrap.bindGlobal(['shift+up', 'ctrl+shift+.'], function(e) {
  document.getElementById('light').style.display='block';
  return false;
});

//close command bar
Mousetrap.bindGlobal(['shift+down', 'esc'], function(e) {
  document.getElementById('light').style.display='none';
  return false;
});


