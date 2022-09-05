package yando0.finesoftware.fallguys;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class SomeExpansion extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "someauthor";
    }

    @Override
    public String getIdentifier() {
        return "example";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }
}
