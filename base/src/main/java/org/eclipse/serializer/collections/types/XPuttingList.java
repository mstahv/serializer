package org.eclipse.serializer.collections.types;

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




public interface XPuttingList<E> extends XAddingList<E>, XPuttingBag<E>, XPuttingSequence<E>
{
	public interface Creator<E> extends XAddingList.Creator<E>, XPuttingBag.Creator<E>, XPuttingSequence.Creator<E>
	{
		@Override
		public XPuttingList<E> newInstance();
	}


	@SuppressWarnings("unchecked")
	@Override
	public XPuttingList<E> addAll(E... elements);

	@Override
	public XPuttingList<E> addAll(E[] elements, int offset, int length);

	@Override
	public XPuttingList<E> addAll(XGettingCollection<? extends E> elements);

	@SuppressWarnings("unchecked")
	@Override
	public XPuttingList<E> putAll(E... elements);

	@Override
	public XPuttingList<E> putAll(E[] elements, int offset, int length);

	@Override
	public XPuttingList<E> putAll(XGettingCollection<? extends E> elements);

}
