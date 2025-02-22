package org.eclipse.serializer.util.traversing;

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

import java.util.Collection;


public final class TraverserCollectionOld implements TypeTraverser<Collection<?>>
{
	@Override
	public final void traverseReferences(
		final Collection<?>     instance,
		final TraversalEnqueuer enqueuer
	)
	{
		instance.forEach(current ->
		{
			enqueuer.enqueue(current);
		});
	}
	
	@Override
	public final void traverseReferences(
		final Collection<?>     instance,
		final TraversalEnqueuer enqueuer,
		final TraversalAcceptor acceptor
	)
	{
		try
		{
			instance.forEach(current ->
			{
				if(acceptor.acceptReference(current, instance))
				{
					enqueuer.enqueue(current);
				}
			});
		}
		catch(final AbstractTraversalSkipSignal s)
		{
			// any skip signal reaching this point means abort the whole instance, in one way or another
		}
	}
	
	@Override
	public final void traverseReferences(
		final Collection<?>     instance        ,
		final TraversalEnqueuer enqueuer        ,
		final TraversalMutator  mutator         ,
		final MutationListener  mutationListener
	)
	{
		try
		{
			instance.forEach(current ->
			{
				enqueuer.enqueue(current);
				if(mutator.mutateReference(current, instance) != current)
				{
					throw new UnsupportedOperationException();
				}
			});
		}
		catch(final AbstractTraversalSkipSignal s)
		{
			// any skip signal reaching this point means abort the whole instance, in one way or another
		}
	}
	
	@Override
	public final void traverseReferences(
		final Collection<?>     instance        ,
		final TraversalEnqueuer enqueuer        ,
		final TraversalAcceptor acceptor        ,
		final TraversalMutator  mutator         ,
		final MutationListener  mutationListener
	)
	{
		try
		{
			instance.forEach(current ->
			{
				if(acceptor.acceptReference(current, instance))
				{
					enqueuer.enqueue(current);
				}
				if(mutator.mutateReference(current, instance) != current)
				{
					throw new UnsupportedOperationException();
				}
			});
		}
		catch(final AbstractTraversalSkipSignal s)
		{
			// any skip signal reaching this point means abort the whole instance, in one way or another
		}
	}

	
	@Override
	public final void traverseReferences(
		final Collection<?>     instance,
		final TraversalAcceptor acceptor
	)
	{
		try
		{
			instance.forEach(current ->
			{
				acceptor.acceptReference(current, instance);
			});
		}
		catch(final AbstractTraversalSkipSignal s)
		{
			// any skip signal reaching this point means abort the whole instance, in one way or another
		}
	}
	
	@Override
	public final void traverseReferences(
		final Collection<?>     instance        ,
		final TraversalMutator  mutator         ,
		final MutationListener  mutationListener
	)
	{
		try
		{
			instance.forEach(current ->
			{
				if(mutator.mutateReference(current, instance) != current)
				{
					throw new UnsupportedOperationException();
				}
			});
		}
		catch(final AbstractTraversalSkipSignal s)
		{
			// any skip signal reaching this point means abort the whole instance, in one way or another
		}
	}
	
	@Override
	public final void traverseReferences(
		final Collection<?>     instance        ,
		final TraversalAcceptor acceptor        ,
		final TraversalMutator  mutator         ,
		final MutationListener  mutationListener
	)
	{
		try
		{
			instance.forEach(current ->
			{
				acceptor.acceptReference(current, instance);
				if(mutator.mutateReference(current, instance) != current)
				{
					throw new UnsupportedOperationException();
				}
			});
		}
		catch(final AbstractTraversalSkipSignal s)
		{
			// any skip signal reaching this point means abort the whole instance, in one way or another
		}
	}
		
}
