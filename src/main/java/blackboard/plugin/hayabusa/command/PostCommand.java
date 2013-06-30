package blackboard.plugin.hayabusa.command;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects.ToStringHelper;

import java.util.Map;

public class PostCommand extends SimpleCommand
{
  private Map<String, String> _parameters;

  public PostCommand( String title, String uri, Category category, Map<String, String> parameters )
  {
    super( title, uri, category );
    _parameters = checkNotNull( parameters );
  }

  public Map<String, String> getParameters()
  {
    return _parameters;
  }

  public void setParameters( Map<String, String> parameters )
  {
    _parameters = parameters;
  }

  @Override
  public String toString()
  {
    ToStringHelper helper = com.google.common.base.Objects.toStringHelper( this );
    helper.add( "title", getTitle() );
    helper.add( "uri", getUri() );
    helper.add( "category", getCategory() );
    helper.add( "parameters", _parameters );
    return helper.toString();
  }
}
