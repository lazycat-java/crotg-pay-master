package com.crotg.pay.service.system.test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.container.Container;

/**
 * 
 * @描述: 启动Dubbo服务用的MainClass.
 * @作者: chengdongdong .
 * @版本: 1.0 .
 */
public class SystemDubbo {
  
  public static final String CONTAINER_KEY = "dubbo.container";
  
  public static final String SHUTDOWN_HOOK_KEY = "dubbo.shutdown.hook";
  
  private static final Logger logger = LoggerFactory.getLogger(SystemDubbo.class);
  
  private static final ExtensionLoader<Container> loader = ExtensionLoader.getExtensionLoader(Container.class);
  
  private static volatile boolean running = true;
  
  public static void main(String[] args) {
    try {
      if (args == null || args.length == 0) {
        String config = ConfigUtils.getProperty(CONTAINER_KEY, loader.getDefaultExtensionName());
        args = Constants.COMMA_SPLIT_PATTERN.split(config);
      }
      
      final List<Container> containers = new ArrayList<Container>();
      for (int i = 0; i < args.length; i++) {
        containers.add(loader.getExtension(args[i]));
      }
      logger.info("Use container type(" + Arrays.toString(args) + ") to run dubbo serivce.");
      
      if ("true".equals(System.getProperty(SHUTDOWN_HOOK_KEY))) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
          public void run() {
            for (Container container : containers) {
              try {
                container.stop();
                logger.info("Dubbo " + container.getClass().getSimpleName() + " stopped!");
              } catch (Throwable t) {
                logger.error(t.getMessage(), t);
              }
              synchronized (SystemDubbo.class) {
                running = false;
                SystemDubbo.class.notify();
              }
            }
          }
        });
      }
      
      for (Container container : containers) {
        container.start();
        logger.info("Dubbo " + container.getClass().getSimpleName() + " started!");
      }
      System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
          + " Dubbo service server started!");
    } catch (RuntimeException e) {
      e.printStackTrace();
      logger.error(e.getMessage(), e);
      System.exit(1);
    }
    synchronized (SystemDubbo.class) {
      while (running) {
        try {
          SystemDubbo.class.wait();
        } catch (Throwable e) {
        }
      }
    }
  }
  
 
  
}