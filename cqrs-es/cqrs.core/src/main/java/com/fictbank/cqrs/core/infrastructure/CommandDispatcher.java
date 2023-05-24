package com.fictbank.cqrs.core.infrastructure;

import com.fictbank.cqrs.core.commands.BaseCommand;
import com.fictbank.cqrs.core.commands.CommandHandlerMethod;

// Mediator
public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
