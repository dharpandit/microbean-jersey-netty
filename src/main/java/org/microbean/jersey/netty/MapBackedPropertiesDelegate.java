/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright © 2019 microBean™.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.microbean.jersey.netty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.glassfish.jersey.internal.PropertiesDelegate;

/**
 * A simple {@link PropertiesDelegate} built around a {@link Map}.
 *
 * <h2>Thread Safety</h2>
 *
 * <p>Instances of this class are <strong>not</strong> safe for
 * concurrent use by multiple threads.</p>
 *
 * @author <a href="https://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 *
 * @see PropertiesDelegate
 */
public final class MapBackedPropertiesDelegate implements PropertiesDelegate {


  /*
   * Instance fields.
   */

  
  private final Map<String, Object> map;


  /*
   * Constructors
   */
  

  /**
   * Creates a new {@link MapBackedPropertiesDelegate}.
   *
   * @see #MapBackedPropertiesDelegate(Map)
   */
  public MapBackedPropertiesDelegate() {
    this(new HashMap<>());
  }

  /**
   * Creates a new {@link MapBackedPropertiesDelegate}.
   *
   * @param map the {@link Map} implementing this {@link
   * MapBackedPropertiesDelegate}; may be {@code null} in which case a
   * {@linkplain HashMap#HashMap() new} {@link HashMap} will be used
   * instead
   */
  public MapBackedPropertiesDelegate(final Map<String, Object> map) {
    super();
    this.map = map == null ? new HashMap<>() : map;
  }

  /**
   * Returns the property value indexed under the supplied {@code
   * name}, or {@code null} if the proeprty value is itself {@code
   * null} or if no such property exists.
   *
   * <p>This method may return {@code null}.</p>
   *
   * @param name the name of the property; may be {@code null}
   *
   * @return the property value, or {@code null}
   */
  @Override
  public final Object getProperty(final String name) {
    return this.map.get(name);
  }

  /**
   * Returns a {@link Collection} of the names of properties that this
   * {@link MapBackedPropertiesDelegate} stores.
   *
   * <p>This method never returns {@code null}.</p>
   *
   * <p>The order of the elements of the returned {@link Collection}
   * is deliberately unspecified, is subject to change, and must not
   * be relied upon.</p>
   *
   * @return a non-{@code null} {@link Collection} of property names
   */
  @Override
  public final Collection<String> getPropertyNames() {
    return this.map.keySet();
  }

  /**
   * Sets the supplied {@code value} as the property value to be
   * indexed under the supplied {@code name}.
   *
   * @param name the name of the property to set; may be {@code null}
   *
   * @param value the value of the property to set; may be {@code
   * null}
   */
  @Override
  public final void setProperty(final String name, final Object value) {
    this.map.put(name, value);
  }

  /**
   * Removes any property value indexed under the supplied property
   * name.
   *
   * @param name the name of the property to remove; may be {@code
   * null}
   */
  @Override
  public final void removeProperty(final String name) {
    this.map.remove(name);
  }
  
}
