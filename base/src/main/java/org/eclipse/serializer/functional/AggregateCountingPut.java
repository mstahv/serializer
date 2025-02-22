package org.eclipse.serializer.functional;

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

import org.eclipse.serializer.collections.types.XPuttingCollection;

public class AggregateCountingPut<E> implements Aggregator<E, Integer>
{
	///////////////////////////////////////////////////////////////////////////
	// instance fields //
	////////////////////

	private final XPuttingCollection<? super E> target;
	private int putCount;



	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	public AggregateCountingPut(final XPuttingCollection<? super E> target)
	{
		super();
		this.target = target;
	}



	///////////////////////////////////////////////////////////////////////////
	// override methods //
	/////////////////////

	@Override
	public final void accept(final E element)
	{
		if(this.target.put(element))
		{
			this.putCount++;
		}
	}

	@Override
	public final Integer yield()
	{
		return this.putCount;
	}

}
