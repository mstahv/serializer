package org.eclipse.serializer.persistence.binary.jdk17.java.util;

/*-
 * #%L
 * Eclipse Serializer Persistence JDK17
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

import java.util.Set;

/**
 * Specialized handler for immutable Set implementations in Java 15 and later
 * as found in java.util.ImmutableCollections.Set12<E>
 * <p></p>
 * the implementations are returned from Set.of(), Set.of(E e1) and  Set.of(E e1, E e2)
 * <p></p>
 * The handler takes the internal constant java.util.ImmutableCollections.EMPTY
 * into account which must not be persisted.
 * 
 * @param <T> the handled type.
 */
public class BinaryHandlerImmutableCollectionsSet12<T> extends AbstractBinaryHandlerGenericImmutableCollections12<T>
{
	///////////////////////////////////////////////////////////////////////////
	// static methods //
	///////////////////

	public static BinaryHandlerImmutableCollectionsSet12<?> New()
	{
		return new BinaryHandlerImmutableCollectionsSet12<>(Set.of(new Object()).getClass());
	}


	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	protected BinaryHandlerImmutableCollectionsSet12(final Class<T> type)
	{
		super(type);
	}


	///////////////////////////////////////////////////////////////////////////
	// methods //
	////////////

	@SuppressWarnings("unchecked")
	@Override
	protected T createInstance()
	{
		return (T) Set.of(new Object());
	}

}
