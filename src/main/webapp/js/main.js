var matchedJSON =
{};

$( function()
{
  // /provider/commands
  $.getJSON( "/provider/commands", function( data )
  {
    parse( data.items );
    $( "#keyword" ).autocomplete(
    {
        autoFocus : true,
        source : data.items,
        selectFirst : true,
        minLength : 1,
        select : function( event, ui )
        {
          matchedJSON = ui.item;
          $( "#searchForm" ).attr( "action", getUri() );
        }
    } );
  } )
} );

function getUri()
{
  var uri = matchedJSON.uri;
  return uri;
}

function parse( items )
{
  $( items ).each( function()
  {
    this.value = this.title;
  } );
}
