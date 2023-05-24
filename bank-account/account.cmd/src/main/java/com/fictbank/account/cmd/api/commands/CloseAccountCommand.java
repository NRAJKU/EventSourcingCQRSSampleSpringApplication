package com.fictbank.account.cmd.api.commands;

import com.fictbank.cqrs.core.commands.BaseCommand;

public class CloseAccountCommand extends BaseCommand {
    public CloseAccountCommand(String id) {
        super(id);
    }
}
