package org.eclipse.serializer.collections.sorting;

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

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Composite type to guarantee that the implementation of {@link Sortable} and {@link Consumer} refers to the same
 * parametrized type.
 *
 * @param <E> the type of the input to the operation
 */
public interface SortableProcedure<E> extends Sortable<E>, Consumer<E>
{
	public static <E> void sortIfApplicable(final Consumer<E> procedure, final Comparator<? super E> comparator)
	{
		if(comparator == null || !(procedure instanceof SortableProcedure<?>))
		{
			return;
		}
		((SortableProcedure<E>)procedure).sort(comparator);
	}
}
