package org.eclipse.serializer.collections;

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

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.eclipse.serializer.collections.old.OldCollection;
import org.eclipse.serializer.collections.types.XGettingCollection;
import org.eclipse.serializer.collections.types.XProcessingSequence;
import org.eclipse.serializer.equality.Equalator;

public class SubProcessor<E> extends SubView<E> implements XProcessingSequence<E>
{
	/* (12.07.2012 TM)FIXME: complete SubProcessor implementation
	 * See all "FIXME"s
	 */

	@Override
	public E fetch()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public <C extends Consumer<? super E>> C moveSelection(final C target, final long... indices)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public E pick()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public E pinch()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public E pop()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public E removeAt(final long index)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public XProcessingSequence<E> removeRange(final long offset, final long length)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public XProcessingSequence<E> retainRange(final long offset, final long length)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public long removeSelection(final long[] indices)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public E retrieve(final E element)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public E retrieveBy(final Predicate<? super E> predicate)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public boolean removeOne(final E element)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public <C extends Consumer<? super E>> C moveTo(final C target, final Predicate<? super E> predicate)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public final <P extends Consumer<? super E>> P process(final P procedure)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public long removeBy(final Predicate<? super E> predicate)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public long removeDuplicates(final Equalator<? super E> equalator)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public void clear()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public long consolidate()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public long nullRemove()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public long optimize()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public long remove(final E element)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public long removeAll(final XGettingCollection<? extends E> elements)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public long removeDuplicates()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public long retainAll(final XGettingCollection<? extends E> elements)
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public void truncate()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}


	@Override
	public SubProcessor<E> copy()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public SubProcessor<E> view()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public OldCollection<E> old()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

	@Override
	public SubProcessor<E> toReversed()
	{
		throw new org.eclipse.serializer.meta.NotImplementedYetError(); // FIXME not implemented yet
	}

}
