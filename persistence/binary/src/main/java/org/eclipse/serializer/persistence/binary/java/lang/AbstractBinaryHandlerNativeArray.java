package org.eclipse.serializer.persistence.binary.java.lang;

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

import org.eclipse.serializer.util.X;
import org.eclipse.serializer.collections.types.XGettingSequence;
import org.eclipse.serializer.collections.types.XImmutableSequence;
import org.eclipse.serializer.persistence.binary.internal.AbstractBinaryHandlerCustom;
import org.eclipse.serializer.persistence.types.PersistenceTypeDefinitionMemberFieldGeneric;
import org.eclipse.serializer.persistence.types.PersistenceTypeDefinitionMemberFieldGenericComplex;


public abstract class AbstractBinaryHandlerNativeArray<A> extends AbstractBinaryHandlerCustom<A>
{
	///////////////////////////////////////////////////////////////////////////
	// static methods //
	///////////////////

	protected static final XImmutableSequence<PersistenceTypeDefinitionMemberFieldGenericComplex>
	defineElementsType(final Class<?> componentType)
	{
		// admitted, this is a little crazy. But also very compact.
		return
			X.Constant(
				Complex(
					"elements",
					CustomField(componentType, "element")
				)
			)
		;
	}



	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	public AbstractBinaryHandlerNativeArray(
		final Class<A>                                                                arrayType   ,
		final XGettingSequence<? extends PersistenceTypeDefinitionMemberFieldGeneric> customFields
	)
	{
		super(arrayType, customFields);
	}



	///////////////////////////////////////////////////////////////////////////
	// override methods //
	/////////////////////
	
	@Override
	public final boolean hasPersistedVariableLength()
	{
		return true;
	}

	@Override
	public final boolean hasVaryingPersistedLengthInstances()
	{
		// note: java array might become truncatable in the future, so instance length might indeed change
		return true;
	}

}
