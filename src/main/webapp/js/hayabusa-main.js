jQuery.noConflict();
var matchedJSON =
{};

jQuery( function()
{
  // /provider/commands
  jQuery.getJSON( "/webapps/bb-hayabusa-BBLEARN/execute/provider/commands", function( data )
  {
    jQuery( "#lightboxInput" ).autocomplete(
    {
        autoFocus : true,
        source : data.commands,
        selectFirst : true,
        minLength : 1,
        select : function( event, ui )
        {
          matchedJSON = ui.item;
          parent.frames['content'].location.href = getUri();
        }
    } ).data( 'ui-Autocomplete' )._renderItem = function( ul, item )
    {
      return item.title;
    }
  } )
} );

function getUri()
{
  var uri = matchedJSON.uri;
  return uri;
}
