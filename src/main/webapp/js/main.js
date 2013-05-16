jQuery.noConflict();

var matchedJSON =
{};

jQuery( function()
{
  // /provider/commands
  jQuery.getJSON( "/webapps/bb-hayabusa-BBLEARN/execute/provider/commands", function( data )
  {
    parse( data.commands );
    jQuery( "#lightbox_input" ).autocomplete(
    {
        autoFocus : true,
        source : data.commands,
        selectFirst : true,
        minLength : 1,
        select : function( event, ui )
        {
          matchedJSON = ui.item;
          jQuery( "#searchForm" ).attr( "action", getUri() );
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
  jQuery( items ).each( function()
  {
    this.value = this.title;
  } );
}
