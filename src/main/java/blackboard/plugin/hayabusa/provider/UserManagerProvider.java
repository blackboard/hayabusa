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

package blackboard.plugin.hayabusa.provider;

import java.util.List;
import java.util.Set;

import blackboard.data.navigation.NavigationItem;
import blackboard.data.navigation.NavigationItemControl;
import blackboard.persist.PersistenceException;
import blackboard.persist.PersistenceRuntimeException;
import blackboard.persist.navigation.NavigationItemDbLoader;
import blackboard.platform.context.ContextManagerFactory;
import blackboard.plugin.hayabusa.command.Category;
import blackboard.plugin.hayabusa.command.Command;
import blackboard.plugin.hayabusa.command.SimpleCommand;
import blackboard.portal.data.Module;
import blackboard.portal.persist.ModuleDbLoader;
import blackboard.portal.view.FramesetUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A {@link Provider} for ADMIN EMAIL COMMANDS.
 * 
 * @author ZHAOXIA YANG
 * @since 1.0
 */
public class UserManagerProvider implements Provider
{

  private static final String INTERNAL_HANDLER = "list_users";

  @Override
  public Iterable<Command> getCommands()
  {
    try
    {
      // TODO wire these dependencies as fields/constructor inject
      NavigationItemDbLoader niLoader = NavigationItemDbLoader.Default.getInstance();
      ModuleDbLoader moduleLoader = ModuleDbLoader.Default.getInstance();
      Module module = moduleLoader.loadByExtRef( "platform/admin-users" );

      NavigationItemControl family = NavigationItemControl.createInstance( niLoader
          .loadByInternalHandle( INTERNAL_HANDLER ) );

      // For admin
      List<NavigationItemControl> userAdminNics = getUserAdminNics();
      Set<Command> commands = Sets.newTreeSet();

      for ( NavigationItemControl nic : userAdminNics )
      {
        if ( !nic.userHasAccess() )
        {
          continue;
        }
        String title = String.format( "%s - %s: %s", module.getTitle(), family.getLabel(), nic.getLabel() );
        String url = processUri( nic );
        url = FramesetUtil.getTabGroupUrl(blackboard.data.navigation.Tab.TabType.admin, url);
        commands.add( new SimpleCommand( title, url, Category.SYSTEM_ADMIN ) );
      }

      List<NavigationItemControl> personalEditNics = getPersonalEditNics();

      for ( NavigationItemControl nic : personalEditNics )
      {
        if ( !nic.userHasAccess() )
        {
          continue;
        }
        String title = String.format( "%s: %s", family.getLabel(), nic.getLabel() );
        String uri = processUri( nic );
        uri = FramesetUtil.getTabGroupUrl(blackboard.data.navigation.Tab.TabType.my_institution, uri);
       commands.add( new SimpleCommand( title, uri, Category.USER ) );
      }
      return commands;
    }
    catch ( PersistenceException e )
    {
      throw new PersistenceRuntimeException( e );
    }
  }

  private String processUri( NavigationItemControl nic )
  {
    String uri = nic.getUrl();
    blackboard.platform.context.Context ctx = ContextManagerFactory.getInstance().getContext();
    uri = uri.replaceAll( "@X@target_user.pk_string@X@", ctx.getUserId().getExternalString() );    
    return uri;
  }

  private List<NavigationItemControl> getPersonalEditNics() throws PersistenceException
  {
    NavigationItemDbLoader niLoader = NavigationItemDbLoader.Default.getInstance();
    // For Personal Info Edit
    List<NavigationItem> personalEditNis = Lists.newArrayList( niLoader.loadByInternalHandle( "my_inst_personal_edit" ),
                                                               niLoader.loadByInternalHandle( "my_inst_personal_change_password" ) );
    List<NavigationItemControl> personalEditNics = NavigationItemControl.createList( personalEditNis );
    return personalEditNics;
  }

  private List<NavigationItemControl> getUserAdminNics() throws PersistenceException
  {
    NavigationItemDbLoader niLoader = NavigationItemDbLoader.Default.getInstance();
    // For admin
    List<NavigationItem> userAdminNis = Lists.newArrayList( niLoader.loadByInternalHandle( "create_user" ),
                                                            niLoader.loadByInternalHandle( "batch_create_users" ),
                                                            niLoader.loadByInternalHandle( "batch_remove_users" ) );
    List<NavigationItemControl> userAdminNics = NavigationItemControl.createList( userAdminNis );
    return userAdminNics;
  }
}
