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

import org.eclipse.serializer.util.UtilStackTrace;

/**
 * This exception indicates that the storage is currently in a read only mode
 * that denies the usage of {@link org.eclipse.serializer.persistence.types.PersistenceStorer} methods.
 * 
 */
@SuppressWarnings("serial")
public class PersistenceExceptionStorerDeactivated extends PersistenceException
{
	@Override
	public String getMessage()
	{
		return "PersistenceStorer is in read only mode. Calling the method '" + UtilStackTrace.getThrowingMethodName(this) + "' is not allowed!";
	}
}
