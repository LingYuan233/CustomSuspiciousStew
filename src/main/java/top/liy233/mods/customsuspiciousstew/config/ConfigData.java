package top.liy233.mods.customsuspiciousstew.config;

public class ConfigData {
    private String raw;
    private String effectId;
    private int duration;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getEffectId() {
        return effectId;
    }

    public void setEffectId(String effectId) {
        this.effectId = effectId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ConfigData() {
    }

    public ConfigData(String raw, String effectId, int duration) {
        this.raw = raw;
        this.effectId = effectId;
        this.duration = duration;
    }
}
