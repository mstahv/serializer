/*-
 * #%L
 * Eclipse Serializer Persistence
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
module org.eclipse.serializer.persistence
{
	exports org.eclipse.serializer.persistence.exceptions;
	exports org.eclipse.serializer.persistence.internal;
	/* .internal but LoggingLegacyTypeMappingResultor and PersistenceTypeDictionaryFileHandler used in Eclipse Store
	FIXME These 2 classes need to be moved.
	 */
	exports org.eclipse.serializer.persistence.types;
	exports org.eclipse.serializer.persistence.util;

	requires transitive org.eclipse.serializer.afs;
	/* To indicate we use these modules directly, but they also come through org.eclipse.serializer.afs */
	requires org.eclipse.serializer.base;
	requires org.slf4j;
}
