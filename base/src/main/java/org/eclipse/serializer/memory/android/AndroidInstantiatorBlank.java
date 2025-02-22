package org.eclipse.serializer.memory.android;

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

import org.eclipse.serializer.exceptions.InstantiationRuntimeException;
import org.eclipse.serializer.functional.DefaultInstantiator;


public final class AndroidInstantiatorBlank implements DefaultInstantiator
{
	///////////////////////////////////////////////////////////////////////////
	// static methods //
	///////////////////
	
	public static final AndroidInstantiatorBlank New()
	{
		return new AndroidInstantiatorBlank();
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////
	
	AndroidInstantiatorBlank()
	{
		super();
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// methods //
	////////////
	
	@Override
	public final <T> T instantiate(final Class<T> type) throws InstantiationRuntimeException
	{
		return AndroidInternals.instantiateBlank(type);
	}
		
}
