package com.fictbank.cqrs.core.producers;

import com.fictbank.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
