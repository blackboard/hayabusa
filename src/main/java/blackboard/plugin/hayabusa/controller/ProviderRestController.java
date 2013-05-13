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

import blackboard.plugin.hayabusa.provider.Provider;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Providers controller.
 * 
 * @author Danny Thomas
 * @since 1.0
 */
@Controller
@RequestMapping( ProviderRestController.PREFIX )
public class ProviderRestController
{
  static final String PREFIX = "/provider";

  @RequestMapping( value = "data", method = RequestMethod.GET )
  @ResponseStatus( value = HttpStatus.OK )
  public HttpEntity<List<Provider>> getProviderData()
  {
    return new HttpEntity<List<Provider>>( Collections.<Provider>emptyList() );
  }

}
