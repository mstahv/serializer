
package org.eclipse.serializer.entity;

/*-
 * #%L
 * Eclipse Serializer Base
 * %%
 * Copyright (C) 2023 MicroStream Software
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

import static org.eclipse.serializer.util.X.notNull;

public final class EntityLayerLogging extends EntityLayer
{
	private final EntityLogger logger;
	
	protected EntityLayerLogging(final Entity inner, final EntityLogger logger)
	{
		super(inner);
		
		this.logger = notNull(logger);
	}
	
	@Override
	protected void entityCreated()
	{
		this.logger.entityCreated(this.entityIdentity(), this.entityData());
		
		super.entityCreated();
	}
	
	@Override
	protected Entity entityData()
	{
		final Entity data = super.entityData();
		
		this.logger.afterRead(this.entityIdentity(), data);
		
		return data;
	}
	
	@Override
	protected boolean updateEntityData(final Entity newData)
	{
		final Entity identity = this.entityIdentity();
		
		this.logger.beforeUpdate(identity, newData);
		
		final boolean success = super.updateEntityData(newData);
		
		this.logger.afterUpdate(identity, newData, success);
		
		return success;
	}
	
}
