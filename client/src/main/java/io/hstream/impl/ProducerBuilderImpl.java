package io.hstream.impl;

import static com.google.common.base.Preconditions.*;

import io.hstream.Producer;
import io.hstream.ProducerBuilder;
import java.util.List;

public class ProducerBuilderImpl implements ProducerBuilder {

  private String streamName;

  private boolean enableBatch = false;

  private int recordCountLimit = 1;

  private final List<String> serverUrls;
  private final ChannelProvider channelProvider;

  public ProducerBuilderImpl(List<String> serverUrls, ChannelProvider channelProvider) {
    this.serverUrls = serverUrls;
    this.channelProvider = channelProvider;
  }

  @Override
  public ProducerBuilder stream(String streamName) {
    this.streamName = streamName;
    return this;
  }

  @Override
  public ProducerBuilder enableBatch() {
    this.enableBatch = true;
    return this;
  }

  @Override
  public ProducerBuilder recordCountLimit(int recordCountLimit) {
    this.recordCountLimit = recordCountLimit;
    return this;
  }

  @Override
  public Producer build() {
    checkNotNull(streamName);
    return new ProducerImpl(serverUrls, channelProvider, streamName, enableBatch, recordCountLimit);
  }
}
