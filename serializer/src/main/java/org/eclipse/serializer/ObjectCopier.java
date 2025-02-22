package org.eclipse.serializer;

import static org.eclipse.serializer.util.X.notNull;

import java.io.Closeable;

import org.eclipse.serializer.persistence.binary.types.Binary;
import org.eclipse.serializer.persistence.types.PersistenceManager;
import org.eclipse.serializer.reference.Reference;

/*-
 * #%L
 * Eclipse Serializer
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

import org.eclipse.serializer.util.X;

public interface ObjectCopier extends Closeable
{
	public <T> T copy(T source);
	
	@Override
	public void close();
	
	
	public static ObjectCopier New()
	{
		return new Default(SerializerFoundation.New());
	}
	
	public static ObjectCopier New(final SerializerFoundation<?> foundation)
	{
		return new Default(
			notNull(foundation)
		);
	}
	
	
	public static class Default implements ObjectCopier
	{
		private final SerializerFoundation<?> foundation        ;
		private PersistenceManager<Binary>    persistenceManager;
				
		Default(final SerializerFoundation<?> foundation)
		{
			super();
			this.foundation = foundation;
		}

		@SuppressWarnings("unchecked")
		@Override
		public synchronized <T> T copy(final T source)
		{
			this.lazyInit();
			
			this.persistenceManager.store(source);
			return (T)this.persistenceManager.get();
		}
		
		@Override
		public synchronized void close()
		{
			if(this.persistenceManager != null)
			{
				this.persistenceManager.objectRegistry().clearAll();
				this.persistenceManager.close();
				this.persistenceManager = null;
			}
		}
		
		private void lazyInit()
		{
			if(this.persistenceManager == null)
			{
				final Reference<Binary> buffer = X.Reference(null)               ;
				final Serializer.Source source = ()   -> X.Constant(buffer.get());
				final Serializer.Target target = data -> buffer.set(data)        ;

				this.persistenceManager = this.foundation
					.setPersistenceSource(source)
					.setPersistenceTarget(target)
					.createPersistenceManager()
				;
			}
			else
			{
				this.persistenceManager.objectRegistry().truncateAll();
			}
		}
		
	}
	
}
