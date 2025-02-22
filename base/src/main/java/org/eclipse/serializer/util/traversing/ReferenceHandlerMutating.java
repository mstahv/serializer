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

import java.util.function.Predicate;

import org.eclipse.serializer.collections.types.XSet;

public final class ReferenceHandlerMutating extends AbstractReferenceHandler
{
	///////////////////////////////////////////////////////////////////////////
	// instance fields //
	////////////////////

	private final TraversalMutator  traversalMutator ;
	private final MutationListener  mutationListener ;
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////
	
	ReferenceHandlerMutating(
		final TypeTraverserProvider  traverserProvider,
		final XSet<Object>           alreadyHandled   ,
		final TraversalPredicateSkip predicateSkip    ,
		final TraversalPredicateNode predicateNode    ,
		final TraversalPredicateLeaf predicateLeaf    ,
		final TraversalPredicateFull predicateFull    ,
		final Predicate<Object>      predicateHandle  ,
		final TraversalMutator       traversalMutator ,
		final MutationListener       mutationListener
		
	)
	{
		super(traverserProvider, alreadyHandled, predicateSkip, predicateNode, predicateLeaf, predicateFull, predicateHandle);
		this.traversalMutator = traversalMutator;
		this.mutationListener = mutationListener;
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// methods //
	////////////
	
	@Override
	final <T> void handleFull(final T instance, final TypeTraverser<T> traverser)
	{
		traverser.traverseReferences(instance, this, this.traversalMutator, this.mutationListener);
	}
	
	@Override
	final <T> void handleLeaf(final T instance, final TypeTraverser<T> traverser)
	{
		traverser.traverseReferences(instance, this.traversalMutator, this.mutationListener);
	}
	
}
