package org.eclipse.serializer.persistence.binary.org.eclipse.serializer.collections;

/*-
 * #%L
 * Eclipse Serializer Persistence Binary
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

import static org.eclipse.serializer.util.X.notNull;

import org.eclipse.serializer.collections.BulkList;
import org.eclipse.serializer.persistence.binary.internal.AbstractBinaryHandlerCustomIterableSizedArray;
import org.eclipse.serializer.persistence.binary.types.Binary;
import org.eclipse.serializer.persistence.types.Persistence;
import org.eclipse.serializer.persistence.types.PersistenceFunction;
import org.eclipse.serializer.persistence.types.PersistenceLoadHandler;
import org.eclipse.serializer.persistence.types.PersistenceReferenceLoader;
import org.eclipse.serializer.persistence.types.PersistenceSizedArrayLengthController;
import org.eclipse.serializer.persistence.types.PersistenceStoreHandler;


public final class BinaryHandlerBulkList
extends AbstractBinaryHandlerCustomIterableSizedArray<BulkList<?>>
{
	///////////////////////////////////////////////////////////////////////////
	// constants //
	//////////////

	static final long BINARY_OFFSET_SIZED_ARRAY = 0; // binary form is 100% just a sized array, so offset 0



	///////////////////////////////////////////////////////////////////////////
	// static methods //
	///////////////////

	@SuppressWarnings({"unchecked",  "rawtypes"})
	private static Class<BulkList<?>> handledType()
	{
		// no idea how to get ".class" to work otherwise
		return (Class)BulkList.class;
	}
	
	public static BinaryHandlerBulkList New(final PersistenceSizedArrayLengthController controller)
	{
		return new BinaryHandlerBulkList(
			notNull(controller)
		);
	}
	


	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////
	
	BinaryHandlerBulkList(final PersistenceSizedArrayLengthController controller)
	{
		// binary layout definition
		super(
			handledType(),
			SizedArrayFields(),
			controller
		);
	}
	


	///////////////////////////////////////////////////////////////////////////
	// methods //
	////////////

	@Override
	public final void store(
		final Binary                          data    ,
		final BulkList<?>                     instance,
		final long                            objectId,
		final PersistenceStoreHandler<Binary> handler
	)
	{
		data.storeSizedArray(
			this.typeId()                          ,
			objectId                               ,
			BINARY_OFFSET_SIZED_ARRAY              ,
			XCollectionsInternals.getData(instance),
			instance.intSize()                     ,
			handler
		);
	}

	@Override
	public final BulkList<?> create(final Binary data, final PersistenceLoadHandler handler)
	{
		return new BulkList<>();
	}

	@Override
	public final void updateState(final Binary data, final BulkList<?> instance, final PersistenceLoadHandler handler)
	{
		// must clear to avoid memory leaks due to residual references beyond the new size in existing instances.
		instance.clear();
		
		// length must be checked for consistency reasons
		instance.ensureCapacity(this.determineArrayLength(data, BINARY_OFFSET_SIZED_ARRAY));
		
		XCollectionsInternals.setSize(instance, data.updateSizedArrayObjectReferences(
			BINARY_OFFSET_SIZED_ARRAY,
			handler,
			XCollectionsInternals.getData(instance)
		));
	}

	@Override
	public final void iterateInstanceReferences(final BulkList<?> instance, final PersistenceFunction iterator)
	{
		Persistence.iterateReferences(iterator, XCollectionsInternals.getData(instance), 0, instance.intSize());
	}

	@Override
	public final void iterateLoadableReferences(final Binary data, final PersistenceReferenceLoader iterator)
	{
		data.iterateSizedArrayElementReferences(BINARY_OFFSET_SIZED_ARRAY, iterator);
	}
	
}
