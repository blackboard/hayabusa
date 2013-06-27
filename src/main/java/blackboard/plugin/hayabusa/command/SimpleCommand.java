/*
 * Copyright (c) 2013, Blackboard, Inc. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer. 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided with the distribution. 3. Neither the
 * name of the Blackboard Inc. nor the names of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * BLACKBOARD MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR NON-
 * INFRINGEMENT. BLACKBOARD SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package blackboard.plugin.hayabusa.command;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

/**
 * A simple {@link Command} implementation.
 * 
 * @author Danny Thomas
 * @since 1.0
 */
public class SimpleCommand implements Command
{
  private String _title;
  private String _uri;
  private Category _category;

  public SimpleCommand( String title, String uri, Category category )
  {
    _title = checkNotNull( title );
    _uri = checkNotNull( uri );
    _category = checkNotNull( category );
  }

  @Override
  public String getTitle()
  {
    return _title;
  }

  @Override
  public String getUri()
  {
    return _uri;
  }

  @Override
  public Category getCategory()
  {
    return _category;
  }

  @Override
  public String toString()
  {
    ToStringHelper helper = com.google.common.base.Objects.toStringHelper( this );
    helper.add( "title", _title );
    helper.add( "uri", _uri );
    helper.add( "category", _category );
    return helper.toString();
  }

  @Override
  public int hashCode()
  {
    return Objects.hashCode( _title, _uri, _category );
  }

  @Override
  public boolean equals( @Nullable Object obj )
  {
    if ( obj instanceof SimpleCommand )
    {
      SimpleCommand command = (SimpleCommand) obj;
      return Objects.equal( _title, command._title ) && Objects.equal( _uri, command._uri )
             && Objects.equal( _category, command._uri );
    }
    return false;
  }

  @Override
  public int compareTo( Command o )
  {
    int categoryOrder = _category.compareTo( o.getCategory() );
    return categoryOrder == 0 ? _title.compareTo( o.getTitle() ) : categoryOrder;
  }

}
