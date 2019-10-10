package model;

public enum Command {

    INIT("init"),
    MOVE("move"),
    OPPONENT("opponent");

    private String commandMessage;

    Command(String commandMessage) {
        this.commandMessage = commandMessage;
    }

    public String getCommandMessage() {
        return commandMessage;
    }

    public static Command getFromCommandMessage(String commandMessage) {
        for (Command command : Command.values()) {
            if (!command.getCommandMessage().equals(commandMessage)) continue;
            return command;
        }
        throw new EnumConstantNotPresentException(Command.class, commandMessage);
    }
}
