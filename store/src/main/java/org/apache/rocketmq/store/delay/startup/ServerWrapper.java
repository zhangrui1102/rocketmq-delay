package org.apache.rocketmq.store.delay.startup;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.store.delay.ScheduleIndex;
import org.apache.rocketmq.store.delay.DefaultDelayLogFacade;
import org.apache.rocketmq.store.delay.DelayLogFacade;
import org.apache.rocketmq.store.delay.config.DefaultStoreConfiguration;
import org.apache.rocketmq.store.delay.configuration.DynamicConfig;
import org.apache.rocketmq.store.delay.configuration.DynamicConfigConstant;
import org.apache.rocketmq.store.delay.configuration.DynamicConfigLoader;
import org.apache.rocketmq.store.delay.util.Disposable;
import org.apache.rocketmq.store.delay.wheel.WheelTickManager;
import org.apache.rocketmq.logging.InternalLogger;
import org.apache.rocketmq.logging.InternalLoggerFactory;
import org.apache.rocketmq.store.MessageStore;


public class ServerWrapper implements Disposable {

    private static final InternalLogger LOGGER = InternalLoggerFactory.getLogger(ServerWrapper.class);

    private DelayLogFacade facade;
    private WheelTickManager wheelTickManager;
    private DynamicConfig config;
    private DefaultStoreConfiguration storeConfig;
    private MessageStore writeMessageStore;

    public ServerWrapper(MessageStore writeMessageStore,String delayConfPath){
        if(StringUtils.isNotBlank(delayConfPath)){
            System.setProperty(DynamicConfigConstant.DELAY_PATH,delayConfPath);
        }
        this.writeMessageStore = writeMessageStore;
        init();
        startServer();
    }

    private void init() {
        this.config = DynamicConfigLoader.load("delay.properties");
        storeConfig = new DefaultStoreConfiguration(config);
        this.facade = new DefaultDelayLogFacade(storeConfig, this::iterateCallback);

        this.wheelTickManager = new WheelTickManager(storeConfig, facade,writeMessageStore);
    }

    private boolean iterateCallback(final ScheduleIndex index) {
        long scheduleTime = index.getScheduleTime();
        long offset = index.getOffset();
        if (wheelTickManager.canAdd(scheduleTime, offset)) {
            facade.scheduleLogFlush();
            wheelTickManager.addWHeel(index);
            return true;
        }

        return false;
    }

    private void startServer() {
        wheelTickManager.start();
        facade.start();
    }

    @Override
    public void destroy() {
        facade.shutdown();
        wheelTickManager.shutdown();
    }

    public DelayLogFacade getFacade(){
        return facade;
    }
}
