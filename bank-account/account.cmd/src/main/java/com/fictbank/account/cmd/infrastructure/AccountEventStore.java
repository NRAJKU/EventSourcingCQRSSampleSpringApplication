package com.fictbank.account.cmd.infrastructure;

import com.fictbank.account.cmd.domain.AccountAggregate;
import com.fictbank.account.cmd.domain.EventStoreRepository;
import com.fictbank.cqrs.core.events.BaseEvent;
import com.fictbank.cqrs.core.events.EventModel;
import com.fictbank.cqrs.core.exceptions.AggregateNotFoundException;
import com.fictbank.cqrs.core.exceptions.ConcurrencyException;
import com.fictbank.cqrs.core.infrastructure.EventStore;
import com.fictbank.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountEventStore implements EventStore {
    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }
        var version = expectedVersion;
        for (var event: events) {
           version++;
           event.setVersion(version);
           var eventModel = EventModel.builder()
                   .timeStamp(new Date())
                   .aggregateIdentifier(aggregateId)
                   .aggregateType(AccountAggregate.class.getTypeName())
                   .version(version)
                   .eventType(event.getClass().getTypeName())
                   .eventData(event)
                   .build();
           var persistedEvent = eventStoreRepository.save(eventModel);
           // TODO: instead of doing it this way we can adapt to - 'Polling' / 'transaction log tailing' mechanism
           if (!persistedEvent.getId().isEmpty()) {
               System.out.println("Message Produced on Topic: " + event.getClass().getSimpleName());
               eventProducer.produce(event.getClass().getSimpleName(), event);
           }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            throw new AggregateNotFoundException("Incorrect account ID provided!");
        }
        return eventStream.stream().map(x -> x.getEventData()).collect(Collectors.toList());
    }

    @Override
    public List<String> getAggregateIds() {
        var eventStream = eventStoreRepository.findAll();
        if (eventStream == null || eventStream.isEmpty()) {
            throw new IllegalStateException("Could not retrieve event stream from the event store!");
        }
        return eventStream.stream().map(EventModel::getAggregateIdentifier).distinct().collect(Collectors.toList());
    }
}
