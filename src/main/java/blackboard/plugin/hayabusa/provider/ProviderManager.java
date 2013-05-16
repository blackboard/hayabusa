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

import com.google.common.collect.ImmutableList;

/**
 * Manage known provider instances.
 * 
 * @author Danny Thomas
 * @since 1.0
 */
public class ProviderManager
{
  public List<Provider> getProviders()
  {
    return ImmutableList.<Provider> of( new ModuleItemProvider() ).asList();
  }

}
