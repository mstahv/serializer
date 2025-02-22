package org.eclipse.serializer.persistence.exceptions;

/*-
 * #%L
 * Eclipse Serializer Persistence
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


public class PersistenceExceptionTypeHandlerConsistencyUnhandledTypeId
extends PersistenceExceptionTypeHandlerConsistency
{
	///////////////////////////////////////////////////////////////////////////
	// instance fields //
	////////////////////

	final long typeId ;



	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	public PersistenceExceptionTypeHandlerConsistencyUnhandledTypeId(
		final long typeId
	)
	{
		this(typeId, null, null);
	}

	public PersistenceExceptionTypeHandlerConsistencyUnhandledTypeId(
		final long typeId,
		final String message
	)
	{
		this(typeId, message, null);
	}

	public PersistenceExceptionTypeHandlerConsistencyUnhandledTypeId(
		final long typeId,
		final Throwable cause
	)
	{
		this(typeId, null, cause);
	}

	public PersistenceExceptionTypeHandlerConsistencyUnhandledTypeId(
		final long typeId,
		final String message, final Throwable cause
	)
	{
		this(typeId, message, cause, true, true);
	}

	public PersistenceExceptionTypeHandlerConsistencyUnhandledTypeId(
		final long typeId,
		final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace
	)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		this.typeId            = typeId           ;
	}



	///////////////////////////////////////////////////////////////////////////
	// getters //
	////////////

	public long getTypeId()
	{
		return this.typeId;
	}



	///////////////////////////////////////////////////////////////////////////
	// override methods //
	/////////////////////

	@Override
	public String getMessage()
	{
		return "No type handler found for type id \"" + this.typeId + "\"."
			+ (super.getMessage() != null ? " Details: " + super.getMessage() : "")
		;
	}



}
