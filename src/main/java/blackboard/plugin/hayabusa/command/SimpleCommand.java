/*
 * (C) Copyright Blackboard Inc. 2013 - All Rights Reserved
 * 
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership. Blackboard
 * Inc. licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * BLACKBOARD MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR NON-
 * INFRINGEMENT. BLACKBOARD SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package blackboard.plugin.hayabusa.command;

import static com.google.common.base.Preconditions.checkNotNull;

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

  public SimpleCommand( String title, String uri )
  {
    _title = checkNotNull( title );
    _uri = checkNotNull( uri );
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

  public String toString()
  {
    ToStringHelper helper = com.google.common.base.Objects.toStringHelper( this );
    helper.add( "title", _title );
    helper.add( "uri", _uri );
    return helper.toString();
  }

}
