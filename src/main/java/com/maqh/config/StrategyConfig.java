package com.maqh.config;

/**
 * 策略配置
 *
 * @author maqh
 */
public class StrategyConfig {

    /**
     * 实体 是否为Lombok 模型
     */
    private boolean entityLombokModel = false;

    public boolean isEntityLombokModel() {
        return entityLombokModel;
    }

    public void setEntityLombokModel(boolean entityLombokModel) {
        this.entityLombokModel = entityLombokModel;
    }
}
