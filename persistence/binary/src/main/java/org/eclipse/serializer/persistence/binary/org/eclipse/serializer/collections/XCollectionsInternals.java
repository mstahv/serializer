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

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.eclipse.serializer.collections.BulkList;
import org.eclipse.serializer.collections.ConstHashEnum;
import org.eclipse.serializer.collections.ConstHashTable;
import org.eclipse.serializer.collections.ConstList;
import org.eclipse.serializer.collections.EqBulkList;
import org.eclipse.serializer.collections.EqConstHashEnum;
import org.eclipse.serializer.collections.EqConstHashTable;
import org.eclipse.serializer.collections.EqHashEnum;
import org.eclipse.serializer.collections.EqHashTable;
import org.eclipse.serializer.collections.FixedList;
import org.eclipse.serializer.collections.HashEnum;
import org.eclipse.serializer.collections.HashTable;
import org.eclipse.serializer.collections.LimitList;
import org.eclipse.serializer.collections.Singleton;
import org.eclipse.serializer.hashing.HashEqualator;
import org.eclipse.serializer.memory.XMemory;
import org.eclipse.serializer.memory.sun.JdkInternals;
import org.eclipse.serializer.reflect.XReflect;

final class XCollectionsInternals
{
	// CHECKSTYLE.OFF: ConstantName: type names are intentionally unchanged
	private static final long
		OFFSET_BulkList_data             = getFieldOffset(BulkList        .class, "data"         ),
		OFFSET_BulkList_size             = getFieldOffset(BulkList        .class, "size"         ),
		OFFSET_ConstHashEnum_size        = getFieldOffset(ConstHashEnum   .class, "size"         ),
		OFFSET_ConstList_data            = getFieldOffset(ConstList       .class, "data"         ),
		OFFSET_EqBulkList_data           = getFieldOffset(EqBulkList      .class, "data"         ),
		OFFSET_EqBulkList_size           = getFieldOffset(EqBulkList      .class, "size"         ),
		OFFSET_EqConstHashEnum_size      = getFieldOffset(EqConstHashEnum .class, "size"         ),
		OFFSET_EqConstHashTable_size     = getFieldOffset(EqConstHashTable.class, "size"         ),
		OFFSET_EqHashEnum_size           = getFieldOffset(EqHashEnum      .class, "size"         ),
		OFFSET_EqHashTable_size          = getFieldOffset(EqHashTable     .class, "size"         ),
		OFFSET_EqHashTable_hashEqualator = getFieldOffset(EqHashTable     .class, "hashEqualator"),
		OFFSET_EqHashTable_keys          = getFieldOffset(EqHashTable     .class, "keys"         ),
		OFFSET_EqHashTable_values        = getFieldOffset(EqHashTable     .class, "values"       ),
		OFFSET_FixedList_data            = getFieldOffset(FixedList       .class, "data"         ),
		OFFSET_HashEnum_size             = getFieldOffset(HashEnum        .class, "size"         ),
		OFFSET_HashTable_size            = getFieldOffset(EqHashTable     .class, "size"         ),
		OFFSET_HashTable_keys            = getFieldOffset(EqHashTable     .class, "keys"         ),
		OFFSET_HashTable_values          = getFieldOffset(EqHashTable     .class, "values"       ),
		OFFSET_LimitList_data            = getFieldOffset(LimitList       .class, "data"         ),
		OFFSET_LimitList_size            = getFieldOffset(LimitList       .class, "size"         ),
		OFFSET_Singleton_element         = getFieldOffset(Singleton       .class, "element"      )
	;
	private static final Method
		METHOD_ConstHashEnum_internalAdd = getDeclaredMethod(ConstHashEnum.class, "internalAdd", Object.class)
	;
	private static final Method
		METHOD_ConstHashTable_internalAdd = getDeclaredMethod(ConstHashTable.class, "internalAdd", Object.class, Object.class)
	;
	private static final Method
		METHOD_EqConstHashEnum_internalCollectUnhashed = getDeclaredMethod(EqConstHashEnum.class, "internalCollectUnhashed", Object.class)
	;
	private static final Method
		METHOD_EqConstHashEnum_internalRehash = getDeclaredMethod(EqConstHashEnum.class, "internalRehash")
	;
	private static final Method
		METHOD_EqConstHashTable_internalAdd = getDeclaredMethod(EqConstHashTable.class, "internalAdd", Object.class, Object.class)
	;
	private static final Method
		METHOD_EqConstHashTable_internalRehash = getDeclaredMethod(EqConstHashTable.class, "internalRehash")
	;
	private static final Method
		METHOD_EqHashEnum_internalCollectUnhashed = getDeclaredMethod(EqHashEnum.class, "internalCollectUnhashed", Object.class)
	;
	private static final Method
		METHOD_EqHashTable_internalCollectUnhashed = getDeclaredMethod(EqHashTable.class, "internalCollectUnhashed", Object.class, Object.class)
	;
	private static final Method
		METHOD_HashTable_internalAdd = getDeclaredMethod(HashTable.class, "internalAdd", Object.class, Object.class)
	;
	// CHECKSTYLE.ON: ConstantName
	
	static final long getFieldOffset(final Class<?> type, final String declaredFieldName)
	{
		// minimal algorithm, only for local use
		for(Class<?> c = type; c != null && c != Object.class; c = c.getSuperclass())
		{
			try
			{
				for(final Field field : c.getDeclaredFields())
				{
					if(field.getName().equals(declaredFieldName))
					{
						return JdkInternals.VM().objectFieldOffset(field);
					}
				}
			}
			catch(final Exception e)
			{
				throw new Error(e); // explode and die :)
			}
		}
		throw new Error("Field not found: " + type.getName() + '#' + declaredFieldName);
	}
	
	static final Method getDeclaredMethod(final Class<?> c, final String name, final Class<?>... parameterTypes)
	{
		return XReflect.setAccessible(XReflect.getDeclaredMethod(c, name, parameterTypes));
	}
	
	public static Object[] getData(final BulkList<?> instance)
	{
		return (Object[])XMemory.getObject(instance, OFFSET_BulkList_data);
	}
	
	public static Object[] getData(final ConstList<?> instance)
	{
		return (Object[])XMemory.getObject(instance, OFFSET_ConstList_data);
	}
	
	public static Object[] getData(final EqBulkList<?> instance)
	{
		return (Object[])XMemory.getObject(instance, OFFSET_EqBulkList_data);
	}
	
	public static Object[] getData(final FixedList<?> instance)
	{
		return (Object[])XMemory.getObject(instance, OFFSET_FixedList_data);
	}
	
	public static Object[] getData(final LimitList<?> instance)
	{
		return (Object[])XMemory.getObject(instance, OFFSET_LimitList_data);
	}
	
	public static void setSize(final BulkList<?> instance, final int size)
	{
		XMemory.set_int(instance, OFFSET_BulkList_size, size);
	}
	
	public static void setSize(final LimitList<?> instance, final int size)
	{
		XMemory.set_int(instance, OFFSET_LimitList_size, size);
	}
	
	public static void setSize(final ConstHashEnum<?> instance, final int size)
	{
		XMemory.set_int(instance, OFFSET_ConstHashEnum_size, size);
	}
	
	public static void setSize(final EqBulkList<?> instance, final int size)
	{
		XMemory.set_int(instance, OFFSET_EqBulkList_size, size);
	}
	
	public static void setSize(final EqConstHashEnum<?> instance, final int size)
	{
		XMemory.set_int(instance, OFFSET_EqConstHashEnum_size, size);
	}
	
	public static void setSize(final EqConstHashTable<?, ?> instance, final int size)
	{
		XMemory.set_int(instance, OFFSET_EqConstHashTable_size, size);
	}
	
	public static void setSize(final EqHashEnum<?> instance, final int size)
	{
		XMemory.set_int(instance, OFFSET_EqHashEnum_size, size);
	}
	
	public static void setSize(final EqHashTable<?, ?> instance, final int size)
	{
		XMemory.set_int(instance, OFFSET_EqHashTable_size, size);
	}
	
	public static void setHashEqualator(final EqHashTable<?, ?> instance, final HashEqualator<?> hashEqualator)
	{
		XMemory.setObject(instance, OFFSET_EqHashTable_hashEqualator, hashEqualator);
	}
	
	public static void setKeys(final EqHashTable<?, ?> instance, final EqHashTable<?, ?>.Keys keys)
	{
		XMemory.setObject(instance, OFFSET_EqHashTable_keys, keys);
	}
	
	public static void setValues(final EqHashTable<?, ?> instance, final EqHashTable<?, ?>.Values values)
	{
		XMemory.setObject(instance, OFFSET_EqHashTable_values, values);
	}
	
	public static void setSize(final HashEnum<?> instance, final int size)
	{
		XMemory.set_int(instance, OFFSET_HashEnum_size, size);
	}
	
	public static void setSize(final HashTable<?, ?> instance, final int size)
	{
		XMemory.set_int(instance, OFFSET_HashTable_size, size);
	}

	public static void setKeys(final HashTable<?, ?> instance, final HashTable<?, ?>.Keys keys)
	{
		XMemory.setObject(instance, OFFSET_HashTable_keys, keys);
	}
	
	public static void setValues(final HashTable<?, ?> instance, final HashTable<?, ?>.Values values)
	{
		XMemory.setObject(instance, OFFSET_HashTable_values, values);
	}
	
	public static void setElement(final Singleton<?> instance, final Object element)
	{
		XMemory.setObject(instance, OFFSET_Singleton_element, element);
	}
	
	public static void internalAdd(final ConstHashEnum<?> instance, final Object item)
	{
		XReflect.invoke(METHOD_ConstHashEnum_internalAdd, instance, item);
	}
	
	public static void internalAdd(final ConstHashTable<?, ?> instance, final Object key, final Object value)
	{
		XReflect.invoke(METHOD_ConstHashTable_internalAdd, instance, key, value);
	}
	
	public static void internalCollectUnhashed(final EqConstHashEnum<?> instance, final Object item)
	{
		XReflect.invoke(METHOD_EqConstHashEnum_internalCollectUnhashed, instance, item);
	}
	
	public static void internalRehash(final EqConstHashEnum<?> instance)
	{
		XReflect.invoke(METHOD_EqConstHashEnum_internalRehash, instance);
	}
	
	public static void internalAdd(final EqConstHashTable<?, ?> instance, final Object key, final Object value)
	{
		XReflect.invoke(METHOD_EqConstHashTable_internalAdd, instance, key, value);
	}
	
	public static void internalRehash(final EqConstHashTable<?, ?> instance)
	{
		XReflect.invoke(METHOD_EqConstHashTable_internalRehash, instance);
	}
	
	public static void internalCollectUnhashed(final EqHashEnum<?> instance, final Object item)
	{
		XReflect.invoke(METHOD_EqHashEnum_internalCollectUnhashed, instance, item);
	}
	
	public static void internalCollectUnhashed(final EqHashTable<?, ?> instance, final Object key, final Object value)
	{
		XReflect.invoke(METHOD_EqHashTable_internalCollectUnhashed, instance, key, value);
	}
	
	public static void internalAdd(final HashTable<?, ?> instance, final Object key, final Object value)
	{
		XReflect.invoke(METHOD_HashTable_internalAdd, instance, key, value);
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////
	/**
	 * Dummy constructor to prevent instantiation of this static-only utility class.
	 * 
	 * @throws UnsupportedOperationException when called
	 */
	private XCollectionsInternals()
	{
		// static only
		throw new UnsupportedOperationException();
	}
}
