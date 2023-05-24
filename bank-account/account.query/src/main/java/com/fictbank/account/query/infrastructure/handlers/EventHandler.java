package com.fictbank.account.query.infrastructure.handlers;

import com.fictbank.account.common.events.AccountClosedEvent;
import com.fictbank.account.common.events.AccountOpenedEvent;
import com.fictbank.account.common.events.FundsDepositedEvent;
import com.fictbank.account.common.events.FundsWithdrawnEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
