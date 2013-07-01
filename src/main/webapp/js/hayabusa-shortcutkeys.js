//open command bar
Mousetrap.bindGlobal(['shift+up'], function(e) {
  top.document.getElementById('light').style.display='block';
  return false;
});

//close command bar
Mousetrap.bindGlobal(['shift+down', 'esc'], function(e) {
  top.document.getElementById('lightboxInput').value="";	
  top.document.getElementById('light').style.display='none';

  return false;
});

//open command bar and trigger voice search
Mousetrap.bindGlobal(['ctrl+shift+.', 'command+shift+.'], function(e) {
  top.document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'
  return false;
});
