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

import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelOutboundInvoker;

import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpContent;

/**
 * An {@link AbstractByteBufBackedChannelOutboundInvokingOutputStream}
 * that writes {@link HttpContent} messages.
 *
 * @author <a href="https://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 *
 * @see #createMessage(ByteBuf)
 *
 * @see #createLastMessage()
 */
public class ByteBufBackedChannelOutboundInvokingHttpContentOutputStream extends AbstractByteBufBackedChannelOutboundInvokingOutputStream<HttpContent> {


  /*
   * Constructors.
   */


  /**
   * Creates a new {@link
   * ByteBufBackedChannelOutboundInvokingHttpContentOutputStream}.
   *
   * @param channelOutboundInvoker the {@link ChannelOutboundInvoker}
   * to which operations are adapted; must not be {@code null}
   *
   * @param closeChannelOutboundInvoker whether {@link
   * ChannelOutboundInvoker#close(ChannelPromise)} will be called on
   * the supplied {@link ChannelOutboundInvoker} when {@link #close()
   * close()} is called
   *
   * @see
   * #ByteBufBackedChannelOutboundInvokingHttpContentOutputStream(ChannelOutboundInvoker,
   * int, boolean,
   * AbstractByteBufBackedChannelOutboundInvokingOutputStream.ByteBufCreator)
   */
  public ByteBufBackedChannelOutboundInvokingHttpContentOutputStream(final ChannelOutboundInvoker channelOutboundInvoker,
                                                                     final boolean closeChannelOutboundInvoker) {
    this(channelOutboundInvoker, Integer.MAX_VALUE, closeChannelOutboundInvoker, null);
  }

  /**
   * Creates a new {@link
   * ByteBufBackedChannelOutboundInvokingHttpContentOutputStream}.
   *
   * @param channelOutboundInvoker the {@link ChannelOutboundInvoker}
   * to which operations are adapted; must not be {@code null}
   *
   * @param flushThreshold the minimum number of bytes that this
   * instance has to {@linkplain #write(byte[], int, int) write}
   * before an automatic {@linkplain #flush() flush} will take place;
   * if less than {@code 0} {@code 0} will be used instead; if {@code
   * Integer#MAX_VALUE} then no automatic flushing will occur
   *
   * @param closeChannelOutboundInvoker whether {@link
   * ChannelOutboundInvoker#close(ChannelPromise)} will be called on
   * the supplied {@link ChannelOutboundInvoker} when {@link #close()
   * close()} is called
   *
   * @param byteBufCreator a {@link ByteBufCreator} that will be used
   * to {@linkplain ByteBufCreator#toByteBuf(byte[], int, int) create
   * <code>ByteBuf</code> instances}; may be {@code null} in which
   * case a default {@link ByteBufCreator} adapting {@link
   * io.netty.buffer.Unpooled#wrappedBuffer(byte[], int, int)} will be
   * used instead
   */
  public ByteBufBackedChannelOutboundInvokingHttpContentOutputStream(final ChannelOutboundInvoker channelOutboundInvoker,
                                                                     final int flushThreshold,
                                                                     final boolean closeChannelOutboundInvoker,
                                                                     final ByteBufCreator byteBufCreator) {
    super(channelOutboundInvoker, flushThreshold, closeChannelOutboundInvoker, byteBufCreator);
  }


  /*
   * Instance methods.
   */

  
  /**
   * Returns a new {@link DefaultHttpContent} whose {@link
   * DefaultHttpContent#content() content()} method returns the
   * supplied {@link ByteBuf}.
   *
   * <p>This method never returns {@code null}.</p>
   *
   * @param content a {@link ByteBuf}; must not be {@code null}
   *
   * @return a new {@link DefaultHttpContent} whose {@link
   * DefaultHttpContent#content() content()} method returns the
   * supplied {@link ByteBuf}; never {@code null}
   */
  @Override
  protected HttpContent createMessage(final ByteBuf content) {
    return new DefaultHttpContent(content);
  }


  /**
   * Returns a new {@link DefaultLastHttpContent} when invoked.
   *
   * <p>This method never returns {@code null}.</p>
   *
   * @return a new {@link DefaultLastHttpContent}
   *
   * @see #close()
   */
  @Override
  protected HttpContent createLastMessage() {
    return new DefaultLastHttpContent();
  }

}
