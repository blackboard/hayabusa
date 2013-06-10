//open command bar
Mousetrap.bindGlobal(['shift+up'], function(e) {
  document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'
  return false;
});

//close command bar
Mousetrap.bindGlobal(['shift+down'], function(e) {
  document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'
  return false;
});