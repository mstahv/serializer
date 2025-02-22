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

import org.eclipse.serializer.collections.interfaces.ReleasingCollection;


public interface XPreputtingEnum<E> extends XPreputtingSequence<E>, ReleasingCollection<E>
{
	public interface Creator<E> extends XPreputtingSequence.Creator<E>
	{
		@Override
		public XPreputtingEnum<E> newInstance();
	}



	@SuppressWarnings("unchecked")
	@Override
	public XPreputtingEnum<E> prependAll(E... elements);

	@Override
	public XPreputtingEnum<E> prependAll(E[] elements, int srcStartIndex, int srcLength);

	@Override
	public XPreputtingEnum<E> prependAll(XGettingCollection<? extends E> elements);

	@SuppressWarnings("unchecked")
	@Override
	public XPreputtingEnum<E> preputAll(E... elements);

	@Override
	public XPreputtingEnum<E> preputAll(E[] elements, int srcStartIndex, int srcLength);

	@Override
	public XPreputtingEnum<E> preputAll(XGettingCollection<? extends E> elements);

}
