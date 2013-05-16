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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

/**
 * A simple provider service implementation.
 * 
 * @author Danny Thomas
 * @since 1.0
 */
@Service
@Scope( value = "singleton" )
public class SimpleProviderService implements ProviderService
{
  @Autowired
  private List<Provider> _providers;

  public List<Provider> getProviders()
  {
    return ImmutableList.copyOf( _providers );
  }

}
