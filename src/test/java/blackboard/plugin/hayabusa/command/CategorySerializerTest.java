package blackboard.plugin.hayabusa.command;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.hamcrest.core.IsInstanceOf;

public class CategorySerializerTest
{
  public void testNulls() throws Exception
  {
    CategorySerializer serializer = new CategorySerializer();

    try
    {
      serializer.serialize( Category.COURSE, null, null );
      fail( "Expected an NullPointerException" );
    }
    catch ( Exception e )
    {
      assertThat( e, new IsInstanceOf( NullPointerException.class ) );
    }

    try
    {
      serializer.serialize( null, new JsonFactory().createJsonGenerator( new StringWriter() ), null );
      fail( "Expected an NullPointerException" );
    }
    catch ( Exception e )
    {
      assertThat( e, new IsInstanceOf( NullPointerException.class ) );
    }

  }
}
