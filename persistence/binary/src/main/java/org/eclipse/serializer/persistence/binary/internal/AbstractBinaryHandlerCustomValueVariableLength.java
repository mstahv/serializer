package org.eclipse.serializer.persistence.binary.internal;

/*-
 * #%L
 * Eclipse Serializer Persistence Binary
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

import org.eclipse.serializer.collections.types.XGettingSequence;
import org.eclipse.serializer.persistence.types.PersistenceTypeDefinitionMemberFieldGeneric;


public abstract class AbstractBinaryHandlerCustomValueVariableLength<T, S>
extends AbstractBinaryHandlerCustomValue<T, S>
{
	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	public AbstractBinaryHandlerCustomValueVariableLength(
		final Class<T>                                                                type        ,
		final XGettingSequence<? extends PersistenceTypeDefinitionMemberFieldGeneric> customFields
	)
	{
		super(type, customFields);
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// methods //
	////////////
	
	@Override
	public final boolean hasPersistedVariableLength()
	{
		return true;
	}
	
	@Override
	public final boolean hasVaryingPersistedLengthInstances()
	{
		return true;
	}
	
}
