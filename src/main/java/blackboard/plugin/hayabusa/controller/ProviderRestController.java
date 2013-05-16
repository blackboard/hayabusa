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

package blackboard.plugin.hayabusa.controller;

import blackboard.plugin.hayabusa.command.Command;
import blackboard.plugin.hayabusa.provider.Provider;
import blackboard.plugin.hayabusa.provider.ProviderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
  public HttpEntity<List<Command>> getCommands()
  {
    List<Provider> providers = _providerService.getProviders();
    List<Iterable<Command>> commands = Lists.newArrayList();
    for ( Provider provider : providers )
    {
      commands.add( provider.getCommands() );
    }
    return new HttpEntity<List<Command>>( Lists.newArrayList( Iterables.concat( commands ) ) );
  }

}
