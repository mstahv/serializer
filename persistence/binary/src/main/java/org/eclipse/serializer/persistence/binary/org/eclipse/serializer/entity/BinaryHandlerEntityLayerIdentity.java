package org.eclipse.serializer.persistence.binary.org.eclipse.serializer.entity;

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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.eclipse.serializer.collections.BulkList;
import org.eclipse.serializer.collections.types.XGettingList;
import org.eclipse.serializer.entity.Entity;
import org.eclipse.serializer.entity.EntityLayer;
import org.eclipse.serializer.entity.EntityLayerIdentity;
import org.eclipse.serializer.exceptions.NoSuchMethodRuntimeException;
import org.eclipse.serializer.functional.Instantiator;
import org.eclipse.serializer.memory.XMemory;
import org.eclipse.serializer.persistence.binary.internal.AbstractBinaryHandlerCustomCollection;
import org.eclipse.serializer.persistence.binary.types.Binary;
import org.eclipse.serializer.persistence.types.Persistence;
import org.eclipse.serializer.persistence.types.PersistenceFunction;
import org.eclipse.serializer.persistence.types.PersistenceLoadHandler;
import org.eclipse.serializer.persistence.types.PersistenceReferenceLoader;
import org.eclipse.serializer.persistence.types.PersistenceStoreHandler;
import org.eclipse.serializer.reflect.XReflect;
import org.eclipse.serializer.util.X;

public class BinaryHandlerEntityLayerIdentity<T extends EntityLayerIdentity>
	extends AbstractBinaryHandlerCustomCollection<T>
{
	public static <T extends EntityLayerIdentity> BinaryHandlerEntityLayerIdentity<T> New(
		final Class<T>                 type                    ,
		final EntityTypeHandlerManager entityTypeHandlerManager
	)
	{
		return new BinaryHandlerEntityLayerIdentity<>(
			type,
			WrapDefaultConstructor(type),
			notNull(entityTypeHandlerManager)
		);
	}
		
	private static <T> Instantiator<T> WrapDefaultConstructor(final Class<T> type)
		throws NoSuchMethodRuntimeException
	{
		try
		{
			final Constructor<T> constructor = type.getDeclaredConstructor();
			constructor.setAccessible(true);
			return Instantiator.WrapDefaultConstructor(constructor);
		}
		catch(final NoSuchMethodException e)
		{
			throw new NoSuchMethodRuntimeException(e);
		}
		catch(final SecurityException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	private static XGettingList<Entity> collectLayers(
		final EntityLayerIdentity identity
	)
	{
		final BulkList<Entity> layers = BulkList.New();
		Entity layer = identity;
		while((layer = Entity.Static.inner(layer)) != null)
		{
			layers.add(layer);
		}
		return layers;
	}
	
	static final long BINARY_OFFSET_LAYERS = 0;
	
	static final Field FIELD_INNER = XReflect.setAccessible(XReflect.getAnyField(EntityLayer.class, "inner"));
	
	private final Instantiator<T>          instantiator            ;
	private final EntityTypeHandlerManager entityTypeHandlerManager;
		
	BinaryHandlerEntityLayerIdentity(
		final Class<T>                 type                    ,
		final Instantiator<T>          instantiator            ,
		final EntityTypeHandlerManager entityTypeHandlerManager
	)
	{
		super(
			type,
			CustomFields(
				Complex("layers",
					CustomField(Entity.class, "layer")
				)
			)
		);
		this.instantiator             = instantiator            ;
		this.entityTypeHandlerManager = entityTypeHandlerManager;
	}
	
	@Override
	public final void store(
		final Binary                          data    ,
		final T                               instance,
		final long                            objectId,
		final PersistenceStoreHandler<Binary> handler
	)
	{
		final Entity[]        layers    = collectLayers(instance).toArray(Entity.class);
		final EntityPersister persister = EntityPersister.New(
			this.entityTypeHandlerManager,
			handler
		);
		data.storeReferences(
			this.typeId()       ,
			objectId            ,
			BINARY_OFFSET_LAYERS,
			persister           ,
			layers              ,
			0                   ,
			layers.length
		);
	}
	
	@Override
	public final T create(
		final Binary                 data   ,
		final PersistenceLoadHandler handler
	)
	{
		return this.instantiator.instantiate();
	}

	@Override
	public final void updateState(
		final Binary                 data    ,
		final T                      identity,
		final PersistenceLoadHandler handler
	)
	{
		final int count = X.checkArrayRange(
			data.getListElementCountReferences(BINARY_OFFSET_LAYERS)
		);
		
		final Entity[] entities = new Entity[count];
		data.validateArrayLength(entities, BINARY_OFFSET_LAYERS);
		data.collectElementsIntoArray(BINARY_OFFSET_LAYERS, handler, entities);
		
		// count is always > 0, because minimum layered entity setup is identity and data
		this.setInner(identity, entities[0]);
		for(int i = 0; i < count - 1; i++)
		{
			this.setInner(entities[i], entities[i + 1]);
		}
	}
	
	private void setInner(final Entity entity, final Entity inner)
	{
		XMemory.setObject(
			entity,
			XMemory.objectFieldOffset(FIELD_INNER),
			inner
		);
	}
	
	@Override
	public void iterateInstanceReferences(final T instance, final PersistenceFunction iterator)
	{
		Persistence.iterateReferences(iterator, collectLayers(instance));
	}

	@Override
	public final void iterateLoadableReferences(
		final Binary                     data    ,
		final PersistenceReferenceLoader iterator
	)
	{
		data.iterateListElementReferences(BINARY_OFFSET_LAYERS, iterator);
	}
	
}
