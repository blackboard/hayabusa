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

import blackboard.data.navigation.NavigationItemControl;
import blackboard.persist.PersistenceException;
import blackboard.persist.PersistenceRuntimeException;
import blackboard.persist.navigation.NavigationItemDbLoader;
import blackboard.plugin.hayabusa.command.Command;
import blackboard.plugin.hayabusa.command.SimpleCommand;
import blackboard.portal.data.Module;
import blackboard.portal.persist.ModuleDbLoader;

import java.util.*;

import com.google.common.collect.*;

/**
 * A {@link Provider} for navigation item links.
 * 
 * @author Danny Thomas
 * @since 1.0
 */
public class ModuleItemProvider implements Provider
{

  @Override
  public Iterable<Command> getCommands()
  {
    try
    {
      // TODO wire these dependencies as fields/constructor inject
      ModuleDbLoader moduleLoader = ModuleDbLoader.Default.getInstance();
      NavigationItemDbLoader niLoader = NavigationItemDbLoader.Default.getInstance();

      Multimap<Module, NavigationItemControl> nicByModule = ArrayListMultimap.create();
      for ( Module module : moduleLoader.heavyLoadByModuleType( "bb/admin" ) )
      {
        String subgroup = module.getPortalExtraInfo().getExtraInfo().getValue( "subgroup" );
        List<NavigationItemControl> nics = NavigationItemControl.createList( niLoader.loadBySubgroup( subgroup ) );
        nicByModule.putAll( module, nics );
      }

      Set<Command> commands = Sets.newTreeSet();
      for ( Module module : nicByModule.keys() )
      {
        Collection<NavigationItemControl> nics = nicByModule.get( module );
        for ( NavigationItemControl nic : nics )
        {
          if ( !nic.userHasAccess() )
          {
            continue;
          }
          String title = String.format( "%s: %s", module.getTitle(), nic.getLabel() );
          commands.add( new SimpleCommand( title, nic.getUrl() ) );
        }
      }
      return commands;
    }
    catch ( PersistenceException e )
    {
      throw new PersistenceRuntimeException( e );
    }
  }

}
