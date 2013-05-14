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

package blackboard.plugin.hayabusa.provider;

import static com.google.common.base.Preconditions.checkNotNull;

import blackboard.data.navigation.NavigationItem;
import blackboard.persist.PersistenceException;
import blackboard.persist.PersistenceRuntimeException;
import blackboard.persist.navigation.NavigationItemDbLoader;
import blackboard.plugin.hayabusa.command.Command;
import blackboard.plugin.hayabusa.command.SimpleCommand;

import java.util.*;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * A {@link Provider} for navigation item links.
 * 
 * @author Danny Thomas
 * @since 1.0
 */
public class NavigationItemProvider implements Provider
{
  private static final Function<NavigationItem, Command> TRANSFORM = new Function<NavigationItem, Command>()
    {
      public Command apply( NavigationItem ni )
      {
        checkNotNull( ni );
        return new SimpleCommand( ni.getLabel(), ni.getHref() );
      }
    };

  @Override
  public Iterable<Command> getCommands()
  {
    try
    {
      NavigationItemDbLoader loader = NavigationItemDbLoader.Default.getInstance();
      List<NavigationItem> items = loader.loadByFamily( "admin_main" );
      return Iterables.transform( items, TRANSFORM );
    }
    catch ( PersistenceException e )
    {
      throw new PersistenceRuntimeException( e );
    }
  }

}
