/*
 * Copyright (c) 2013, Blackboard, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 *    disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the Blackboard Inc. nor the names of its contributors may be used to endorse or promote
 *    products derived from this software without specific prior written permission.
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

package blackboard.plugin.hayabusa.provider;

import blackboard.data.navigation.NavigationItemControl;
import blackboard.persist.PersistenceException;
import blackboard.persist.PersistenceRuntimeException;
import blackboard.persist.navigation.NavigationItemDbLoader;
import blackboard.plugin.hayabusa.command.*;
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
          commands.add( new SimpleCommand( title, nic.getUrl(), Category.SYSTEM_ADMIN ) );
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
