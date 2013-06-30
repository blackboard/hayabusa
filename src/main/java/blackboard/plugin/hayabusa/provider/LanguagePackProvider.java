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

import blackboard.platform.context.Context;
import blackboard.platform.context.ContextManagerFactory;
import blackboard.platform.intl.BbLocale;
import blackboard.platform.intl.LocaleManagerFactory;
import blackboard.platform.security.NonceUtil;
import blackboard.plugin.hayabusa.command.*;
import blackboard.servlet.data.LocalePickerOption;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * A {@link Provider} for courses
 * 
 * @author Noriaki Tatsumi
 * @since 1.0
 */
public class LanguagePackProvider implements Provider
{
  public static final String NONCE_ID = "personalSettingsForm";
  public static final String NONCE_CONTEXT = "/webapps/blackboard";

  @Override
  public Iterable<Command> getCommands()
  {
    Iterator<LocalePickerOption> ll = getLocaleList().iterator();
    Set<Command> commands = Sets.newTreeSet();
    Context bbCtxt = ContextManagerFactory.getInstance().getContext();

    while ( ll.hasNext() )
    {
      LocalePickerOption option = ll.next();

      HashMap<String, String> params = new HashMap<String, String>();
      params.put( "top_Submit", "Submit" );
      params.put( "target", "/webapps/portal/frameset.jsp" );
      params.put( "locale", option.getLocaleLocale() );
      params.put( "showInstructions", "true" );
      params.put( NonceUtil.NONCE_KEY, NonceUtil.create( bbCtxt.getSession(), NONCE_ID, NONCE_CONTEXT ) );

      commands.add( new PostCommand( option.getLocaleName(),
                                     "/webapps/blackboard/execute/personalSettings?command=save",
                                     Category.LANGUAGE_PACK, params ) );
    }
    return commands;
  }

  private List<LocalePickerOption> getLocaleList()
  {
    List<LocalePickerOption> optionsList = new ArrayList<LocalePickerOption>();

    List<BbLocale> localeList = LocaleManagerFactory.getInstance().getAll();
    Iterator<BbLocale> iterObj = localeList.iterator();
    while ( iterObj.hasNext() )
    {
      BbLocale locale = iterObj.next();
      if ( !locale.getIsAvailable() )
        continue;
      if ( !locale.getIsUserEnabled() )
        continue;

      LocalePickerOption lpOption = new LocalePickerOption( false, locale.getName(), locale.getLocale() );
      optionsList.add( lpOption );
    }
    return optionsList;
  }

}
