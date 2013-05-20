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

package blackboard.plugin.hayabusa.controller;

import blackboard.plugin.hayabusa.command.Command;
import blackboard.plugin.hayabusa.provider.Provider;
import blackboard.plugin.hayabusa.provider.ProviderService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Provider REST controller.
 * 
 * @author Danny Thomas
 * @since 1.0
 */
@Controller
@RequestMapping( ProviderRestController.PREFIX )
public class ProviderRestController
{
  protected static final String PREFIX = "/provider";

  @Autowired
  private ProviderService _providerService;

  @RequestMapping( value = "commands", method = RequestMethod.GET )
  @ResponseStatus( value = HttpStatus.OK )
  public @ResponseBody CommandResponse getCommands()
  {
    List<Provider> providers = _providerService.getProviders();
    List<Iterable<Command>> commands = Lists.newArrayList();
    for ( Provider provider : providers )
    {
      commands.add( provider.getCommands() );
    }

    CommandList commandList = new CommandList();
    Iterables.addAll( commandList, Iterables.concat( commands ) );
    return new CommandResponse( commandList );
  }

  private static class CommandResponse
  {
    private final CommandList _commands;

    public CommandResponse( CommandList commands )
    {
      _commands = commands;
    }

    @SuppressWarnings( "unused" )
    public CommandList getCommands()
    {
      return _commands;
    }
  }

  @SuppressWarnings( "serial" )
  private static class CommandList extends ArrayList<Command>{
  }

}
