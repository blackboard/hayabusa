//open command bar
Mousetrap.bindGlobal(['shift+up'], function(e) {
  top.document.getElementById('light').style.display='block';
  return false;
});

//close command bar
Mousetrap.bindGlobal(['shift+down', 'esc'], function(e) {
<<<<<<< HEAD
	document.getElementById('lightboxInput').value="";	
	document.getElementById('light').style.display='none';
	
=======
  top.document.getElementById('light').style.display='none';
>>>>>>> aab9096dfb31aeb7935d35545feb75762ded6d3c
  return false;
});

//open command bar and trigger voice search
Mousetrap.bindGlobal(['ctrl+shift+.', 'command+shift+.'], function(e) {
  top.document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'
  return false;
});
