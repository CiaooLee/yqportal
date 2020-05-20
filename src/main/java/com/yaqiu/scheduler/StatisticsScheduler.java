package com.yaqiu.scheduler;

import com.yaqiu.entity.OperationLog;
import com.yaqiu.entity.SessionLog;
import com.yaqiu.service.OperationLogService;
import com.yaqiu.service.SessionLogService;
import com.yaqiu.util.DateUtil;
import com.yaqiu.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

import static com.yaqiu.constant.GlobalConstant.COMPUTER_TYPE;
import static com.yaqiu.constant.GlobalConstant.MOBILE_TYPE;

@Component
public class StatisticsScheduler {
    private final Logger logger = LoggerFactory.getLogger(StatisticsScheduler.class);

    @Resource
    private SessionLogService sessionLogService;

    @Resource
    private OperationLogService operationLogService;

    /**
     * @Description 每日流量统计
     * @Description 每天23:59生成
     * @author CiaoLee
     */
    @Scheduled(cron = "0 59 23 ? * *")
    public void trafficStatisticsDaily() {
        /* 获取今天的时间 */
        String dateToday = DateUtil.getCurrentDate();
        try {
            /* 查询今日所有的SessionLog */
            List<SessionLog> sessionLogsToday = sessionLogService.getSessionLogsToday();
            /* 查询今日所有的OperationLog */
            List<OperationLog> operationLogsToday = operationLogService.getOperationLogsToday();
            /* 生成日志开始 */
            logger.info("===============["+dateToday+"流量统计]===============");
            /* 初始化设备数量计数器 */
            int mobileCount = 0; //移动端访问
            int computerCount = 0; //电脑端访问
            int crawlerCount = 0; //爬虫访问
            int localVisitorCount = 0; //本地游客访问
            /* 分析今日SessionLog */
            int visitorCount = sessionLogsToday.size();
            logger.info(">>>>今日访问流量："+visitorCount);
            logger.info(">>>>设备类型统计位于文档末尾");
            logger.info("");
            /* 获取每个游客的操作记录 */
            for(SessionLog sessionLog : sessionLogsToday) {
                //记录设备类型
                String deviceType = sessionLog.getDeviceType();
                if(MOBILE_TYPE.equals(deviceType)) mobileCount++;
                if(COMPUTER_TYPE.equals(deviceType)) computerCount++;
                //获取SessionLog主键
                String sessionLogId = sessionLog.getId();
                //编辑归属地信息
                String province = sessionLog.getProvince();
                String city = sessionLog.getCity();
                String isp = sessionLog.getIsp();
                String visitorOrigin = ObjectUtil.isNotEmpty(province)?province+" ":"";
                visitorOrigin += ObjectUtil.isNotEmpty(city)?city+" ":"";
                visitorOrigin += isp;
                if(ObjectUtil.isEmpty(visitorOrigin)) visitorOrigin = "未知归属地";
                visitorOrigin = visitorOrigin.trim(); //去除前后空格=> 归属地查询不完全时 尾部会出现空格
                //编辑访问者类型信息=>游客or爬虫or阿里云/腾讯云
                String browserGroup = sessionLog.getBrowserGroup();
                String visitorType = "游客";
                if(browserGroup.contains("Robot") || browserGroup.contains("Spider") || browserGroup.contains("Tool")) {
                    visitorType = "爬虫";
                    crawlerCount++;
                }
                if("阿里云".equals(isp) || "腾讯云".equals(isp)) {
                    visitorType = "";
                    crawlerCount++;
                }
                if("重庆".equals(province) || "重庆".equals(city)) {
                    localVisitorCount++;
                }
                //生成SessionLog日志
                logger.info(">>>>["+visitorOrigin+"]"+visitorType+"于["+sessionLog.getCreateTime()+"]访问了站点<<<<");
                logger.info(">>>>ID："+sessionLogId);
                logger.info(">>>>IP："+sessionLog.getIp());
                logger.info(">>>>浏览器："+browserGroup);
                logger.info(">>>>设备类型："+sessionLog.getDeviceType());
                logger.info(">>>>操作系统："+sessionLog.getOsGroup());
                //获取此次会话的所有操作记录
                Iterator<OperationLog> iterator = operationLogsToday.iterator();
                while(iterator.hasNext()){
                    OperationLog operationLog = iterator.next();
                    if(operationLog.getSessionLogId().equals(sessionLogId)) {
                        logger.info(">>>>"+visitorType+"于"+operationLog.getCreateTime()+" "+operationLog.getContent());
                        iterator.remove();
                    }
                }
                logger.info("");
            }
            /* 生成日志结束 */
            logger.info("===============["+dateToday+"设备类型统计]===============");
            logger.info(">>>>爬虫访问："+crawlerCount);
            logger.info(">>>>电脑端访问："+computerCount);
            logger.info(">>>>手机端访问："+mobileCount);
            logger.info(">>>>本地游客访问："+localVisitorCount);
            logger.info(">>>>未知设备访问："+(visitorCount-computerCount-mobileCount-crawlerCount));
            logger.info("===============["+dateToday+"流量统计结束]===============");
        } catch(Exception e) {
            logger.error("["+dateToday+"流量统计生成失败]");
        }
    }
}
