package org.eclipse.serializer.persistence.exceptions;

/*-
 * #%L
 * Eclipse Serializer Persistence
 * %%
 * Copyright (C) 2023 Eclipse Foundation
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

public class PersistenceExceptionTypeNotPersistable extends PersistenceException
{
	///////////////////////////////////////////////////////////////////////////
	// instance fields //
	////////////////////

	private final Class<?> type;



	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	public PersistenceExceptionTypeNotPersistable(
		final Class<?> type
	)
	{
		this(type, null, null);
	}

	public PersistenceExceptionTypeNotPersistable(
		final Class<?> type,
		final String message
	)
	{
		this(type, message, null);
	}

	public PersistenceExceptionTypeNotPersistable(
		final Class<?> type,
		final Throwable cause
	)
	{
		this(type, null, cause);
	}

	public PersistenceExceptionTypeNotPersistable(
		final Class<?> type,
		final String message, final Throwable cause
	)
	{
		this(type, message, cause, true, true);
	}

	public PersistenceExceptionTypeNotPersistable(
		final Class<?> type,
		final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace
	)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		this.type = type;
	}



	///////////////////////////////////////////////////////////////////////////
	// getters //
	////////////

	public Class<?> getType()
	{
		return this.type;
	}



	///////////////////////////////////////////////////////////////////////////
	// override methods //
	/////////////////////

	@Override
	public String getMessage()
	{
		return "Type not persistable: \"" + this.type + "\"."
			+ (super.getMessage() != null ? " Details: " + super.getMessage() : "")
		;
	}

}
