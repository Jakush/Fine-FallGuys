package retamrovec.finesoftware.fallguys.Builders;

public class CommandBuilder {

    private boolean bol;
    private boolean usingOption;
    public CommandBuilder(boolean bol) {
        this.bol = bol;
    }
// ItzKillerSK#0001
    public CommandBuilder option() {
        usingOption = true;
        return this;
    }

    public CommandBuilder True(Runnable lambda) {
        if (!usingOption) {
            throw new IllegalArgumentException("Illegal use of CommandBuilder.");
        }
        if (bol) {
            lambda.run();
        }
        return this;
    }

    public CommandBuilder False(Runnable lambda) {
        if (!usingOption) {
            throw new IllegalArgumentException("Illegal use of CommandBuilder.");
        }
        if (!bol) {
            lambda.run();
        }
        return this;
    }

    public void reset() {
        if (!usingOption) {
            throw new IllegalArgumentException("Illegal use of CommandBuilder.");
        }
        bol = false;
    }
}