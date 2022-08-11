package com.guardproducts.bungee.commands;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.guardproducts.api.data.WhitelistManager;
import com.guardproducts.util.ChatUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class WhitelistCommand extends Command {

    public WhitelistCommand(String name) {
        super(name, "guardspigot.whitelist");
    }

    @Inject
    private WhitelistManager whitelistManager;

    @Inject
    private @Named("wlAddedMessage") String wlAddedMessage;
    @Inject
    private @Named("wlRemovedMessage") String wlRemovedMessage;
    @Inject
    private @Named("wlExistsMessage") String wlExistsMessage;
    @Inject
    private @Named("wlNoExistsMessage") String wlNoExistsMessage;

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if(args.length < 2) {
            commandSender.sendMessage(ChatUtil.fixColor("&cUsage: /gpwhitelist <add/remove> <ip>"));
            return;
        }
        switch (args[0]) {
            case "add":
                boolean added = whitelistManager.addWhitelist(args[1]);
                commandSender.sendMessage(ChatUtil.fixColor( (added ? this.wlAddedMessage : this.wlExistsMessage) ));
                break;
            case "remove":
                boolean removed = whitelistManager.removeWhitelist(args[1]);
                commandSender.sendMessage(ChatUtil.fixColor( (removed ? this.wlRemovedMessage : this.wlNoExistsMessage) ));
                break;
            default:
                commandSender.sendMessage(ChatUtil.fixColor("&cUsage: /gpwhitelist <add/remove> <ip>"));
                break;
        }

    }
}
